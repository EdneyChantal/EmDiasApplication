package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.domain.JhiUser;
import com.eschantal.emdias.service.JhiUserService;
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
 * REST controller for managing {@link com.eschantal.emdias.domain.JhiUser}.
 */
@RestController
@RequestMapping("/api")
public class JhiUserResource {

    private final Logger log = LoggerFactory.getLogger(JhiUserResource.class);

    private static final String ENTITY_NAME = "emdiasJhiUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JhiUserService jhiUserService;

    public JhiUserResource(JhiUserService jhiUserService) {
        this.jhiUserService = jhiUserService;
    }

    /**
     * {@code POST  /jhi-users} : Create a new jhiUser.
     *
     * @param jhiUser the jhiUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jhiUser, or with status {@code 400 (Bad Request)} if the jhiUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jhi-users")
    public ResponseEntity<JhiUser> createJhiUser(@RequestBody JhiUser jhiUser) throws URISyntaxException {
        log.debug("REST request to save JhiUser : {}", jhiUser);
        if (jhiUser.getId() != null) {
            throw new BadRequestAlertException("A new jhiUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JhiUser result = jhiUserService.save(jhiUser);
        return ResponseEntity.created(new URI("/api/jhi-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jhi-users} : Updates an existing jhiUser.
     *
     * @param jhiUser the jhiUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jhiUser,
     * or with status {@code 400 (Bad Request)} if the jhiUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jhiUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jhi-users")
    public ResponseEntity<JhiUser> updateJhiUser(@RequestBody JhiUser jhiUser) throws URISyntaxException {
        log.debug("REST request to update JhiUser : {}", jhiUser);
        if (jhiUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JhiUser result = jhiUserService.save(jhiUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jhiUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /jhi-users} : get all the jhiUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jhiUsers in body.
     */
    @GetMapping("/jhi-users")
    public List<JhiUser> getAllJhiUsers() {
        log.debug("REST request to get all JhiUsers");
        return jhiUserService.findAll();
    }

    /**
     * {@code GET  /jhi-users/:id} : get the "id" jhiUser.
     *
     * @param id the id of the jhiUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jhiUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jhi-users/{id}")
    public ResponseEntity<JhiUser> getJhiUser(@PathVariable Long id) {
        log.debug("REST request to get JhiUser : {}", id);
        Optional<JhiUser> jhiUser = jhiUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jhiUser);
    }

    /**
     * {@code DELETE  /jhi-users/:id} : delete the "id" jhiUser.
     *
     * @param id the id of the jhiUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jhi-users/{id}")
    public ResponseEntity<Void> deleteJhiUser(@PathVariable Long id) {
        log.debug("REST request to delete JhiUser : {}", id);
        jhiUserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
