package com.eschantal.emdias.service;

import com.eschantal.emdias.domain.JhiUser;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link JhiUser}.
 */
public interface JhiUserService {

    /**
     * Save a jhiUser.
     *
     * @param jhiUser the entity to save.
     * @return the persisted entity.
     */
    JhiUser save(JhiUser jhiUser);

    /**
     * Get all the jhiUsers.
     *
     * @return the list of entities.
     */
    List<JhiUser> findAll();


    /**
     * Get the "id" jhiUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JhiUser> findOne(Long id);

    /**
     * Delete the "id" jhiUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
