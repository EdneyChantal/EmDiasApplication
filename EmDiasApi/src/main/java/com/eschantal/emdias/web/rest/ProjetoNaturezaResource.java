package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.domain.FiltroMovimentoBancario;
import com.eschantal.emdias.domain.ProjetoNatureza;
import com.eschantal.emdias.service.ProjetoNaturezaService;
import com.eschantal.emdias.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eschantal.emdias.domain.ProjetoNatureza}.
 */
@RestController
@RequestMapping("/api")
public class ProjetoNaturezaResource {

    private final Logger log = LoggerFactory.getLogger(ProjetoNaturezaResource.class);
    private static final String ENTITY_NAME = "emdiasProjetoNatureza";
    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    private final ProjetoNaturezaService projetoNaturezaService;

    public ProjetoNaturezaResource(ProjetoNaturezaService projetoNaturezaService) {
        this.projetoNaturezaService = projetoNaturezaService;
    }

    /**
     * {@code POST  /projeto-naturezas} : Create a new projetoNatureza.
     *
     * @param projetoNatureza the projetoNatureza to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projetoNatureza, or with status {@code 400 (Bad Request)} if the projetoNatureza has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/projeto-naturezas")
    public ResponseEntity<ProjetoNatureza> createProjetoNatureza(@RequestBody ProjetoNatureza projetoNatureza) throws URISyntaxException {
        log.debug("REST request to save ProjetoNatureza : {}", projetoNatureza);
        if (projetoNatureza.getId() != null) {
            throw new BadRequestAlertException("A new projetoNatureza cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjetoNatureza result = projetoNaturezaService.save(projetoNatureza);
        return ResponseEntity.created(new URI("/api/projeto-naturezas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /projeto-naturezas} : Updates an existing projetoNatureza.
     *
     * @param projetoNatureza the projetoNatureza to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projetoNatureza,
     * or with status {@code 400 (Bad Request)} if the projetoNatureza is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projetoNatureza couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/projeto-naturezas")
    public ResponseEntity<ProjetoNatureza> updateProjetoNatureza(@RequestBody ProjetoNatureza projetoNatureza) throws URISyntaxException {
        log.debug("REST request to update ProjetoNatureza : {}", projetoNatureza);
        if (projetoNatureza.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjetoNatureza result = projetoNaturezaService.save(projetoNatureza);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projetoNatureza.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /projeto-naturezas} : get all the projetoNaturezas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projetoNaturezas in body.
     */
    @GetMapping("/projeto-naturezas")
    public List<ProjetoNatureza> getAllProjetoNaturezas() {
        log.debug("REST request to get all ProjetoNaturezas");
        return projetoNaturezaService.findAll();
    }

    /**
     * {@code GET  /projeto-naturezas/:id} : get the "id" projetoNatureza.
     *
     * @param id the id of the projetoNatureza to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projetoNatureza, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/projeto-naturezas/{id}")
    public ResponseEntity<ProjetoNatureza> getProjetoNatureza(@PathVariable Long id) {
        log.debug("REST request to get ProjetoNatureza : {}", id);
        Optional<ProjetoNatureza> projetoNatureza = projetoNaturezaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projetoNatureza);
    }

    /**
     * {@code DELETE  /projeto-naturezas/:id} : delete the "id" projetoNatureza.
     *
     * @param id the id of the projetoNatureza to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/projeto-naturezas/{id}")
    public ResponseEntity<Void> deleteProjetoNatureza(@PathVariable Long id) {
        log.debug("REST request to delete ProjetoNatureza : {}", id);
        projetoNaturezaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/projeto-naturezas/buscarpeloprojetodatas")
    public List<ProjetoNatureza> buscarPeloProjetosDatas(@RequestBody FiltroMovimentoBancario filtroMovimentoBancario) {
       return projetoNaturezaService
           .buscarPorProjetoeIntervaloData(filtroMovimentoBancario.getProjetoId(),
               filtroMovimentoBancario.getDataInicial(),
               filtroMovimentoBancario.getDataFinal());
    }

}
