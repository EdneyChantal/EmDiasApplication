package com.praticasolucoes.emdiasapiv2.web.rest;

import com.praticasolucoes.emdiasapiv2.domain.FiltroFluxoCaixa;
import com.praticasolucoes.emdiasapiv2.service.FluxoCaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import com.praticasolucoes.emdiasapiv2.domain.fluxocaixa.RelatorioFluxoCaixaDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fluxocaixa")
public class FluxoCaixaResource {
    @Autowired
    private FluxoCaixaService fluxoCaixaService;

    @PostMapping("/gerar")
    public RelatorioFluxoCaixaDTO gerarFluxoCaixa(@RequestBody FiltroFluxoCaixa filtroFluxoCaixa) {
       return  new RelatorioFluxoCaixaDTO(fluxoCaixaService.gerarFluxoCaixa(filtroFluxoCaixa.getWorkSpaceId(),
       filtroFluxoCaixa.getDataInicial(),
       filtroFluxoCaixa.getDataFinal()));
    }
}
