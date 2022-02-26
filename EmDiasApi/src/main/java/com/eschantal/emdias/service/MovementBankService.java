package com.eschantal.emdias.service;

import com.eschantal.emdias.domain.*;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link MovementBank}.
 */
public interface MovementBankService {

    /**
     * Save a movementBank.
     *
     * @param movementBank the entity to save.
     * @return the persisted entity.
     */
    MovementBank save(MovementBank movementBank);

    /**
     * Get all the movementBanks.
     *
     * @return the list of entities.
     */
    List<MovementBank> findAll();
    List<MovementBank> findByFiltro(FiltroMovimentoBancario filtroMovimentoBancario);
    Double totalMovimentoAteAData(FiltroMovimentoBancario filtroMovimentoBancario);
    List<NaturezaResumo> totalPorNatureza(FiltroMovimentoBancario filtroMovimentoBancario);
    List<MovementBank> findByFiltroNatureza(FiltroMovimentoBancario filtroMovimentoBancario);
    /**
     * Get the "id" movementBank.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MovementBank> findOne(Long id);

    /**
     * Delete the "id" movementBank.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);


    List<NaturezaResumo> pegaMovimentosDaNatureza (NaturePlan naturePlan, FiltroMovimentoBancario filtroMovimentoBancario);
    boolean filtraNaturezaResumo(NaturezaResumo naturezaResumo) ;
    void atualizaProjetoNaturezaPeloNaturezaResumo(NaturezaResumo naturezaResumo,Projeto projeto);
    void atualizaProjetoPeloRealizado(FiltroMovimentoBancario filtroMovimentoBancario);
    Double pegarSaldo(Long id);
}
