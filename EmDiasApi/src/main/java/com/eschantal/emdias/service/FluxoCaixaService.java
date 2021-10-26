package com.eschantal.emdias.service;

import com.eschantal.emdias.domain.dto.fluxocaixa.RelatorioFluxoCaixa;

import java.time.ZonedDateTime;

public interface FluxoCaixaService {
    RelatorioFluxoCaixa gerarFluxoCaixa(Long WorkSpaceId ,ZonedDateTime dataInicial , ZonedDateTime dataFinal);
    FluxoCaixaService processarLinhas(Long workspaceId, ZonedDateTime dataInicial, ZonedDateTime dataFinal);
    RelatorioFluxoCaixa getRelatorioFluxoCaixa();
    FluxoCaixaService processarSaldos();
}
