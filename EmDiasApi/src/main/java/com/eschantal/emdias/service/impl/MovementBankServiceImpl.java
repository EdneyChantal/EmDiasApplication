package com.eschantal.emdias.service.impl;

import com.eschantal.emdias.domain.*;
import com.eschantal.emdias.repository.*;
import com.eschantal.emdias.service.MovementBankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MovementBank}.
 */
@Service
@Transactional
public class MovementBankServiceImpl implements MovementBankService {

    private final Logger log = LoggerFactory.getLogger(MovementBankServiceImpl.class);

    private final MovementBankRepository movementBankRepository;
    private final NaturePlanRepository naturePlanRepository;
    private final ProjetoNaturezaRepository projetoNaturezaRepository;
    private final ProjetoRepository projetoRepository;
    private final AccountBankRepository accountBankRepository;


    public MovementBankServiceImpl(MovementBankRepository movementBankRepository, NaturePlanRepository naturePlanRepository, ProjetoNaturezaRepository projetoNaturezaRepository, ProjetoRepository projetoRepository, AccountBankRepository accountBankRepository) {
        this.movementBankRepository = movementBankRepository;
        this.naturePlanRepository = naturePlanRepository;

        this.projetoNaturezaRepository = projetoNaturezaRepository;
        this.projetoRepository = projetoRepository;
        this.accountBankRepository = accountBankRepository;
    }


