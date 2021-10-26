package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.domain.AccountBank;
import com.eschantal.emdias.service.AccountBankService;
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
 * REST controller for managing {@link com.eschantal.emdias.domain.AccountBank}.
 */
@RestController
@RequestMapping("/api")
public class AccountBankResource {

    private final Logger log = LoggerFactory.getLogger(AccountBankResource.class);

    private static final String ENTITY_NAME = "emdiasAccountBank";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountBankService accountBankService;

    public AccountBankResource(AccountBankService accountBankService) {
        this.accountBankService = accountBankService;
    }

    /**
     * {@code POST  /account-banks} : Create a new accountBank.
     *
     * @param accountBank the accountBank to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountBank, or with status {@code 400 (Bad Request)} if the accountBank has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-banks")
    public ResponseEntity<AccountBank> createAccountBank(@RequestBody AccountBank accountBank) throws URISyntaxException {
        log.debug("REST request to save AccountBank : {}", accountBank);
        if (accountBank.getId() != null) {
            throw new BadRequestAlertException("A new accountBank cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountBank result = accountBankService.save(accountBank);
        return ResponseEntity.created(new URI("/api/account-banks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-banks} : Updates an existing accountBank.
     *
     * @param accountBank the accountBank to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountBank,
     * or with status {@code 400 (Bad Request)} if the accountBank is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountBank couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-banks")
    public ResponseEntity<AccountBank> updateAccountBank(@RequestBody AccountBank accountBank) throws URISyntaxException {
        log.debug("REST request to update AccountBank : {}", accountBank);
        if (accountBank.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountBank result = accountBankService.save(accountBank);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountBank.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-banks} : get all the accountBanks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountBanks in body.
     */
    @GetMapping("/account-banks")
    public List<AccountBank> getAllAccountBanks() {
        log.debug("REST request to get all AccountBanks");
        return accountBankService.findAll();
    }

    /**
     * {@code GET  /account-banks/:id} : get the "id" accountBank.
     *
     * @param id the id of the accountBank to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountBank, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-banks/{id}")
    public ResponseEntity<AccountBank> getAccountBank(@PathVariable Long id) {
        log.debug("REST request to get AccountBank : {}", id);
        Optional<AccountBank> accountBank = accountBankService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountBank);
    }

    /**
     * {@code DELETE  /account-banks/:id} : delete the "id" accountBank.
     *
     * @param id the id of the accountBank to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-banks/{id}")
    public ResponseEntity<Void> deleteAccountBank(@PathVariable Long id) {
        log.debug("REST request to delete AccountBank : {}", id);
        accountBankService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
