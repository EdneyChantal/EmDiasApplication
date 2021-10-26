package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.domain.WorkSpace;
import com.eschantal.emdias.service.WorkSpaceService;
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
 * REST controller for managing {@link com.eschantal.emdias.domain.WorkSpace}.
 */
@RestController
@RequestMapping("/api")
public class WorkSpaceResource {

    private final Logger log = LoggerFactory.getLogger(WorkSpaceResource.class);

    private static final String ENTITY_NAME = "emdiasWorkSpace";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkSpaceService workSpaceService;

    public WorkSpaceResource(WorkSpaceService workSpaceService) {
        this.workSpaceService = workSpaceService;
    }

    /**
     * {@code POST  /work-spaces} : Create a new workSpace.
     *
     * @param workSpace the workSpace to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workSpace, or with status {@code 400 (Bad Request)} if the workSpace has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/work-spaces")
    public ResponseEntity<WorkSpace> createWorkSpace(@RequestBody WorkSpace workSpace) throws URISyntaxException {
        log.debug("REST request to save WorkSpace : {}", workSpace);
        if (workSpace.getId() != null) {
            throw new BadRequestAlertException("A new workSpace cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkSpace result = workSpaceService.save(workSpace);
        return ResponseEntity.created(new URI("/api/work-spaces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /work-spaces} : Updates an existing workSpace.
     *
     * @param workSpace the workSpace to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workSpace,
     * or with status {@code 400 (Bad Request)} if the workSpace is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workSpace couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/work-spaces")
    public ResponseEntity<WorkSpace> updateWorkSpace(@RequestBody WorkSpace workSpace) throws URISyntaxException {
        log.debug("REST request to update WorkSpace : {}", workSpace);
        if (workSpace.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkSpace result = workSpaceService.save(workSpace);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workSpace.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /work-spaces} : get all the workSpaces.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workSpaces in body.
     */
    @GetMapping("/work-spaces")
    public List<WorkSpace> getAllWorkSpaces() {
        log.debug("REST request to get all WorkSpaces");
        return workSpaceService.findAll();
    }

    /**
     * {@code GET  /work-spaces/:id} : get the "id" workSpace.
     *
     * @param id the id of the workSpace to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workSpace, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/work-spaces/{id}")
    public ResponseEntity<WorkSpace> getWorkSpace(@PathVariable Long id) {
        log.debug("REST request to get WorkSpace : {}", id);
        Optional<WorkSpace> workSpace = workSpaceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workSpace);
    }

    /**
     * {@code DELETE  /work-spaces/:id} : delete the "id" workSpace.
     *
     * @param id the id of the workSpace to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/work-spaces/{id}")
    public ResponseEntity<Void> deleteWorkSpace(@PathVariable Long id) {
        log.debug("REST request to delete WorkSpace : {}", id);
        workSpaceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
