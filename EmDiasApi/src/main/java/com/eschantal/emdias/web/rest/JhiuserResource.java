package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.domain.JhiUser;
import com.eschantal.emdias.repository.JhiuserRepository;
import com.eschantal.emdias.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link JhiUser}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class JhiuserResource {

    private final Logger log = LoggerFactory.getLogger(JhiuserResource.class);

    private static final String ENTITY_NAME = "emdiasJhiuser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JhiuserRepository jhiuserRepository;

    public JhiuserResource(JhiuserRepository jhiuserRepository) {
        this.jhiuserRepository = jhiuserRepository;
    }

    /**
     * {@code POST  /jhiusers} : Create a new jhiUser.
     *
     * @param jhiUser the jhiUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jhiUser, or with status {@code 400 (Bad Request)} if the jhiUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jhiusers")
    public ResponseEntity<JhiUser> createJhiuser(@RequestBody JhiUser jhiUser) throws URISyntaxException {
        log.debug("REST request to save JhiUser : {}", jhiUser);
        if (jhiUser.getId() != null) {
            throw new BadRequestAlertException("A new jhiUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JhiUser result = jhiuserRepository.save(jhiUser);
        return ResponseEntity.created(new URI("/api/jhiusers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jhiusers} : Updates an existing jhiUser.
     *
     * @param jhiUser the jhiUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jhiUser,
     * or with status {@code 400 (Bad Request)} if the jhiUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jhiUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jhiusers")
    public ResponseEntity<JhiUser> updateJhiuser(@RequestBody JhiUser jhiUser) throws URISyntaxException {
        log.debug("REST request to update JhiUser : {}", jhiUser);
        if (jhiUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JhiUser result = jhiuserRepository.save(jhiUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jhiUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /jhiusers} : get all the jhiusers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jhiusers in body.
     */
    @GetMapping("/jhiusers")
    public List<JhiUser> getAllJhiusers() {
        log.debug("REST request to get all Jhiusers");
        return jhiuserRepository.findAll();
    }

    /**
     * {@code GET  /jhiusers/:id} : get the "id" jhiuser.
     *
     * @param id the id of the jhiuser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jhiuser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jhiusers/{id}")
    public ResponseEntity<JhiUser> getJhiuser(@PathVariable Long id) {
        log.debug("REST request to get JhiUser : {}", id);
        Optional<JhiUser> jhiuser = jhiuserRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jhiuser);
    }

    /**
     * {@code DELETE  /jhiusers/:id} : delete the "id" jhiuser.
     *
     * @param id the id of the jhiuser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jhiusers/{id}")
    public ResponseEntity<Void> deleteJhiuser(@PathVariable Long id) {
        log.debug("REST request to delete JhiUser : {}", id);
        jhiuserRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
