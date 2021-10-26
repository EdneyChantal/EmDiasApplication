package com.eschantal.emdias.service;

import com.eschantal.emdias.domain.WorkSpace;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link WorkSpace}.
 */
public interface WorkSpaceService {

    /**
     * Save a workSpace.
     *
     * @param workSpace the entity to save.
     * @return the persisted entity.
     */
    WorkSpace save(WorkSpace workSpace);

    /**
     * Get all the workSpaces.
     *
     * @return the list of entities.
     */
    List<WorkSpace> findAll();


    /**
     * Get the "id" workSpace.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkSpace> findOne(Long id);

    /**
     * Delete the "id" workSpace.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
