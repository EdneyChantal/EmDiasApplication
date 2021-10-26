package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.EmdiasApp;
import com.eschantal.emdias.service.AccountBankService;
import com.eschantal.emdias.service.NaturePlanService;
import com.eschantal.emdias.service.WorkspacelistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the EmdiasResource REST controller.
 *
 * @see EmdiasResource
 */
@SpringBootTest(classes = EmdiasApp.class)
public class EmdiasResourceIT {

    private MockMvc restMockMvc;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        EmdiasResource emdiasResource = new EmdiasResource();
        restMockMvc = MockMvcBuilders
            .standaloneSetup(emdiasResource)
            .build();
    }

    /**
     * Test workspacelist
     */
    @Test
    public void testWorkspacelist() throws Exception {
        restMockMvc.perform(get("/api/emdias/workspacelist"))
            .andExpect(status().isOk());
    }
}
