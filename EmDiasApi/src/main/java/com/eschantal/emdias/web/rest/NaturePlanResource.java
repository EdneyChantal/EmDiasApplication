package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.domain.NaturePlan;
import com.eschantal.emdias.service.NaturePlanService;
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
 * REST controller for managing {@link com.eschantal.emdias.domain.NaturePlan}.
 */
@RestController
@RequestMapping("/api")
public class NaturePlanResource {

    private final Logger log = LoggerFactory.getLogger(NaturePlanResource.class);

    private static final String ENTITY_NAME = "emdiasNaturePlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NaturePlanService naturePlanService;

    public NaturePlanResource(NaturePlanService naturePlanService) {
        this.naturePlanService = naturePlanService;
    }

    /**
     * {@code POST  /nature-plans} : Create a new naturePlan.
     *
     * @param naturePlan the naturePlan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new naturePlan, or with status {@code 400 (Bad Request)} if the naturePlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nature-plans")
    public ResponseEntity<NaturePlan> createNaturePlan(@RequestBody NaturePlan naturePlan) throws URISyntaxException {
        log.debug("REST request to save NaturePlan : {}", naturePlan);
        if (naturePlan.getId() != null) {
            throw new BadRequestAlertException("A new naturePlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NaturePlan result = naturePlanService.save(naturePlan);
        return ResponseEntity.created(new URI("/api/nature-plans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nature-plans} : Updates an existing naturePlan.
     *
     * @param naturePlan the naturePlan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated naturePlan,
     * or with status {@code 400 (Bad Request)} if the naturePlan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the naturePlan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nature-plans")
    public ResponseEntity<NaturePlan> updateNaturePlan(@RequestBody NaturePlan naturePlan) throws URISyntaxException {
        log.debug("REST request to update NaturePlan : {}", naturePlan);
        if (naturePlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NaturePlan result = naturePlanService.save(naturePlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, naturePlan.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nature-plans} : get all the naturePlans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of naturePlans in body.
     */
    @GetMapping("/nature-plans")
    public List<NaturePlan> getAllNaturePlans() {
        log.debug("REST request to get all NaturePlans");
        return naturePlanService.findAll();
    }

    /**
     * {@code GET  /nature-plans/:id} : get the "id" naturePlan.
     *
     * @param id the id of the naturePlan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the naturePlan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nature-plans/{id}")
    public ResponseEntity<NaturePlan> getNaturePlan(@PathVariable Long id) {
        log.debug("REST request to get NaturePlan : {}", id);
        Optional<NaturePlan> naturePlan = naturePlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(naturePlan);
    }

    @GetMapping("/nature-plans/workspace/{id}")
    public List<NaturePlan> getNaturePlansfromWorkSpace(@PathVariable Long id) {
        log.debug("REST request to get NaturePlans with workspace: {}", id);
        return naturePlanService.buscarTodosdoWorkspace(id);

    }



    /**
     * {@code DELETE  /nature-plans/:id} : delete the "id" naturePlan.
     *
     * @param id the id of the naturePlan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nature-plans/{id}")
    public ResponseEntity<Void> deleteNaturePlan(@PathVariable Long id) {
        log.debug("REST request to delete NaturePlan : {}", id);
        naturePlanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
