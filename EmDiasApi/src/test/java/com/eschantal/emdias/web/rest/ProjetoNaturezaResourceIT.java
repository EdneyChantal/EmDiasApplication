package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.EmdiasApp;
import com.eschantal.emdias.domain.ProjetoNatureza;
import com.eschantal.emdias.repository.ProjetoNaturezaRepository;
import com.eschantal.emdias.service.ProjetoNaturezaService;

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
 * Integration tests for the {@link ProjetoNaturezaResource} REST controller.
 */
@SpringBootTest(classes = EmdiasApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjetoNaturezaResourceIT {

    private static final Double DEFAULT_VALOR_PREVISTO = 1D;
    private static final Double UPDATED_VALOR_PREVISTO = 2D;

    private static final Double DEFAULT_VALOR_REALIZADO = 1D;
    private static final Double UPDATED_VALOR_REALIZADO = 2D;

    @Autowired
    private ProjetoNaturezaRepository projetoNaturezaRepository;

    @Autowired
    private ProjetoNaturezaService projetoNaturezaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjetoNaturezaMockMvc;

    private ProjetoNatureza projetoNatureza;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjetoNatureza createEntity(EntityManager em) {
        ProjetoNatureza projetoNatureza = new ProjetoNatureza()
            .valorPrevisto(DEFAULT_VALOR_PREVISTO)
            .valorRealizado(DEFAULT_VALOR_REALIZADO);
        return projetoNatureza;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjetoNatureza createUpdatedEntity(EntityManager em) {
        ProjetoNatureza projetoNatureza = new ProjetoNatureza()
            .valorPrevisto(UPDATED_VALOR_PREVISTO)
            .valorRealizado(UPDATED_VALOR_REALIZADO);
        return projetoNatureza;
    }

    @BeforeEach
    public void initTest() {
        projetoNatureza = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjetoNatureza() throws Exception {
        int databaseSizeBeforeCreate = projetoNaturezaRepository.findAll().size();
        // Create the ProjetoNatureza
        restProjetoNaturezaMockMvc.perform(post("/api/projeto-naturezas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projetoNatureza)))
            .andExpect(status().isCreated());

        // Validate the ProjetoNatureza in the database
        List<ProjetoNatureza> projetoNaturezaList = projetoNaturezaRepository.findAll();
        assertThat(projetoNaturezaList).hasSize(databaseSizeBeforeCreate + 1);
        ProjetoNatureza testProjetoNatureza = projetoNaturezaList.get(projetoNaturezaList.size() - 1);
        assertThat(testProjetoNatureza.getValorPrevisto()).isEqualTo(DEFAULT_VALOR_PREVISTO);
        assertThat(testProjetoNatureza.getValorRealizado()).isEqualTo(DEFAULT_VALOR_REALIZADO);
    }

    @Test
    @Transactional
    public void createProjetoNaturezaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projetoNaturezaRepository.findAll().size();

        // Create the ProjetoNatureza with an existing ID
        projetoNatureza.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjetoNaturezaMockMvc.perform(post("/api/projeto-naturezas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projetoNatureza)))
            .andExpect(status().isBadRequest());

        // Validate the ProjetoNatureza in the database
        List<ProjetoNatureza> projetoNaturezaList = projetoNaturezaRepository.findAll();
        assertThat(projetoNaturezaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProjetoNaturezas() throws Exception {
        // Initialize the database
        projetoNaturezaRepository.saveAndFlush(projetoNatureza);

        // Get all the projetoNaturezaList
        restProjetoNaturezaMockMvc.perform(get("/api/projeto-naturezas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projetoNatureza.getId().intValue())))
            .andExpect(jsonPath("$.[*].valorPrevisto").value(hasItem(DEFAULT_VALOR_PREVISTO.doubleValue())))
            .andExpect(jsonPath("$.[*].valorRealizado").value(hasItem(DEFAULT_VALOR_REALIZADO.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getProjetoNatureza() throws Exception {
        // Initialize the database
        projetoNaturezaRepository.saveAndFlush(projetoNatureza);

        // Get the projetoNatureza
        restProjetoNaturezaMockMvc.perform(get("/api/projeto-naturezas/{id}", projetoNatureza.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projetoNatureza.getId().intValue()))
            .andExpect(jsonPath("$.valorPrevisto").value(DEFAULT_VALOR_PREVISTO.doubleValue()))
            .andExpect(jsonPath("$.valorRealizado").value(DEFAULT_VALOR_REALIZADO.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProjetoNatureza() throws Exception {
        // Get the projetoNatureza
        restProjetoNaturezaMockMvc.perform(get("/api/projeto-naturezas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjetoNatureza() throws Exception {
        // Initialize the database
        projetoNaturezaService.save(projetoNatureza);

        int databaseSizeBeforeUpdate = projetoNaturezaRepository.findAll().size();

        // Update the projetoNatureza
        ProjetoNatureza updatedProjetoNatureza = projetoNaturezaRepository.findById(projetoNatureza.getId()).get();
        // Disconnect from session so that the updates on updatedProjetoNatureza are not directly saved in db
        em.detach(updatedProjetoNatureza);
        updatedProjetoNatureza
            .valorPrevisto(UPDATED_VALOR_PREVISTO)
            .valorRealizado(UPDATED_VALOR_REALIZADO);

        restProjetoNaturezaMockMvc.perform(put("/api/projeto-naturezas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjetoNatureza)))
            .andExpect(status().isOk());

        // Validate the ProjetoNatureza in the database
        List<ProjetoNatureza> projetoNaturezaList = projetoNaturezaRepository.findAll();
        assertThat(projetoNaturezaList).hasSize(databaseSizeBeforeUpdate);
        ProjetoNatureza testProjetoNatureza = projetoNaturezaList.get(projetoNaturezaList.size() - 1);
        assertThat(testProjetoNatureza.getValorPrevisto()).isEqualTo(UPDATED_VALOR_PREVISTO);
        assertThat(testProjetoNatureza.getValorRealizado()).isEqualTo(UPDATED_VALOR_REALIZADO);
    }

    @Test
    @Transactional
    public void updateNonExistingProjetoNatureza() throws Exception {
        int databaseSizeBeforeUpdate = projetoNaturezaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjetoNaturezaMockMvc.perform(put("/api/projeto-naturezas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projetoNatureza)))
            .andExpect(status().isBadRequest());

        // Validate the ProjetoNatureza in the database
        List<ProjetoNatureza> projetoNaturezaList = projetoNaturezaRepository.findAll();
        assertThat(projetoNaturezaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjetoNatureza() throws Exception {
        // Initialize the database
        projetoNaturezaService.save(projetoNatureza);

        int databaseSizeBeforeDelete = projetoNaturezaRepository.findAll().size();

        // Delete the projetoNatureza
        restProjetoNaturezaMockMvc.perform(delete("/api/projeto-naturezas/{id}", projetoNatureza.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjetoNatureza> projetoNaturezaList = projetoNaturezaRepository.findAll();
        assertThat(projetoNaturezaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
