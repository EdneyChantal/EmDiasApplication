package com.praticasolucoes.emdiasapiv2.service.impl;


import com.praticasolucoes.emdiasapiv2.domain.MovementBank;
import com.praticasolucoes.emdiasapiv2.domain.fluxocaixa.LinhaFluxoCaixa;
import com.praticasolucoes.emdiasapiv2.domain.fluxocaixa.LinhaFluxoCaixaBuilder;
import com.praticasolucoes.emdiasapiv2.domain.fluxocaixa.RelatorioFluxoCaixa;
import com.praticasolucoes.emdiasapiv2.domain.fluxocaixa.RelatorioFluxoCaixaBuilder;
import com.praticasolucoes.emdiasapiv2.repository.MovementBankRepository;
import com.praticasolucoes.emdiasapiv2.service.FluxoCaixaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static com.praticasolucoes.emdiasapiv2.domain.fluxocaixa.RelatorioFluxoCaixa.*;

@Service
public class FluxoCaixaServiceImpl implements FluxoCaixaService {
    public static final Double IN = 0D;
    private final MovementBankRepository movementBankRepository;
    private RelatorioFluxoCaixa relatorioFluxoCaixa;

    public FluxoCaixaServiceImpl(MovementBankRepository movementBankRepository) {
        this.movementBankRepository = movementBankRepository;
    }
    @Override
    public RelatorioFluxoCaixa gerarFluxoCaixa(Long workSpaceId, ZonedDateTime dataInicial, ZonedDateTime dataFinal) {
        return this.gerarSaldoInicial(workSpaceId, dataInicial,dataFinal)
            .processarLinhas(workSpaceId, dataInicial, dataFinal)
            .processarSaldos(dataInicial,dataFinal)
            .getRelatorioFluxoCaixa();
    }
    @Override
    public RelatorioFluxoCaixa getRelatorioFluxoCaixa() {
        return relatorioFluxoCaixa;
    }
    @Override
    public FluxoCaixaService processarLinhas(Long workspaceId, ZonedDateTime dataInicial, ZonedDateTime dataFinal) {
        this.movementBankRepository.findByWorkspaceAndIntervaloData(workspaceId, dataInicial, dataFinal)
            .ifPresent(this::processarLista);
        return this;
    }
    public void gerarSaldosDia(LocalDate pday , Double valorTotalDia) {
        BigDecimal valorSaldoInicial = this.relatorioFluxoCaixa.pegarValorDiaConta(ID_SALDOINICIAL,pday);


        this.relatorioFluxoCaixa.getLinhaFluxoCaixaSet().get(ID_SALDOFINAL)
            .put(pday,valorSaldoInicial.add(new BigDecimal(valorTotalDia)));
        this.relatorioFluxoCaixa.getLinhaFluxoCaixaSet().get(ID_SALDOINICIAL)
            .put(pday.plusDays(1),valorSaldoInicial.add(new BigDecimal(valorTotalDia)));
   }
   public FluxoCaixaService processarSaldos(ZonedDateTime dataInicial,ZonedDateTime dataFinal) {
        Map<LocalDate, Double> mtotalDias =  this.relatorioFluxoCaixa.getSumAllDays();
        LocalDate day = dataInicial.toLocalDate();
        LinhaFluxoCaixa linhaSaldoFinal = LinhaFluxoCaixaBuilder.umaLinhaFluxoCaixaDTO(ID_SALDOFINAL, DESCRICAO_SALDO_FINAL).build();
        this.getRelatorioFluxoCaixa().getLinhaFluxoCaixaSet().put(ID_SALDOFINAL, linhaSaldoFinal);
        while (day.isBefore(dataFinal.toLocalDate()) || day.isEqual(dataFinal.toLocalDate())) {
            Double valor = new Double(0);
            if (mtotalDias.containsKey(day))  valor = mtotalDias.get(day);
            gerarSaldosDia(day,valor);
           day = day.plusDays(1);
        }
        return this;
    }
    public void processarLista(List<MovementBank> movementBankList) {
        movementBankList
            .stream()
            .forEach(movementBank -> relatorioFluxoCaixa.addLinha(movementBank.getNaturePlan()
                , movementBank.getMovementDate(),
                movementBank.getMovementValue()));
    }
    public FluxoCaixaService gerarSaldoInicial(Long workSpaceId, ZonedDateTime dataInicial,ZonedDateTime dataFinal) {
        relatorioFluxoCaixa = RelatorioFluxoCaixaBuilder.umRelatorioFluxoCaixa("Fluxo de Caixa",dataInicial.toLocalDate(),dataFinal.toLocalDate())
            .build();
        relatorioFluxoCaixa.getLinhaFluxoCaixaSet()
            .put(ID_SALDOINICIAL, LinhaFluxoCaixaBuilder.umaLinhaFluxoCaixaDTO(ID_SALDOINICIAL, DESCRICAO_SALDO_INICIAL)
                .withDia(dataInicial,
                    BigDecimal.valueOf(
                        movementBankRepository.totalGeralAteAData(workSpaceId, dataInicial.minusDays(1))
                            .orElse(IN)
                    )
                )
                .build());
        return this;
    }
}
