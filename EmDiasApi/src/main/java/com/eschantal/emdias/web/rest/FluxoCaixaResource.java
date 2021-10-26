package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.domain.FiltroFluxoCaixa;
import com.eschantal.emdias.domain.MovementBank;
import com.eschantal.emdias.domain.dto.fluxocaixa.RelatorioFluxoCaixa;
import com.eschantal.emdias.domain.dto.fluxocaixa.RelatorioFluxoCaixaDTO;
import com.eschantal.emdias.service.FluxoCaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
