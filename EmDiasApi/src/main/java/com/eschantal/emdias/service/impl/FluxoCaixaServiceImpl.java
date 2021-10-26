package com.eschantal.emdias.service.impl;

import com.eschantal.emdias.domain.MovementBank;
import com.eschantal.emdias.domain.dto.fluxocaixa.*;
import com.eschantal.emdias.repository.MovementBankRepository;
import com.eschantal.emdias.service.FluxoCaixaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class FluxoCaixaServiceImpl implements FluxoCaixaService {
    public static final String ID_SALDOINICIAL = "1";
    public static final String DESCRICAO_SALDO_INICIAL = "Saldo Inicial";
    public static final Double IN = 0D;
    public static final String ID_SALDOFINAL = "2";
    public static final String DESCRICAO_SALDO_FINAL = "Saldo Final";
    private final MovementBankRepository movementBankRepository;
    private RelatorioFluxoCaixa relatorioFluxoCaixa;

    public FluxoCaixaServiceImpl(MovementBankRepository movementBankRepository) {
        this.movementBankRepository = movementBankRepository;
    }

    @Override
    public RelatorioFluxoCaixa gerarFluxoCaixa(Long workSpaceId, ZonedDateTime dataInicial, ZonedDateTime dataFinal) {
        return this.gerarSaldoInicial(workSpaceId, dataInicial)
            .processarLinhas(workSpaceId, dataInicial, dataFinal)
            .processarSaldos()
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

    public FluxoCaixaService processarSaldos() {
        LinhaFluxoCaixa linhaSaldoInicial = this.relatorioFluxoCaixa.getLinhaFluxoCaixaSet().get(ID_SALDOINICIAL);
        LinhaFluxoCaixa linhaSaldoFinal = LinhaFluxoCaixaBuilder.umaLinhaFluxoCaixaDTO(ID_SALDOFINAL, DESCRICAO_SALDO_FINAL).build();
        this.relatorioFluxoCaixa
            .getSumAllDays()
            .forEach((day, valor) -> {
                if (valor != null ) {
                    CelulaFluxoCaixa tmp = linhaSaldoFinal.pegaDiaMaisProximo(day);
                    if (!linhaSaldoInicial.getMapDias().containsKey(day) &&
                        tmp != null ) {
                        linhaSaldoInicial.getMapDias().put(day,
                           tmp);
                    }
                    if (linhaSaldoInicial.getMapDias().containsKey(day))
                    linhaSaldoFinal.add(day, linhaSaldoInicial.getMapDias()
                        .get(day)
                        .getValor().doubleValue() + valor);
                }
            });
        this.getRelatorioFluxoCaixa().getLinhaFluxoCaixaSet().put(ID_SALDOINICIAL, linhaSaldoInicial);
        this.getRelatorioFluxoCaixa().getLinhaFluxoCaixaSet().put(ID_SALDOFINAL, linhaSaldoFinal);
        return this;
    }

    public void processarLista(List<MovementBank> movementBankList) {
        movementBankList
            .stream()
            .forEach(movementBank -> relatorioFluxoCaixa.addLinha(movementBank.getNaturePlan()
                , movementBank.getMovementDate(),
                movementBank.getMovementValue()));
    }

    public FluxoCaixaService gerarSaldoInicial(Long workSpaceId, ZonedDateTime dataInicial) {
        relatorioFluxoCaixa = RelatorioFluxoCaixaBuilder.umRelatorioFluxoCaixa("Fluxo de Caixa")
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
