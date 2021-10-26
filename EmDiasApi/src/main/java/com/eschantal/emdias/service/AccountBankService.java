package com.eschantal.emdias.service;

import com.eschantal.emdias.domain.AccountBank;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link AccountBank}.
 */
public interface AccountBankService {

    /**
     * Save a accountBank.
     *
     * @param accountBank the entity to save.
     * @return the persisted entity.
     */
    AccountBank save(AccountBank accountBank);

    /**
     * Get all the accountBanks.
     *
     * @return the list of entities.
     */
    List<AccountBank> findAll();


    /**
     * Get the "id" accountBank.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountBank> findOne(Long id);

    /**
     * Delete the "id" accountBank.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
