package com.eschantal.emdias.service;

import com.eschantal.emdias.domain.UserWorkSpace;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link UserWorkSpace}.
 */
public interface UserWorkSpaceService {

    /**
     * Save a userWorkSpace.
     *
     * @param userWorkSpace the entity to save.
     * @return the persisted entity.
     */
    UserWorkSpace save(UserWorkSpace userWorkSpace);

    /**
     * Get all the userWorkSpaces.
     *
     * @return the list of entities.
     */
    List<UserWorkSpace> findAll();


    /**
     * Get the "id" userWorkSpace.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserWorkSpace> findOne(Long id);

    /**
     * Delete the "id" userWorkSpace.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
