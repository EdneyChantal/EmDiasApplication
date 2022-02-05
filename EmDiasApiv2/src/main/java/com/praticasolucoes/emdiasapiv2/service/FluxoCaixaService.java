package com.praticasolucoes.emdiasapiv2.service;



import com.praticasolucoes.emdiasapiv2.domain.fluxocaixa.RelatorioFluxoCaixa;

import java.time.ZonedDateTime;

public interface FluxoCaixaService {
    RelatorioFluxoCaixa gerarFluxoCaixa(Long WorkSpaceId , ZonedDateTime dataInicial , ZonedDateTime dataFinal);
    FluxoCaixaService processarLinhas(Long workspaceId, ZonedDateTime dataInicial, ZonedDateTime dataFinal);
    RelatorioFluxoCaixa getRelatorioFluxoCaixa();
    FluxoCaixaService processarSaldos(ZonedDateTime dataInicial,ZonedDateTime dataFinal);
}
