package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.domain.UserWorkSpace;
import com.eschantal.emdias.service.UserWorkSpaceService;
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
 * REST controller for managing {@link com.eschantal.emdias.domain.UserWorkSpace}.
 */
@RestController
@RequestMapping("/api")
public class UserWorkSpaceResource {

    private final Logger log = LoggerFactory.getLogger(UserWorkSpaceResource.class);

    private static final String ENTITY_NAME = "emdiasUserWorkSpace";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserWorkSpaceService userWorkSpaceService;

    public UserWorkSpaceResource(UserWorkSpaceService userWorkSpaceService) {
        this.userWorkSpaceService = userWorkSpaceService;
    }

    /**
     * {@code POST  /user-work-spaces} : Create a new userWorkSpace.
     *
     * @param userWorkSpace the userWorkSpace to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userWorkSpace, or with status {@code 400 (Bad Request)} if the userWorkSpace has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-work-spaces")
    public ResponseEntity<UserWorkSpace> createUserWorkSpace(@RequestBody UserWorkSpace userWorkSpace) throws URISyntaxException {
        log.debug("REST request to save UserWorkSpace : {}", userWorkSpace);
        if (userWorkSpace.getId() != null) {
            throw new BadRequestAlertException("A new userWorkSpace cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserWorkSpace result = userWorkSpaceService.save(userWorkSpace);
        return ResponseEntity.created(new URI("/api/user-work-spaces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-work-spaces} : Updates an existing userWorkSpace.
     *
     * @param userWorkSpace the userWorkSpace to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userWorkSpace,
     * or with status {@code 400 (Bad Request)} if the userWorkSpace is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userWorkSpace couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-work-spaces")
    public ResponseEntity<UserWorkSpace> updateUserWorkSpace(@RequestBody UserWorkSpace userWorkSpace) throws URISyntaxException {
        log.debug("REST request to update UserWorkSpace : {}", userWorkSpace);
        if (userWorkSpace.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserWorkSpace result = userWorkSpaceService.save(userWorkSpace);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userWorkSpace.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-work-spaces} : get all the userWorkSpaces.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userWorkSpaces in body.
     */
    @GetMapping("/user-work-spaces")
    public List<UserWorkSpace> getAllUserWorkSpaces() {
        log.debug("REST request to get all UserWorkSpaces");
        return userWorkSpaceService.findAll();
    }

    /**
     * {@code GET  /user-work-spaces/:id} : get the "id" userWorkSpace.
     *
     * @param id the id of the userWorkSpace to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userWorkSpace, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-work-spaces/{id}")
    public ResponseEntity<UserWorkSpace> getUserWorkSpace(@PathVariable Long id) {
        log.debug("REST request to get UserWorkSpace : {}", id);
        Optional<UserWorkSpace> userWorkSpace = userWorkSpaceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userWorkSpace);
    }

    /**
     * {@code DELETE  /user-work-spaces/:id} : delete the "id" userWorkSpace.
     *
     * @param id the id of the userWorkSpace to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-work-spaces/{id}")
    public ResponseEntity<Void> deleteUserWorkSpace(@PathVariable Long id) {
        log.debug("REST request to delete UserWorkSpace : {}", id);
        userWorkSpaceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
