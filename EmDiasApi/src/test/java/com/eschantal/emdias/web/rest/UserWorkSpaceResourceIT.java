package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.EmdiasApp;
import com.eschantal.emdias.domain.UserWorkSpace;
import com.eschantal.emdias.repository.UserWorkSpaceRepository;
import com.eschantal.emdias.service.UserWorkSpaceService;

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
 * Integration tests for the {@link UserWorkSpaceResource} REST controller.
 */
@SpringBootTest(classes = EmdiasApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserWorkSpaceResourceIT {

    @Autowired
    private UserWorkSpaceRepository userWorkSpaceRepository;

    @Autowired
    private UserWorkSpaceService userWorkSpaceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserWorkSpaceMockMvc;

    private UserWorkSpace userWorkSpace;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserWorkSpace createEntity(EntityManager em) {
        UserWorkSpace userWorkSpace = new UserWorkSpace();
        return userWorkSpace;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserWorkSpace createUpdatedEntity(EntityManager em) {
        UserWorkSpace userWorkSpace = new UserWorkSpace();
        return userWorkSpace;
    }

    @BeforeEach
    public void initTest() {
        userWorkSpace = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserWorkSpace() throws Exception {
        int databaseSizeBeforeCreate = userWorkSpaceRepository.findAll().size();
        // Create the UserWorkSpace
        restUserWorkSpaceMockMvc.perform(post("/api/user-work-spaces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userWorkSpace)))
            .andExpect(status().isCreated());

        // Validate the UserWorkSpace in the database
        List<UserWorkSpace> userWorkSpaceList = userWorkSpaceRepository.findAll();
        assertThat(userWorkSpaceList).hasSize(databaseSizeBeforeCreate + 1);
        UserWorkSpace testUserWorkSpace = userWorkSpaceList.get(userWorkSpaceList.size() - 1);
    }

    @Test
    @Transactional
    public void createUserWorkSpaceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userWorkSpaceRepository.findAll().size();

        // Create the UserWorkSpace with an existing ID
        userWorkSpace.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserWorkSpaceMockMvc.perform(post("/api/user-work-spaces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userWorkSpace)))
            .andExpect(status().isBadRequest());

        // Validate the UserWorkSpace in the database
        List<UserWorkSpace> userWorkSpaceList = userWorkSpaceRepository.findAll();
        assertThat(userWorkSpaceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserWorkSpaces() throws Exception {
        // Initialize the database
        userWorkSpaceRepository.saveAndFlush(userWorkSpace);

        // Get all the userWorkSpaceList
        restUserWorkSpaceMockMvc.perform(get("/api/user-work-spaces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userWorkSpace.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getUserWorkSpace() throws Exception {
        // Initialize the database
        userWorkSpaceRepository.saveAndFlush(userWorkSpace);

        // Get the userWorkSpace
        restUserWorkSpaceMockMvc.perform(get("/api/user-work-spaces/{id}", userWorkSpace.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userWorkSpace.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingUserWorkSpace() throws Exception {
        // Get the userWorkSpace
        restUserWorkSpaceMockMvc.perform(get("/api/user-work-spaces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserWorkSpace() throws Exception {
        // Initialize the database
        userWorkSpaceService.save(userWorkSpace);

        int databaseSizeBeforeUpdate = userWorkSpaceRepository.findAll().size();

        // Update the userWorkSpace
        UserWorkSpace updatedUserWorkSpace = userWorkSpaceRepository.findById(userWorkSpace.getId()).get();
        // Disconnect from session so that the updates on updatedUserWorkSpace are not directly saved in db
        em.detach(updatedUserWorkSpace);

        restUserWorkSpaceMockMvc.perform(put("/api/user-work-spaces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserWorkSpace)))
            .andExpect(status().isOk());

        // Validate the UserWorkSpace in the database
        List<UserWorkSpace> userWorkSpaceList = userWorkSpaceRepository.findAll();
        assertThat(userWorkSpaceList).hasSize(databaseSizeBeforeUpdate);
        UserWorkSpace testUserWorkSpace = userWorkSpaceList.get(userWorkSpaceList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingUserWorkSpace() throws Exception {
        int databaseSizeBeforeUpdate = userWorkSpaceRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserWorkSpaceMockMvc.perform(put("/api/user-work-spaces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userWorkSpace)))
            .andExpect(status().isBadRequest());

        // Validate the UserWorkSpace in the database
        List<UserWorkSpace> userWorkSpaceList = userWorkSpaceRepository.findAll();
        assertThat(userWorkSpaceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserWorkSpace() throws Exception {
        // Initialize the database
        userWorkSpaceService.save(userWorkSpace);

        int databaseSizeBeforeDelete = userWorkSpaceRepository.findAll().size();

        // Delete the userWorkSpace
        restUserWorkSpaceMockMvc.perform(delete("/api/user-work-spaces/{id}", userWorkSpace.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserWorkSpace> userWorkSpaceList = userWorkSpaceRepository.findAll();
        assertThat(userWorkSpaceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
