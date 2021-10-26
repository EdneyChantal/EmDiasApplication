package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.EmdiasApp;
import com.eschantal.emdias.domain.WorkSpace;
import com.eschantal.emdias.repository.WorkSpaceRepository;
import com.eschantal.emdias.service.WorkSpaceService;

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
 * Integration tests for the {@link WorkSpaceResource} REST controller.
 */
@SpringBootTest(classes = EmdiasApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WorkSpaceResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private WorkSpaceRepository workSpaceRepository;

    @Autowired
    private WorkSpaceService workSpaceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkSpaceMockMvc;

    private WorkSpace workSpace;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkSpace createEntity(EntityManager em) {
        WorkSpace workSpace = new WorkSpace()
            .nome(DEFAULT_NOME);
        return workSpace;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkSpace createUpdatedEntity(EntityManager em) {
        WorkSpace workSpace = new WorkSpace()
            .nome(UPDATED_NOME);
        return workSpace;
    }

    @BeforeEach
    public void initTest() {
        workSpace = createEntity(em);
    }

    @Test
    @Transactional
    public void createWorkSpace() throws Exception {
        int databaseSizeBeforeCreate = workSpaceRepository.findAll().size();
        // Create the WorkSpace
        restWorkSpaceMockMvc.perform(post("/api/work-spaces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workSpace)))
            .andExpect(status().isCreated());

        // Validate the WorkSpace in the database
        List<WorkSpace> workSpaceList = workSpaceRepository.findAll();
        assertThat(workSpaceList).hasSize(databaseSizeBeforeCreate + 1);
        WorkSpace testWorkSpace = workSpaceList.get(workSpaceList.size() - 1);
        assertThat(testWorkSpace.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createWorkSpaceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = workSpaceRepository.findAll().size();

        // Create the WorkSpace with an existing ID
        workSpace.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkSpaceMockMvc.perform(post("/api/work-spaces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workSpace)))
            .andExpect(status().isBadRequest());

        // Validate the WorkSpace in the database
        List<WorkSpace> workSpaceList = workSpaceRepository.findAll();
        assertThat(workSpaceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWorkSpaces() throws Exception {
        // Initialize the database
        workSpaceRepository.saveAndFlush(workSpace);

        // Get all the workSpaceList
        restWorkSpaceMockMvc.perform(get("/api/work-spaces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workSpace.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)));
    }
    
    @Test
    @Transactional
    public void getWorkSpace() throws Exception {
        // Initialize the database
        workSpaceRepository.saveAndFlush(workSpace);

        // Get the workSpace
        restWorkSpaceMockMvc.perform(get("/api/work-spaces/{id}", workSpace.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workSpace.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME));
    }
    @Test
    @Transactional
    public void getNonExistingWorkSpace() throws Exception {
        // Get the workSpace
        restWorkSpaceMockMvc.perform(get("/api/work-spaces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkSpace() throws Exception {
        // Initialize the database
        workSpaceService.save(workSpace);

        int databaseSizeBeforeUpdate = workSpaceRepository.findAll().size();

        // Update the workSpace
        WorkSpace updatedWorkSpace = workSpaceRepository.findById(workSpace.getId()).get();
        // Disconnect from session so that the updates on updatedWorkSpace are not directly saved in db
        em.detach(updatedWorkSpace);
        updatedWorkSpace
            .nome(UPDATED_NOME);

        restWorkSpaceMockMvc.perform(put("/api/work-spaces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWorkSpace)))
            .andExpect(status().isOk());

        // Validate the WorkSpace in the database
        List<WorkSpace> workSpaceList = workSpaceRepository.findAll();
        assertThat(workSpaceList).hasSize(databaseSizeBeforeUpdate);
        WorkSpace testWorkSpace = workSpaceList.get(workSpaceList.size() - 1);
        assertThat(testWorkSpace.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingWorkSpace() throws Exception {
        int databaseSizeBeforeUpdate = workSpaceRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkSpaceMockMvc.perform(put("/api/work-spaces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(workSpace)))
            .andExpect(status().isBadRequest());

        // Validate the WorkSpace in the database
        List<WorkSpace> workSpaceList = workSpaceRepository.findAll();
        assertThat(workSpaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWorkSpace() throws Exception {
        // Initialize the database
        workSpaceService.save(workSpace);

        int databaseSizeBeforeDelete = workSpaceRepository.findAll().size();

        // Delete the workSpace
        restWorkSpaceMockMvc.perform(delete("/api/work-spaces/{id}", workSpace.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WorkSpace> workSpaceList = workSpaceRepository.findAll();
        assertThat(workSpaceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