    @Override
    public MovementBank save(MovementBank movementBank) {
        log.debug("Request to save MovementBank : {}", movementBank);
        return movementBankRepository.save(movementBank);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementBank> findAll() {
        log.debug("Request to get all MovementBanks");
        return movementBankRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementBank> findByFiltro(FiltroMovimentoBancario filtroMovimentoBancario) {
        return this.movementBankRepository.findByAccountBankAndIntervaloDeData(filtroMovimentoBancario.getAccountBankId(), filtroMovimentoBancario.getDataInicial(), filtroMovimentoBancario.getDataFinal());
    }

    @Override
    public Double totalMovimentoAteAData(FiltroMovimentoBancario filtroMovimentoBancario) {

        Optional<AccountBank> optionalAccountBank = this.accountBankRepository.findById(filtroMovimentoBancario.getAccountBankId());
        Optional<Double> result = this.movementBankRepository.totalContaAteAData(filtroMovimentoBancario.getAccountBankId(), filtroMovimentoBancario.getDataFinal());

        double value = 0;
        value = (optionalAccountBank.isPresent() ? optionalAccountBank.get().getValorInicial() : 0);
        value += (result.isPresent() ? result.get() : 0);


        return (value);
    }

    @Transactional
    @Override
    public void atualizaProjetoPeloRealizado(FiltroMovimentoBancario filtroMovimentoBancario) {
        if (filtroMovimentoBancario != null) {
            Optional<Projeto> oprojeto = this.projetoRepository.findById(filtroMovimentoBancario.getProjetoId());
            log.info("projeto " + (oprojeto.isPresent() ? "tem projeto " + oprojeto.get().getId() : "não tem projeto " + filtroMovimentoBancario.getProjetoId()));
            if (oprojeto.isPresent()) {
                this.totalPorNatureza(filtroMovimentoBancario).
                    stream().
                    forEach(naturezaResumo -> this.atualizaProjetoNaturezaPeloNaturezaResumo(naturezaResumo, oprojeto.get()));
            }
        }
    }

    @Override
    public void atualizaProjetoNaturezaPeloNaturezaResumo(NaturezaResumo naturezaResumo, Projeto projeto) {
        if ((naturezaResumo != null) && naturezaResumo.getNaturePlan() != null && projeto != null) {
            ProjetoNatureza projetoNatureza = this.projetoNaturezaRepository.findByIdProjetoIdNaturePlan(projeto.getId(), naturezaResumo.getNaturePlan().getId(), naturezaResumo.getDia());
            if (projetoNatureza == null) {
                projetoNatureza = new ProjetoNatureza();
                projetoNatureza.setNaturePlan(naturezaResumo.getNaturePlan());
                projetoNatureza.setProjeto(projeto);
                projetoNatureza.setValorPrevisto(naturezaResumo.getValorTotal());
                projetoNatureza.setDia(naturezaResumo.getDia());

                this.projetoNaturezaRepository.save(projetoNatureza);
            } else {
                if (Math.abs(naturezaResumo.getValorTotal()) > Math.abs(projetoNatureza.getValorPrevisto().doubleValue())) {
                    projetoNatureza.setValorPrevisto(naturezaResumo.getValorTotal());
                    this.projetoNaturezaRepository.save(projetoNatureza);
                }
            }
        }
    }

    @Override
    public List<NaturezaResumo> totalPorNatureza(FiltroMovimentoBancario filtroMovimentoBancario) {
        List<NaturePlan> listNaturePlan = this.naturePlanRepository.findByWorkSpaceId(filtroMovimentoBancario.getWorkSpaceId());
        List<NaturezaResumo> naturezaResumoList = new ArrayList<NaturezaResumo>();

        listNaturePlan.stream()
            .map(naturePlan -> this.pegaMovimentosDaNatureza(naturePlan, filtroMovimentoBancario))
            .collect(Collectors.toList())
            .stream()
            .forEach(naturezaResumos -> naturezaResumos
                .stream()
                .filter(naturezaResumo -> this.filtraNaturezaResumo(naturezaResumo))
                .forEach(naturezaResumo -> naturezaResumoList.add(naturezaResumo)));

        return naturezaResumoList;

    }

    @Override
    public List<MovementBank> findByFiltroNatureza(FiltroMovimentoBancario filtroMovimentoBancario) {
        return this.movementBankRepository.findByIntervaloDeDataNatureza(filtroMovimentoBancario.getDataInicial(), filtroMovimentoBancario.getDataFinal(), filtroMovimentoBancario.getNaturePlanId());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovementBank> findOne(Long id) {
        log.debug("Request to get MovementBank : {}", id);
        return movementBankRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MovementBank : {}", id);
        movementBankRepository.deleteById(id);
    }

    @Override
    public boolean filtraNaturezaResumo(NaturezaResumo naturezaResumo) {
        if (naturezaResumo == null) return false;
        if (naturezaResumo.getValorTotal() == 0) {
            if (naturezaResumo.getProjetoNatureza() == null) return false;
            if (naturezaResumo.getProjetoNatureza().getValorPrevisto() == 0) return false;
        }
        return true;
    }

    @Override
    public List<NaturezaResumo> pegaMovimentosDaNatureza(NaturePlan naturePlan, FiltroMovimentoBancario filtroMovimentoBancario) {


       List<Object[]> listObect = this.movementBankRepository.totalRealizadoNaturezaDia(
            filtroMovimentoBancario.getWorkSpaceId(),
            filtroMovimentoBancario.getDataInicial(),
            filtroMovimentoBancario.getDataFinal(),
            naturePlan.getId()
        );
       List<NaturezaResumo> listRes = listObect
            .stream()
            .map(objects -> objectTuplaToNaturezaResumo(filtroMovimentoBancario, objects, naturePlan))
            .collect(Collectors.toList());
       return listRes;
    }

    private NaturezaResumo objectTuplaToNaturezaResumo(FiltroMovimentoBancario filtroMovimentoBancario, Object[] objects, NaturePlan naturePlan) {
        NaturezaResumo naturezaResumo = new NaturezaResumo();
        naturezaResumo.setNaturePlan(naturePlan);
        naturezaResumo.setIdNature(naturezaResumo.getIdNature());
        naturezaResumo.setDia(retornaDataReferencia((ZonedDateTime) objects[0],filtroMovimentoBancario.getDataReferencia()));
        Optional<AccountBank> accountBank = accountBankRepository.findById((Long) objects[1]);
        naturezaResumo.setAccountBank(accountBank.get());
        naturezaResumo.setValorTotal((Double) objects[2]);
        ProjetoNatureza projetoNatureza = this.projetoNaturezaRepository.findByIdProjetoIdNaturePlan(
            filtroMovimentoBancario.getProjetoId(), naturePlan.getId(), naturezaResumo.getDia());
        naturezaResumo.setProjetoNatureza(projetoNatureza);
        return naturezaResumo;
    }
    private ZonedDateTime retornaDataReferencia(ZonedDateTime dia , ZonedDateTime referencia) {
         LocalDate dt = dia.toLocalDate().with(TemporalAdjusters.lastDayOfMonth());
         LocalDate dr = referencia.toLocalDate().with(TemporalAdjusters.lastDayOfMonth());
         if (dia.getDayOfMonth() == dt.getDayOfMonth()) return dr.atStartOfDay(ZoneId.systemDefault());
         else return  dia.withMonth(referencia.getMonthValue());
    }

}
