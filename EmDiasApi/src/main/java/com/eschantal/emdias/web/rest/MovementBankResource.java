package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.domain.FiltroMovimentoBancario;
import com.eschantal.emdias.domain.MovementBank;
import com.eschantal.emdias.domain.NaturezaResumo;
import com.eschantal.emdias.domain.Resposta;
import com.eschantal.emdias.service.MovementBankService;
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

import static org.springframework.http.ResponseEntity.ok;

/**
 * REST controller for managing {@link com.eschantal.emdias.domain.MovementBank}.
 */
@RestController
@RequestMapping("/api")
public class MovementBankResource {

    private final Logger log = LoggerFactory.getLogger(MovementBankResource.class);

    private static final String ENTITY_NAME = "emdiasMovementBank";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MovementBankService movementBankService;

    public MovementBankResource(MovementBankService movementBankService) {
        this.movementBankService = movementBankService;
    }

    /**
     * {@code POST  /movement-banks} : Create a new movementBank.
     *
     * @param movementBank the movementBank to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new movementBank, or with status {@code 400 (Bad Request)} if the movementBank has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/movement-banks")
    public ResponseEntity<MovementBank> createMovementBank(@RequestBody MovementBank movementBank) throws URISyntaxException {
        log.debug("REST request to save MovementBank : {}", movementBank);
        if (movementBank.getId() != null) {
            throw new BadRequestAlertException("A new movementBank cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovementBank result = movementBankService.save(movementBank);
        return ResponseEntity.created(new URI("/api/movement-banks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /movement-banks} : Updates an existing movementBank.
     *
     * @param movementBank the movementBank to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated movementBank,
     * or with status {@code 400 (Bad Request)} if the movementBank is not valid,
     * or with status {@code 500 (Internal Server Error)} if the movementBank couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/movement-banks")
    public ResponseEntity<MovementBank> updateMovementBank(@RequestBody MovementBank movementBank) throws URISyntaxException {
        log.debug("REST request to update MovementBank : {}", movementBank);
        if (movementBank.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MovementBank result = movementBankService.save(movementBank);
        return ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, movementBank.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /movement-banks} : get all the movementBanks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of movementBanks in body.
     */
    @GetMapping("/movement-banks")
    public List<MovementBank> getAllMovementBanks() {
        log.debug("REST request to get all MovementBanks");
        return movementBankService.findAll();
    }

    @PutMapping("/movement-banks-filtro")
    public List<MovementBank> getMovementBanksFiltro(@RequestBody FiltroMovimentoBancario filtroMovimentoBancario) {
        log.debug("REST request to get all MovementBanks");
        return this.movementBankService.findByFiltro(filtroMovimentoBancario);
    }
    @PutMapping("/movement-banks-filtro-natureza")
    public List<MovementBank> getMovementBanksFiltroNatureza(@RequestBody FiltroMovimentoBancario filtroMovimentoBancario) {
        log.debug("REST request to get all MovementBanks");
        return this.movementBankService.findByFiltroNatureza(filtroMovimentoBancario);
    }


    @PutMapping("/total-ate-a-data")
    public ResponseEntity<Double> getTotalAteAData(@RequestBody FiltroMovimentoBancario filtroMovimentoBancario) {
        log.debug("REST request to get all MovementBanks");
        Double result = this.movementBankService.totalMovimentoAteAData(filtroMovimentoBancario);
        return ok()
            .body(result);
    }
    @PostMapping("/total-natureza")
    public List<NaturezaResumo> getTotalPorNatureza(@RequestBody FiltroMovimentoBancario filtroMovimentoBancario) {
        log.debug("REST request to get all MovementBanks");

        return this.movementBankService.totalPorNatureza(filtroMovimentoBancario);
    }
    @PutMapping("/atualizaprojetopelorealizado")
    public ResponseEntity<Resposta> atualizaProjetoPeloRealizado(@RequestBody FiltroMovimentoBancario filtroMovimentoBancario) {
        log.debug("REST request to get all MovementBanks");

        this.movementBankService.atualizaProjetoPeloRealizado(filtroMovimentoBancario);

        return ok(Resposta.OPERACAO_REALIZADA_COM_SUCESSO());
    }
    /**
     * {@code GET  /movement-banks/:id} : get the "id" movementBank.
     *
     * @param id the id of the movementBank to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the movementBank, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/movement-banks/{id}")
    public ResponseEntity<MovementBank> getMovementBank(@PathVariable Long id) {
        log.debug("REST request to get MovementBank : {}", id);
        Optional<MovementBank> movementBank = movementBankService.findOne(id);
        return ResponseUtil.wrapOrNotFound(movementBank);
    }

    /**
     * {@code DELETE  /movement-banks/:id} : delete the "id" movementBank.
     *
     * @param id the id of the movementBank to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/movement-banks/{id}")
    public ResponseEntity<Void> deleteMovementBank(@PathVariable Long id) {
        log.debug("REST request to delete MovementBank : {}", id);
        movementBankService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
