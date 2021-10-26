package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.EmdiasApp;
import com.eschantal.emdias.domain.MovementBank;
import com.eschantal.emdias.repository.MovementBankRepository;
import com.eschantal.emdias.service.MovementBankService;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.eschantal.emdias.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MovementBankResource} REST controller.
 */
@SpringBootTest(classes = EmdiasApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MovementBankResourceIT {

    private static final ZonedDateTime DEFAULT_MOVEMENT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MOVEMENT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_MOVEMENT_VALUE = 1D;
    private static final Double UPDATED_MOVEMENT_VALUE = 2D;

    private static final String DEFAULT_HISTORY = "AAAAAAAAAA";
    private static final String UPDATED_HISTORY = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER_DOC = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_DOC = "BBBBBBBBBB";

    private static final String DEFAULT_ACCID = "AAAAAAAAAA";
    private static final String UPDATED_ACCID = "BBBBBBBBBB";

    @Autowired
    private MovementBankRepository movementBankRepository;

    @Autowired
    private MovementBankService movementBankService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMovementBankMockMvc;

    private MovementBank movementBank;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovementBank createEntity(EntityManager em) {
        MovementBank movementBank = new MovementBank()
            .movementDate(DEFAULT_MOVEMENT_DATE)
            .movementValue(DEFAULT_MOVEMENT_VALUE)
            .history(DEFAULT_HISTORY)
            .numberDoc(DEFAULT_NUMBER_DOC)
            .accid(DEFAULT_ACCID);
        return movementBank;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovementBank createUpdatedEntity(EntityManager em) {
        MovementBank movementBank = new MovementBank()
            .movementDate(UPDATED_MOVEMENT_DATE)
            .movementValue(UPDATED_MOVEMENT_VALUE)
            .history(UPDATED_HISTORY)
            .numberDoc(UPDATED_NUMBER_DOC)
            .accid(UPDATED_ACCID);
        return movementBank;
    }

    @BeforeEach
    public void initTest() {
        movementBank = createEntity(em);
    }

    @Test
    @Transactional
    public void createMovementBank() throws Exception {
        int databaseSizeBeforeCreate = movementBankRepository.findAll().size();
        // Create the MovementBank
        restMovementBankMockMvc.perform(post("/api/movement-banks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(movementBank)))
            .andExpect(status().isCreated());

        // Validate the MovementBank in the database
        List<MovementBank> movementBankList = movementBankRepository.findAll();
        assertThat(movementBankList).hasSize(databaseSizeBeforeCreate + 1);
        MovementBank testMovementBank = movementBankList.get(movementBankList.size() - 1);
        assertThat(testMovementBank.getMovementDate()).isEqualTo(DEFAULT_MOVEMENT_DATE);
        assertThat(testMovementBank.getMovementValue()).isEqualTo(DEFAULT_MOVEMENT_VALUE);
        assertThat(testMovementBank.getHistory()).isEqualTo(DEFAULT_HISTORY);
        assertThat(testMovementBank.getNumberDoc()).isEqualTo(DEFAULT_NUMBER_DOC);
        assertThat(testMovementBank.getAccid()).isEqualTo(DEFAULT_ACCID);
    }

    @Test
    @Transactional
    public void createMovementBankWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = movementBankRepository.findAll().size();

        // Create the MovementBank with an existing ID
        movementBank.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMovementBankMockMvc.perform(post("/api/movement-banks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(movementBank)))
            .andExpect(status().isBadRequest());

        // Validate the MovementBank in the database
        List<MovementBank> movementBankList = movementBankRepository.findAll();
        assertThat(movementBankList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMovementBanks() throws Exception {
        // Initialize the database
        movementBankRepository.saveAndFlush(movementBank);

        // Get all the movementBankList
        restMovementBankMockMvc.perform(get("/api/movement-banks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movementBank.getId().intValue())))
            .andExpect(jsonPath("$.[*].movementDate").value(hasItem(sameInstant(DEFAULT_MOVEMENT_DATE))))
            .andExpect(jsonPath("$.[*].movementValue").value(hasItem(DEFAULT_MOVEMENT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].history").value(hasItem(DEFAULT_HISTORY)))
            .andExpect(jsonPath("$.[*].numberDoc").value(hasItem(DEFAULT_NUMBER_DOC)))
            .andExpect(jsonPath("$.[*].accid").value(hasItem(DEFAULT_ACCID)));
    }
    
    @Test
    @Transactional
    public void getMovementBank() throws Exception {
        // Initialize the database
        movementBankRepository.saveAndFlush(movementBank);

        // Get the movementBank
        restMovementBankMockMvc.perform(get("/api/movement-banks/{id}", movementBank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(movementBank.getId().intValue()))
            .andExpect(jsonPath("$.movementDate").value(sameInstant(DEFAULT_MOVEMENT_DATE)))
            .andExpect(jsonPath("$.movementValue").value(DEFAULT_MOVEMENT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.history").value(DEFAULT_HISTORY))
            .andExpect(jsonPath("$.numberDoc").value(DEFAULT_NUMBER_DOC))
            .andExpect(jsonPath("$.accid").value(DEFAULT_ACCID));
    }
    @Test
    @Transactional
    public void getNonExistingMovementBank() throws Exception {
        // Get the movementBank
        restMovementBankMockMvc.perform(get("/api/movement-banks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMovementBank() throws Exception {
        // Initialize the database
        movementBankService.save(movementBank);

        int databaseSizeBeforeUpdate = movementBankRepository.findAll().size();

        // Update the movementBank
        MovementBank updatedMovementBank = movementBankRepository.findById(movementBank.getId()).get();
        // Disconnect from session so that the updates on updatedMovementBank are not directly saved in db
        em.detach(updatedMovementBank);
        updatedMovementBank
            .movementDate(UPDATED_MOVEMENT_DATE)
            .movementValue(UPDATED_MOVEMENT_VALUE)
            .history(UPDATED_HISTORY)
            .numberDoc(UPDATED_NUMBER_DOC)
            .accid(UPDATED_ACCID);

        restMovementBankMockMvc.perform(put("/api/movement-banks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMovementBank)))
            .andExpect(status().isOk());

        // Validate the MovementBank in the database
        List<MovementBank> movementBankList = movementBankRepository.findAll();
        assertThat(movementBankList).hasSize(databaseSizeBeforeUpdate);
        MovementBank testMovementBank = movementBankList.get(movementBankList.size() - 1);
        assertThat(testMovementBank.getMovementDate()).isEqualTo(UPDATED_MOVEMENT_DATE);
        assertThat(testMovementBank.getMovementValue()).isEqualTo(UPDATED_MOVEMENT_VALUE);
        assertThat(testMovementBank.getHistory()).isEqualTo(UPDATED_HISTORY);
        assertThat(testMovementBank.getNumberDoc()).isEqualTo(UPDATED_NUMBER_DOC);
        assertThat(testMovementBank.getAccid()).isEqualTo(UPDATED_ACCID);
    }

    @Test
    @Transactional
    public void updateNonExistingMovementBank() throws Exception {
        int databaseSizeBeforeUpdate = movementBankRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMovementBankMockMvc.perform(put("/api/movement-banks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(movementBank)))
            .andExpect(status().isBadRequest());

        // Validate the MovementBank in the database
        List<MovementBank> movementBankList = movementBankRepository.findAll();
        assertThat(movementBankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMovementBank() throws Exception {
        // Initialize the database
        movementBankService.save(movementBank);

        int databaseSizeBeforeDelete = movementBankRepository.findAll().size();

        // Delete the movementBank
        restMovementBankMockMvc.perform(delete("/api/movement-banks/{id}", movementBank.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MovementBank> movementBankList = movementBankRepository.findAll();
        assertThat(movementBankList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
