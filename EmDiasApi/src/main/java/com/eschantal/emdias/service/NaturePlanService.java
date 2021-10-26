package com.eschantal.emdias.service;

import com.eschantal.emdias.domain.NaturePlan;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link NaturePlan}.
 */
public interface NaturePlanService {

    /**
     * Save a naturePlan.
     *
     * @param naturePlan the entity to save.
     * @return the persisted entity.
     */
    NaturePlan save(NaturePlan naturePlan);

    /**
     * Get all the naturePlans.
     *
     * @return the list of entities.
     */
    List<NaturePlan> findAll();


    /**
     * Get the "id" naturePlan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NaturePlan> findOne(Long id);

    /**
     * Delete the "id" naturePlan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
