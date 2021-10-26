package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.EmdiasApp;
import com.eschantal.emdias.domain.AccountBank;
import com.eschantal.emdias.repository.AccountBankRepository;
import com.eschantal.emdias.service.AccountBankService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AccountBankResource} REST controller.
 */
@SpringBootTest(classes = EmdiasApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AccountBankResourceIT {

    private static final String DEFAULT_NOME_DA_CONTA_BANCARIA = "AAAAAAAAAA";
    private static final String UPDATED_NOME_DA_CONTA_BANCARIA = "BBBBBBBBBB";

    private static final Double DEFAULT_VALOR_INICIAL = 1D;
    private static final Double UPDATED_VALOR_INICIAL = 2D;

    private static final String DEFAULT_COD_CONTA_EXTRATO = "AAAAAAAAAA";
    private static final String UPDATED_COD_CONTA_EXTRATO = "BBBBBBBBBB";

    private static final String DEFAULT_DIGITO = "AAAAAAAAAA";
    private static final String UPDATED_DIGITO = "BBBBBBBBBB";

    @Autowired
    private AccountBankRepository accountBankRepository;

    @Autowired
    private AccountBankService accountBankService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAccountBankMockMvc;

    private AccountBank accountBank;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountBank createEntity(EntityManager em) {
        AccountBank accountBank = new AccountBank()
            .nomeDaContaBancaria(DEFAULT_NOME_DA_CONTA_BANCARIA)
            .valorInicial(DEFAULT_VALOR_INICIAL)
            .codContaExtrato(DEFAULT_COD_CONTA_EXTRATO)
            .digito(DEFAULT_DIGITO);
        return accountBank;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountBank createUpdatedEntity(EntityManager em) {
        AccountBank accountBank = new AccountBank()
            .nomeDaContaBancaria(UPDATED_NOME_DA_CONTA_BANCARIA)
            .valorInicial(UPDATED_VALOR_INICIAL)
            .codContaExtrato(UPDATED_COD_CONTA_EXTRATO)
            .digito(UPDATED_DIGITO);
        return accountBank;
    }

    @BeforeEach
    public void initTest() {
        accountBank = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountBank() throws Exception {
        int databaseSizeBeforeCreate = accountBankRepository.findAll().size();
        // Create the AccountBank
        restAccountBankMockMvc.perform(post("/api/account-banks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountBank)))
            .andExpect(status().isCreated());

        // Validate the AccountBank in the database
        List<AccountBank> accountBankList = accountBankRepository.findAll();
        assertThat(accountBankList).hasSize(databaseSizeBeforeCreate + 1);
        AccountBank testAccountBank = accountBankList.get(accountBankList.size() - 1);
        assertThat(testAccountBank.getNomeDaContaBancaria()).isEqualTo(DEFAULT_NOME_DA_CONTA_BANCARIA);
        assertThat(testAccountBank.getValorInicial()).isEqualTo(DEFAULT_VALOR_INICIAL);
        assertThat(testAccountBank.getCodContaExtrato()).isEqualTo(DEFAULT_COD_CONTA_EXTRATO);
        assertThat(testAccountBank.getDigito()).isEqualTo(DEFAULT_DIGITO);
    }

    @Test
    @Transactional
    public void createAccountBankWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountBankRepository.findAll().size();

        // Create the AccountBank with an existing ID
        accountBank.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountBankMockMvc.perform(post("/api/account-banks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountBank)))
            .andExpect(status().isBadRequest());

        // Validate the AccountBank in the database
        List<AccountBank> accountBankList = accountBankRepository.findAll();
        assertThat(accountBankList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountBanks() throws Exception {
        // Initialize the database
        accountBankRepository.saveAndFlush(accountBank);

        // Get all the accountBankList
        restAccountBankMockMvc.perform(get("/api/account-banks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountBank.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomeDaContaBancaria").value(hasItem(DEFAULT_NOME_DA_CONTA_BANCARIA)))
            .andExpect(jsonPath("$.[*].valorInicial").value(hasItem(DEFAULT_VALOR_INICIAL.doubleValue())))
            .andExpect(jsonPath("$.[*].codContaExtrato").value(hasItem(DEFAULT_COD_CONTA_EXTRATO)))
            .andExpect(jsonPath("$.[*].digito").value(hasItem(DEFAULT_DIGITO)));
    }
    
    @Test
    @Transactional
    public void getAccountBank() throws Exception {
        // Initialize the database
        accountBankRepository.saveAndFlush(accountBank);

        // Get the accountBank
        restAccountBankMockMvc.perform(get("/api/account-banks/{id}", accountBank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accountBank.getId().intValue()))
            .andExpect(jsonPath("$.nomeDaContaBancaria").value(DEFAULT_NOME_DA_CONTA_BANCARIA))
            .andExpect(jsonPath("$.valorInicial").value(DEFAULT_VALOR_INICIAL.doubleValue()))
            .andExpect(jsonPath("$.codContaExtrato").value(DEFAULT_COD_CONTA_EXTRATO))
            .andExpect(jsonPath("$.digito").value(DEFAULT_DIGITO));
    }
    @Test
    @Transactional
    public void getNonExistingAccountBank() throws Exception {
        // Get the accountBank
        restAccountBankMockMvc.perform(get("/api/account-banks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountBank() throws Exception {
        // Initialize the database
        accountBankService.save(accountBank);

        int databaseSizeBeforeUpdate = accountBankRepository.findAll().size();

        // Update the accountBank
        AccountBank updatedAccountBank = accountBankRepository.findById(accountBank.getId()).get();
        // Disconnect from session so that the updates on updatedAccountBank are not directly saved in db
        em.detach(updatedAccountBank);
        updatedAccountBank
            .nomeDaContaBancaria(UPDATED_NOME_DA_CONTA_BANCARIA)
            .valorInicial(UPDATED_VALOR_INICIAL)
            .codContaExtrato(UPDATED_COD_CONTA_EXTRATO)
            .digito(UPDATED_DIGITO);

        restAccountBankMockMvc.perform(put("/api/account-banks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAccountBank)))
            .andExpect(status().isOk());

        // Validate the AccountBank in the database
        List<AccountBank> accountBankList = accountBankRepository.findAll();
        assertThat(accountBankList).hasSize(databaseSizeBeforeUpdate);
        AccountBank testAccountBank = accountBankList.get(accountBankList.size() - 1);
        assertThat(testAccountBank.getNomeDaContaBancaria()).isEqualTo(UPDATED_NOME_DA_CONTA_BANCARIA);
        assertThat(testAccountBank.getValorInicial()).isEqualTo(UPDATED_VALOR_INICIAL);
        assertThat(testAccountBank.getCodContaExtrato()).isEqualTo(UPDATED_COD_CONTA_EXTRATO);
        assertThat(testAccountBank.getDigito()).isEqualTo(UPDATED_DIGITO);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountBank() throws Exception {
        int databaseSizeBeforeUpdate = accountBankRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountBankMockMvc.perform(put("/api/account-banks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountBank)))
            .andExpect(status().isBadRequest());

        // Validate the AccountBank in the database
        List<AccountBank> accountBankList = accountBankRepository.findAll();
        assertThat(accountBankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountBank() throws Exception {
        // Initialize the database
        accountBankService.save(accountBank);

        int databaseSizeBeforeDelete = accountBankRepository.findAll().size();

        // Delete the accountBank
        restAccountBankMockMvc.perform(delete("/api/account-banks/{id}", accountBank.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountBank> accountBankList = accountBankRepository.findAll();
        assertThat(accountBankList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
