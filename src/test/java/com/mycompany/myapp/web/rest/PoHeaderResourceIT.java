package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.PoHeader;
import com.mycompany.myapp.repository.PoHeaderRepository;

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
 * Integration tests for the {@link PoHeaderResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PoHeaderResourceIT {

    @Autowired
    private PoHeaderRepository poHeaderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPoHeaderMockMvc;

    private PoHeader poHeader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PoHeader createEntity(EntityManager em) {
        PoHeader poHeader = new PoHeader();
        return poHeader;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PoHeader createUpdatedEntity(EntityManager em) {
        PoHeader poHeader = new PoHeader();
        return poHeader;
    }

    @BeforeEach
    public void initTest() {
        poHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createPoHeader() throws Exception {
        int databaseSizeBeforeCreate = poHeaderRepository.findAll().size();
        // Create the PoHeader
        restPoHeaderMockMvc.perform(post("/api/po-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(poHeader)))
            .andExpect(status().isCreated());

        // Validate the PoHeader in the database
        List<PoHeader> poHeaderList = poHeaderRepository.findAll();
        assertThat(poHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        PoHeader testPoHeader = poHeaderList.get(poHeaderList.size() - 1);
    }

    @Test
    @Transactional
    public void createPoHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = poHeaderRepository.findAll().size();

        // Create the PoHeader with an existing ID
        poHeader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPoHeaderMockMvc.perform(post("/api/po-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(poHeader)))
            .andExpect(status().isBadRequest());

        // Validate the PoHeader in the database
        List<PoHeader> poHeaderList = poHeaderRepository.findAll();
        assertThat(poHeaderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPoHeaders() throws Exception {
        // Initialize the database
        poHeaderRepository.saveAndFlush(poHeader);

        // Get all the poHeaderList
        restPoHeaderMockMvc.perform(get("/api/po-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(poHeader.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getPoHeader() throws Exception {
        // Initialize the database
        poHeaderRepository.saveAndFlush(poHeader);

        // Get the poHeader
        restPoHeaderMockMvc.perform(get("/api/po-headers/{id}", poHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(poHeader.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPoHeader() throws Exception {
        // Get the poHeader
        restPoHeaderMockMvc.perform(get("/api/po-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePoHeader() throws Exception {
        // Initialize the database
        poHeaderRepository.saveAndFlush(poHeader);

        int databaseSizeBeforeUpdate = poHeaderRepository.findAll().size();

        // Update the poHeader
        PoHeader updatedPoHeader = poHeaderRepository.findById(poHeader.getId()).get();
        // Disconnect from session so that the updates on updatedPoHeader are not directly saved in db
        em.detach(updatedPoHeader);

        restPoHeaderMockMvc.perform(put("/api/po-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPoHeader)))
            .andExpect(status().isOk());

        // Validate the PoHeader in the database
        List<PoHeader> poHeaderList = poHeaderRepository.findAll();
        assertThat(poHeaderList).hasSize(databaseSizeBeforeUpdate);
        PoHeader testPoHeader = poHeaderList.get(poHeaderList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingPoHeader() throws Exception {
        int databaseSizeBeforeUpdate = poHeaderRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPoHeaderMockMvc.perform(put("/api/po-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(poHeader)))
            .andExpect(status().isBadRequest());

        // Validate the PoHeader in the database
        List<PoHeader> poHeaderList = poHeaderRepository.findAll();
        assertThat(poHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePoHeader() throws Exception {
        // Initialize the database
        poHeaderRepository.saveAndFlush(poHeader);

        int databaseSizeBeforeDelete = poHeaderRepository.findAll().size();

        // Delete the poHeader
        restPoHeaderMockMvc.perform(delete("/api/po-headers/{id}", poHeader.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PoHeader> poHeaderList = poHeaderRepository.findAll();
        assertThat(poHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
