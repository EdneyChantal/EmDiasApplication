package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.EmdiasApp;
import com.eschantal.emdias.domain.NaturePlan;
import com.eschantal.emdias.domain.TypeNaturePlan;
import com.eschantal.emdias.repository.NaturePlanRepository;
import com.eschantal.emdias.service.NaturePlanService;

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
 * Integration tests for the {@link NaturePlanResource} REST controller.
 */
@SpringBootTest(classes = EmdiasApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NaturePlanResourceIT {

    private static final String DEFAULT_DESC_NATURE_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_DESC_NATURE_PLAN = "BBBBBBBBBB";


    private static final TypeNaturePlan DEFAULT_TYPE_NATURE_PLAN = TypeNaturePlan.R;
    private static final TypeNaturePlan UPDATED_TYPE_NATURE_PLAN = TypeNaturePlan.D;

    @Autowired
    private NaturePlanRepository naturePlanRepository;

    @Autowired
    private NaturePlanService naturePlanService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNaturePlanMockMvc;

    private NaturePlan naturePlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NaturePlan createEntity(EntityManager em) {
        NaturePlan naturePlan = new NaturePlan()
            .descNaturePlan(DEFAULT_DESC_NATURE_PLAN)
            .typeNaturePlan(DEFAULT_TYPE_NATURE_PLAN);
        return naturePlan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NaturePlan createUpdatedEntity(EntityManager em) {
        NaturePlan naturePlan = new NaturePlan()
            .descNaturePlan(UPDATED_DESC_NATURE_PLAN)
            .typeNaturePlan(UPDATED_TYPE_NATURE_PLAN);
        return naturePlan;
    }

    @BeforeEach
    public void initTest() {
        naturePlan = createEntity(em);
    }

    @Test
    @Transactional
    public void createNaturePlan() throws Exception {
        int databaseSizeBeforeCreate = naturePlanRepository.findAll().size();
        // Create the NaturePlan
        restNaturePlanMockMvc.perform(post("/api/nature-plans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(naturePlan)))
            .andExpect(status().isCreated());

        // Validate the NaturePlan in the database
        List<NaturePlan> naturePlanList = naturePlanRepository.findAll();
        assertThat(naturePlanList).hasSize(databaseSizeBeforeCreate + 1);
        NaturePlan testNaturePlan = naturePlanList.get(naturePlanList.size() - 1);
        assertThat(testNaturePlan.getDescNaturePlan()).isEqualTo(DEFAULT_DESC_NATURE_PLAN);
        assertThat(testNaturePlan.getTypeNaturePlan()).isEqualTo(DEFAULT_TYPE_NATURE_PLAN);
    }

    @Test
    @Transactional
    public void createNaturePlanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = naturePlanRepository.findAll().size();

        // Create the NaturePlan with an existing ID
        naturePlan.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNaturePlanMockMvc.perform(post("/api/nature-plans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(naturePlan)))
            .andExpect(status().isBadRequest());

        // Validate the NaturePlan in the database
        List<NaturePlan> naturePlanList = naturePlanRepository.findAll();
        assertThat(naturePlanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNaturePlans() throws Exception {
        // Initialize the database
        naturePlanRepository.saveAndFlush(naturePlan);

        // Get all the naturePlanList
        restNaturePlanMockMvc.perform(get("/api/nature-plans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(naturePlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].descNaturePlan").value(hasItem(DEFAULT_DESC_NATURE_PLAN)))
            .andExpect(jsonPath("$.[*].typeNaturePlan").value(hasItem(DEFAULT_TYPE_NATURE_PLAN.toString())));
    }

    @Test
    @Transactional
    public void getNaturePlan() throws Exception {
        // Initialize the database
        naturePlanRepository.saveAndFlush(naturePlan);

        // Get the naturePlan
        restNaturePlanMockMvc.perform(get("/api/nature-plans/{id}", naturePlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(naturePlan.getId().intValue()))
            .andExpect(jsonPath("$.descNaturePlan").value(DEFAULT_DESC_NATURE_PLAN))
            .andExpect(jsonPath("$.typeNaturePlan").value(DEFAULT_TYPE_NATURE_PLAN.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingNaturePlan() throws Exception {
        // Get the naturePlan
        restNaturePlanMockMvc.perform(get("/api/nature-plans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNaturePlan() throws Exception {
        // Initialize the database
        naturePlanService.save(naturePlan);

        int databaseSizeBeforeUpdate = naturePlanRepository.findAll().size();

        // Update the naturePlan
        NaturePlan updatedNaturePlan = naturePlanRepository.findById(naturePlan.getId()).get();
        // Disconnect from session so that the updates on updatedNaturePlan are not directly saved in db
        em.detach(updatedNaturePlan);
        updatedNaturePlan
            .descNaturePlan(UPDATED_DESC_NATURE_PLAN)
            .typeNaturePlan(UPDATED_TYPE_NATURE_PLAN);

        restNaturePlanMockMvc.perform(put("/api/nature-plans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedNaturePlan)))
            .andExpect(status().isOk());

        // Validate the NaturePlan in the database
        List<NaturePlan> naturePlanList = naturePlanRepository.findAll();
        assertThat(naturePlanList).hasSize(databaseSizeBeforeUpdate);
        NaturePlan testNaturePlan = naturePlanList.get(naturePlanList.size() - 1);
        assertThat(testNaturePlan.getDescNaturePlan()).isEqualTo(UPDATED_DESC_NATURE_PLAN);
        assertThat(testNaturePlan.getTypeNaturePlan()).isEqualTo(UPDATED_TYPE_NATURE_PLAN);
    }

    @Test
    @Transactional
    public void updateNonExistingNaturePlan() throws Exception {
        int databaseSizeBeforeUpdate = naturePlanRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNaturePlanMockMvc.perform(put("/api/nature-plans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(naturePlan)))
            .andExpect(status().isBadRequest());

        // Validate the NaturePlan in the database
        List<NaturePlan> naturePlanList = naturePlanRepository.findAll();
        assertThat(naturePlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNaturePlan() throws Exception {
        // Initialize the database
        naturePlanService.save(naturePlan);

        int databaseSizeBeforeDelete = naturePlanRepository.findAll().size();

        // Delete the naturePlan
        restNaturePlanMockMvc.perform(delete("/api/nature-plans/{id}", naturePlan.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NaturePlan> naturePlanList = naturePlanRepository.findAll();
        assertThat(naturePlanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
