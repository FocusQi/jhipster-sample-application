package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.VendorRound;
import com.mycompany.myapp.repository.VendorRoundRepository;

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
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link VendorRoundResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VendorRoundResourceIT {

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private VendorRoundRepository vendorRoundRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVendorRoundMockMvc;

    private VendorRound vendorRound;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VendorRound createEntity(EntityManager em) {
        VendorRound vendorRound = new VendorRound()
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return vendorRound;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VendorRound createUpdatedEntity(EntityManager em) {
        VendorRound vendorRound = new VendorRound()
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return vendorRound;
    }

    @BeforeEach
    public void initTest() {
        vendorRound = createEntity(em);
    }

    @Test
    @Transactional
    public void createVendorRound() throws Exception {
        int databaseSizeBeforeCreate = vendorRoundRepository.findAll().size();
        // Create the VendorRound
        restVendorRoundMockMvc.perform(post("/api/vendor-rounds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vendorRound)))
            .andExpect(status().isCreated());

        // Validate the VendorRound in the database
        List<VendorRound> vendorRoundList = vendorRoundRepository.findAll();
        assertThat(vendorRoundList).hasSize(databaseSizeBeforeCreate + 1);
        VendorRound testVendorRound = vendorRoundList.get(vendorRoundList.size() - 1);
        assertThat(testVendorRound.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testVendorRound.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testVendorRound.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testVendorRound.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createVendorRoundWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vendorRoundRepository.findAll().size();

        // Create the VendorRound with an existing ID
        vendorRound.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVendorRoundMockMvc.perform(post("/api/vendor-rounds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vendorRound)))
            .andExpect(status().isBadRequest());

        // Validate the VendorRound in the database
        List<VendorRound> vendorRoundList = vendorRoundRepository.findAll();
        assertThat(vendorRoundList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVendorRounds() throws Exception {
        // Initialize the database
        vendorRoundRepository.saveAndFlush(vendorRound);

        // Get all the vendorRoundList
        restVendorRoundMockMvc.perform(get("/api/vendor-rounds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vendorRound.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getVendorRound() throws Exception {
        // Initialize the database
        vendorRoundRepository.saveAndFlush(vendorRound);

        // Get the vendorRound
        restVendorRoundMockMvc.perform(get("/api/vendor-rounds/{id}", vendorRound.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vendorRound.getId().intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingVendorRound() throws Exception {
        // Get the vendorRound
        restVendorRoundMockMvc.perform(get("/api/vendor-rounds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVendorRound() throws Exception {
        // Initialize the database
        vendorRoundRepository.saveAndFlush(vendorRound);

        int databaseSizeBeforeUpdate = vendorRoundRepository.findAll().size();

        // Update the vendorRound
        VendorRound updatedVendorRound = vendorRoundRepository.findById(vendorRound.getId()).get();
        // Disconnect from session so that the updates on updatedVendorRound are not directly saved in db
        em.detach(updatedVendorRound);
        updatedVendorRound
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restVendorRoundMockMvc.perform(put("/api/vendor-rounds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVendorRound)))
            .andExpect(status().isOk());

        // Validate the VendorRound in the database
        List<VendorRound> vendorRoundList = vendorRoundRepository.findAll();
        assertThat(vendorRoundList).hasSize(databaseSizeBeforeUpdate);
        VendorRound testVendorRound = vendorRoundList.get(vendorRoundList.size() - 1);
        assertThat(testVendorRound.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testVendorRound.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testVendorRound.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testVendorRound.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingVendorRound() throws Exception {
        int databaseSizeBeforeUpdate = vendorRoundRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVendorRoundMockMvc.perform(put("/api/vendor-rounds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vendorRound)))
            .andExpect(status().isBadRequest());

        // Validate the VendorRound in the database
        List<VendorRound> vendorRoundList = vendorRoundRepository.findAll();
        assertThat(vendorRoundList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVendorRound() throws Exception {
        // Initialize the database
        vendorRoundRepository.saveAndFlush(vendorRound);

        int databaseSizeBeforeDelete = vendorRoundRepository.findAll().size();

        // Delete the vendorRound
        restVendorRoundMockMvc.perform(delete("/api/vendor-rounds/{id}", vendorRound.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VendorRound> vendorRoundList = vendorRoundRepository.findAll();
        assertThat(vendorRoundList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
