package com.eschantal.emdias.service;

import com.eschantal.emdias.domain.ProjetoNatureza;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ProjetoNatureza}.
 */
public interface ProjetoNaturezaService {

    /**
     * Save a projetoNatureza.
     *
     * @param projetoNatureza the entity to save.
     * @return the persisted entity.
     */
    ProjetoNatureza save(ProjetoNatureza projetoNatureza);

    /**
     * Get all the projetoNaturezas.
     *
     * @return the list of entities.
     */
    List<ProjetoNatureza> findAll();


    /**
     * Get the "id" projetoNatureza.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjetoNatureza> findOne(Long id);

    /**
     * Delete the "id" projetoNatureza.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ProjetoNatureza> buscarPorProjetoeIntervaloData(Long ProjetoId , ZonedDateTime dataInicial , ZonedDateTime dataFinal);
}
