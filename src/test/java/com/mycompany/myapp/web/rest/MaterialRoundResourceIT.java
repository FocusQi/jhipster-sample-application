package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.MaterialRound;
import com.mycompany.myapp.repository.MaterialRoundRepository;

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
 * Integration tests for the {@link MaterialRoundResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MaterialRoundResourceIT {

    private static final Double DEFAULT_NEED_QTY = 1D;
    private static final Double UPDATED_NEED_QTY = 2D;

    private static final Double DEFAULT_PRICE_CEILING = 1D;
    private static final Double UPDATED_PRICE_CEILING = 2D;

    private static final Double DEFAULT_FILE_URL = 1D;
    private static final Double UPDATED_FILE_URL = 2D;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private MaterialRoundRepository materialRoundRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMaterialRoundMockMvc;

    private MaterialRound materialRound;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MaterialRound createEntity(EntityManager em) {
        MaterialRound materialRound = new MaterialRound()
            .needQty(DEFAULT_NEED_QTY)
            .priceCeiling(DEFAULT_PRICE_CEILING)
            .fileUrl(DEFAULT_FILE_URL)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return materialRound;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MaterialRound createUpdatedEntity(EntityManager em) {
        MaterialRound materialRound = new MaterialRound()
            .needQty(UPDATED_NEED_QTY)
            .priceCeiling(UPDATED_PRICE_CEILING)
            .fileUrl(UPDATED_FILE_URL)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return materialRound;
    }

    @BeforeEach
    public void initTest() {
        materialRound = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaterialRound() throws Exception {
        int databaseSizeBeforeCreate = materialRoundRepository.findAll().size();
        // Create the MaterialRound
        restMaterialRoundMockMvc.perform(post("/api/material-rounds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(materialRound)))
            .andExpect(status().isCreated());

        // Validate the MaterialRound in the database
        List<MaterialRound> materialRoundList = materialRoundRepository.findAll();
        assertThat(materialRoundList).hasSize(databaseSizeBeforeCreate + 1);
        MaterialRound testMaterialRound = materialRoundList.get(materialRoundList.size() - 1);
        assertThat(testMaterialRound.getNeedQty()).isEqualTo(DEFAULT_NEED_QTY);
        assertThat(testMaterialRound.getPriceCeiling()).isEqualTo(DEFAULT_PRICE_CEILING);
        assertThat(testMaterialRound.getFileUrl()).isEqualTo(DEFAULT_FILE_URL);
        assertThat(testMaterialRound.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testMaterialRound.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMaterialRound.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testMaterialRound.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createMaterialRoundWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = materialRoundRepository.findAll().size();

        // Create the MaterialRound with an existing ID
        materialRound.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaterialRoundMockMvc.perform(post("/api/material-rounds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(materialRound)))
            .andExpect(status().isBadRequest());

        // Validate the MaterialRound in the database
        List<MaterialRound> materialRoundList = materialRoundRepository.findAll();
        assertThat(materialRoundList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNeedQtyIsRequired() throws Exception {
        int databaseSizeBeforeTest = materialRoundRepository.findAll().size();
        // set the field null
        materialRound.setNeedQty(null);

        // Create the MaterialRound, which fails.


        restMaterialRoundMockMvc.perform(post("/api/material-rounds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(materialRound)))
            .andExpect(status().isBadRequest());

        List<MaterialRound> materialRoundList = materialRoundRepository.findAll();
        assertThat(materialRoundList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMaterialRounds() throws Exception {
        // Initialize the database
        materialRoundRepository.saveAndFlush(materialRound);

        // Get all the materialRoundList
        restMaterialRoundMockMvc.perform(get("/api/material-rounds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(materialRound.getId().intValue())))
            .andExpect(jsonPath("$.[*].needQty").value(hasItem(DEFAULT_NEED_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].priceCeiling").value(hasItem(DEFAULT_PRICE_CEILING.doubleValue())))
            .andExpect(jsonPath("$.[*].fileUrl").value(hasItem(DEFAULT_FILE_URL.doubleValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getMaterialRound() throws Exception {
        // Initialize the database
        materialRoundRepository.saveAndFlush(materialRound);

        // Get the materialRound
        restMaterialRoundMockMvc.perform(get("/api/material-rounds/{id}", materialRound.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(materialRound.getId().intValue()))
            .andExpect(jsonPath("$.needQty").value(DEFAULT_NEED_QTY.doubleValue()))
            .andExpect(jsonPath("$.priceCeiling").value(DEFAULT_PRICE_CEILING.doubleValue()))
            .andExpect(jsonPath("$.fileUrl").value(DEFAULT_FILE_URL.doubleValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMaterialRound() throws Exception {
        // Get the materialRound
        restMaterialRoundMockMvc.perform(get("/api/material-rounds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaterialRound() throws Exception {
        // Initialize the database
        materialRoundRepository.saveAndFlush(materialRound);

        int databaseSizeBeforeUpdate = materialRoundRepository.findAll().size();

        // Update the materialRound
        MaterialRound updatedMaterialRound = materialRoundRepository.findById(materialRound.getId()).get();
        // Disconnect from session so that the updates on updatedMaterialRound are not directly saved in db
        em.detach(updatedMaterialRound);
        updatedMaterialRound
            .needQty(UPDATED_NEED_QTY)
            .priceCeiling(UPDATED_PRICE_CEILING)
            .fileUrl(UPDATED_FILE_URL)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restMaterialRoundMockMvc.perform(put("/api/material-rounds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMaterialRound)))
            .andExpect(status().isOk());

        // Validate the MaterialRound in the database
        List<MaterialRound> materialRoundList = materialRoundRepository.findAll();
        assertThat(materialRoundList).hasSize(databaseSizeBeforeUpdate);
        MaterialRound testMaterialRound = materialRoundList.get(materialRoundList.size() - 1);
        assertThat(testMaterialRound.getNeedQty()).isEqualTo(UPDATED_NEED_QTY);
        assertThat(testMaterialRound.getPriceCeiling()).isEqualTo(UPDATED_PRICE_CEILING);
        assertThat(testMaterialRound.getFileUrl()).isEqualTo(UPDATED_FILE_URL);
        assertThat(testMaterialRound.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMaterialRound.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMaterialRound.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testMaterialRound.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMaterialRound() throws Exception {
        int databaseSizeBeforeUpdate = materialRoundRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMaterialRoundMockMvc.perform(put("/api/material-rounds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(materialRound)))
            .andExpect(status().isBadRequest());

        // Validate the MaterialRound in the database
        List<MaterialRound> materialRoundList = materialRoundRepository.findAll();
        assertThat(materialRoundList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMaterialRound() throws Exception {
        // Initialize the database
        materialRoundRepository.saveAndFlush(materialRound);

        int databaseSizeBeforeDelete = materialRoundRepository.findAll().size();

        // Delete the materialRound
        restMaterialRoundMockMvc.perform(delete("/api/material-rounds/{id}", materialRound.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MaterialRound> materialRoundList = materialRoundRepository.findAll();
        assertThat(materialRoundList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
