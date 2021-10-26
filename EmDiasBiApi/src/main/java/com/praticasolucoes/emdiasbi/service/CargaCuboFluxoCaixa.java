package com.praticasolucoes.emdiasbi.service;


import com.praticasolucoes.emdiasbi.domain.*;
import com.praticasolucoes.emdiasbi.repository.CrudCuFluxoCaixaRepository;
import com.praticasolucoes.emdiasbi.repository.CuFluxoCaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CargaCuboFluxoCaixa {

    private final EmDiasApp emDiasApp;
    private final FatoService<AccountBank, AtConta> fatoServiceAccount;
    private final FatoService<NaturePlan, AtFluxoCaixaItem> fatoServiceItemFluxo;
    private final FatoService<ZonedDateTime, AtData> fatoServiceData;
    private final CuFluxoCaixaRepository cuFluxoCaixaRepository;
    private final CrudCuFluxoCaixaRepository crudCuFluxoCaixaRepository;
    private final FatoService<Projeto, AtRelatorio> fatoServiceRelatorio;
    private final FatoService<String, AtTipoMovim> fatoServiceTipoMov;

    private List<NaturezaResumo> realizadoList;
    private List<ProjetoNatureza> previstoList;

    @Autowired
    public CargaCuboFluxoCaixa(EmDiasApp emDiasApp, FatoService<AccountBank, AtConta> fatoServiceAccount, FatoService<NaturePlan, AtFluxoCaixaItem> fatoServiceItemFluxo, FatoService<ZonedDateTime, AtData> fatoServiceData, CuFluxoCaixaRepository cuFluxoCaixaRepository, CrudCuFluxoCaixaRepository crudCuFluxoCaixaRepository, FatoService<Projeto, AtRelatorio> fatoServiceRelatorio, FatoService<String, AtTipoMovim> fatoServiceTipoMov) {
        this.emDiasApp = emDiasApp;
        this.fatoServiceAccount = fatoServiceAccount;
        this.fatoServiceItemFluxo = fatoServiceItemFluxo;
        this.fatoServiceData = fatoServiceData;
        this.cuFluxoCaixaRepository = cuFluxoCaixaRepository;
        this.crudCuFluxoCaixaRepository = crudCuFluxoCaixaRepository;
        this.fatoServiceRelatorio = fatoServiceRelatorio;
        this.fatoServiceTipoMov = fatoServiceTipoMov;
    }

    public void gerar(FiltroMovimentoBancario filtroMovimentoBancario) {
        cuFluxoCaixaRepository.deleteFluxo(filtroMovimentoBancario.getDataInicial(), filtroMovimentoBancario.getDataFinal());

        realizadoList = this.emDiasApp.getRealizado(filtroMovimentoBancario);
        previstoList = this.emDiasApp.getPrevisto(filtroMovimentoBancario);
        /*realizadoList
            .stream()
            .forEach(this::inserirRealizado);
        previstoList
            .stream()
            .forEach(this::inserePrevistoDiario);*/

    }

    private void inserirRealizado(NaturezaResumo naturezaResumo) {
        CuFluxoCaixa cuFluxoCaixa = new CuFluxoCaixa();

        AtConta atConta = fatoServiceAccount.carregarFato(naturezaResumo.getAccountBank());
        cuFluxoCaixa.setAtConta(atConta);
        AtFluxoCaixaItem atFluxoCaixaItem = fatoServiceItemFluxo.carregarFato(naturezaResumo.getNaturePlan());
        cuFluxoCaixa.setAtFluxoCaixaItem(atFluxoCaixaItem);
        AtData atData = fatoServiceData.carregarFato(naturezaResumo.getDia());
        cuFluxoCaixa.setAtData(atData);

        AtRelatorio atRelatorio = fatoServiceRelatorio.carregarFato(umProjeto());
        cuFluxoCaixa.setAtRelatorio(atRelatorio);
        AtTipoMovim atTipoMovim = fatoServiceTipoMov.carregarFato("Realizado");
        cuFluxoCaixa.setAtTipoMovim(atTipoMovim);
        cuFluxoCaixa.setValor(new BigDecimal(naturezaResumo.getValorTotal()));
        crudCuFluxoCaixaRepository.save(cuFluxoCaixa);


    }

    public void inserePrevistoDiario(ProjetoNatureza projetoNatureza) {

        if (projetoNatureza.getDia().toLocalDate().isBefore(ZonedDateTime.now().toLocalDate())) {
            CuFluxoCaixa cuFluxoCaixa = new CuFluxoCaixa();
            AtFluxoCaixaItem atFluxoCaixaItem = fatoServiceItemFluxo.carregarFato(projetoNatureza.getNaturePlan());
            cuFluxoCaixa.setAtFluxoCaixaItem(atFluxoCaixaItem);
            AtData atData = fatoServiceData.carregarFato(projetoNatureza.getDia());
            cuFluxoCaixa.setAtData(atData);
            AtRelatorio atRelatorio = fatoServiceRelatorio.carregarFato(umProjeto());
            cuFluxoCaixa.setAtRelatorio(atRelatorio);
            AtTipoMovim atTipoMovim = fatoServiceTipoMov.carregarFato("Previsto");
            cuFluxoCaixa.setAtTipoMovim(atTipoMovim);
            cuFluxoCaixa.setValor(new BigDecimal(projetoNatureza.getValorPrevisto()));
            crudCuFluxoCaixaRepository.save(cuFluxoCaixa);
        }


    }
    public void inserePrevisto(ProjetoNatureza projetoNatureza) {
        double valorRealizadoTotal = realizadoList
            .stream()
            .filter(naturezaResumo -> naturezaResumo.getIdNature() == projetoNatureza.getNaturePlan().getId().longValue()
                && naturezaResumo.getDia().toLocalDate().isBefore(projetoNatureza.getDia().toLocalDate()))
            .mapToDouble(NaturezaResumo::getValorTotal).sum();

        double valorPrevistoTotal = previstoList
            .stream()
            .filter(projetoNatureza1 -> projetoNatureza1.getNaturePlan().getId() == projetoNatureza.getNaturePlan().getId().longValue()
                && projetoNatureza1.getDia().toLocalDate().isBefore(projetoNatureza.getDia().toLocalDate()))
            .mapToDouble(ProjetoNatureza::getValorPrevisto)
            .sum();


        double saldo = Math.abs(valorPrevistoTotal) - Math.abs(valorRealizadoTotal);
        double valorInserir = 0 ;
        if (saldo > 0 && saldo <= Math.abs(projetoNatureza.getValorPrevisto().doubleValue()))
            valorInserir = Math.abs(projetoNatureza.getValorPrevisto().doubleValue()) -saldo;
        if (saldo >0 && saldo > Math.abs(projetoNatureza.getValorPrevisto().doubleValue()))
            valorInserir = Math.abs(projetoNatureza.getValorPrevisto().doubleValue());
        if (projetoNatureza.getValorPrevisto().doubleValue() < 0 && valorInserir != 0  )
            valorInserir = valorInserir * -1 ;


        if  (valorInserir != 0)        {
            CuFluxoCaixa cuFluxoCaixa = new CuFluxoCaixa();
            AtFluxoCaixaItem atFluxoCaixaItem = fatoServiceItemFluxo.carregarFato(projetoNatureza.getNaturePlan());
            cuFluxoCaixa.setAtFluxoCaixaItem(atFluxoCaixaItem);
            AtData atData = fatoServiceData.carregarFato(projetoNatureza.getDia());
            cuFluxoCaixa.setAtData(atData);
            AtRelatorio atRelatorio = fatoServiceRelatorio.carregarFato(umProjeto());
            cuFluxoCaixa.setAtRelatorio(atRelatorio);
            AtTipoMovim atTipoMovim = fatoServiceTipoMov.carregarFato("Previsto");
            cuFluxoCaixa.setAtTipoMovim(atTipoMovim);
            cuFluxoCaixa.setValor(new BigDecimal(valorInserir));
            crudCuFluxoCaixaRepository.save(cuFluxoCaixa);
        }

    }


    public static Projeto umProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNomeProjeto("FLUXO_CAIXA");
        return projeto;
    }


}
