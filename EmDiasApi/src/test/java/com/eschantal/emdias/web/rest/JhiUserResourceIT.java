package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.EmdiasApp;
import com.eschantal.emdias.domain.JhiUser;
import com.eschantal.emdias.repository.JhiUserRepository;
import com.eschantal.emdias.service.JhiUserService;

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
 * Integration tests for the {@link JhiUserResource} REST controller.
 */
@SpringBootTest(classes = EmdiasApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class JhiUserResourceIT {

    private static final String DEFAULT_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBBBBBB";

    @Autowired
    private JhiUserRepository jhiUserRepository;

    @Autowired
    private JhiUserService jhiUserService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJhiUserMockMvc;

    private JhiUser jhiUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JhiUser createEntity(EntityManager em) {
        JhiUser jhiUser = new JhiUser()
            .login(DEFAULT_LOGIN);
        return jhiUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JhiUser createUpdatedEntity(EntityManager em) {
        JhiUser jhiUser = new JhiUser()
            .login(UPDATED_LOGIN);
        return jhiUser;
    }

    @BeforeEach
    public void initTest() {
        jhiUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createJhiUser() throws Exception {
        int databaseSizeBeforeCreate = jhiUserRepository.findAll().size();
        // Create the JhiUser
        restJhiUserMockMvc.perform(post("/api/jhi-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jhiUser)))
            .andExpect(status().isCreated());

        // Validate the JhiUser in the database
        List<JhiUser> jhiUserList = jhiUserRepository.findAll();
        assertThat(jhiUserList).hasSize(databaseSizeBeforeCreate + 1);
        JhiUser testJhiUser = jhiUserList.get(jhiUserList.size() - 1);
        assertThat(testJhiUser.getLogin()).isEqualTo(DEFAULT_LOGIN);
    }

    @Test
    @Transactional
    public void createJhiUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jhiUserRepository.findAll().size();

        // Create the JhiUser with an existing ID
        jhiUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJhiUserMockMvc.perform(post("/api/jhi-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jhiUser)))
            .andExpect(status().isBadRequest());

        // Validate the JhiUser in the database
        List<JhiUser> jhiUserList = jhiUserRepository.findAll();
        assertThat(jhiUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllJhiUsers() throws Exception {
        // Initialize the database
        jhiUserRepository.saveAndFlush(jhiUser);

        // Get all the jhiUserList
        restJhiUserMockMvc.perform(get("/api/jhi-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jhiUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN)));
    }
    
    @Test
    @Transactional
    public void getJhiUser() throws Exception {
        // Initialize the database
        jhiUserRepository.saveAndFlush(jhiUser);

        // Get the jhiUser
        restJhiUserMockMvc.perform(get("/api/jhi-users/{id}", jhiUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jhiUser.getId().intValue()))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN));
    }
    @Test
    @Transactional
    public void getNonExistingJhiUser() throws Exception {
        // Get the jhiUser
        restJhiUserMockMvc.perform(get("/api/jhi-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJhiUser() throws Exception {
        // Initialize the database
        jhiUserService.save(jhiUser);

        int databaseSizeBeforeUpdate = jhiUserRepository.findAll().size();

        // Update the jhiUser
        JhiUser updatedJhiUser = jhiUserRepository.findById(jhiUser.getId()).get();
        // Disconnect from session so that the updates on updatedJhiUser are not directly saved in db
        em.detach(updatedJhiUser);
        updatedJhiUser
            .login(UPDATED_LOGIN);

        restJhiUserMockMvc.perform(put("/api/jhi-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedJhiUser)))
            .andExpect(status().isOk());

        // Validate the JhiUser in the database
        List<JhiUser> jhiUserList = jhiUserRepository.findAll();
        assertThat(jhiUserList).hasSize(databaseSizeBeforeUpdate);
        JhiUser testJhiUser = jhiUserList.get(jhiUserList.size() - 1);
        assertThat(testJhiUser.getLogin()).isEqualTo(UPDATED_LOGIN);
    }

    @Test
    @Transactional
    public void updateNonExistingJhiUser() throws Exception {
        int databaseSizeBeforeUpdate = jhiUserRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJhiUserMockMvc.perform(put("/api/jhi-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(jhiUser)))
            .andExpect(status().isBadRequest());

        // Validate the JhiUser in the database
        List<JhiUser> jhiUserList = jhiUserRepository.findAll();
        assertThat(jhiUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJhiUser() throws Exception {
        // Initialize the database
        jhiUserService.save(jhiUser);

        int databaseSizeBeforeDelete = jhiUserRepository.findAll().size();

        // Delete the jhiUser
        restJhiUserMockMvc.perform(delete("/api/jhi-users/{id}", jhiUser.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JhiUser> jhiUserList = jhiUserRepository.findAll();
        assertThat(jhiUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
