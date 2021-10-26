package com.praticasolucoes.emdiasbi.web.rest;

import com.praticasolucoes.emdiasbi.domain.FiltroMovimentoBancario;
import com.praticasolucoes.emdiasbi.service.CargaCuboFluxoCaixa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class FluxoCaixaResource {

    private final CargaCuboFluxoCaixa cargaCuboFluxoCaixa;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private static final String ENTITY_NAME = "emdiasBiCuboFluxoCaixa";
    @Autowired
    public FluxoCaixaResource(CargaCuboFluxoCaixa cargaCuboFluxoCaixa) {
        this.cargaCuboFluxoCaixa = cargaCuboFluxoCaixa;
    }

    @PostMapping("/gerar")
    public ResponseEntity<String> geerar(@RequestBody FiltroMovimentoBancario filtroMovimentoBancario) {
        this.cargaCuboFluxoCaixa.gerar(filtroMovimentoBancario);
        return ok("Procedimento Realizado Com Sucesso");


    }
}
