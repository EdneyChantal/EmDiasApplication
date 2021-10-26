package com.eschantal.emdias.service;

import com.eschantal.emdias.domain.Projeto;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Projeto}.
 */
public interface ProjetoService {

    /**
     * Save a projeto.
     *
     * @param projeto the entity to save.
     * @return the persisted entity.
     */
    Projeto save(Projeto projeto);

    /**
     * Get all the projetos.
     *
     * @return the list of entities.
     */
    List<Projeto> findAll();


    /**
     * Get the "id" projeto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Projeto> findOne(Long id);

    /**
     * Delete the "id" projeto.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
