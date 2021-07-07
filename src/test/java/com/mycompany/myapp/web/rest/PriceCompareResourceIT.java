package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.PriceCompare;
import com.mycompany.myapp.repository.PriceCompareRepository;

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

import com.mycompany.myapp.domain.enumeration.CompareStatus;
/**
 * Integration tests for the {@link PriceCompareResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PriceCompareResourceIT {

    private static final String DEFAULT_COMPARE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COMPARE_CODE = "BBBBBBBBBB";

    private static final CompareStatus DEFAULT_STATUS = CompareStatus.UNDER_REVIEW;
    private static final CompareStatus UPDATED_STATUS = CompareStatus.REVIEW_OK;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PriceCompareRepository priceCompareRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPriceCompareMockMvc;

    private PriceCompare priceCompare;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceCompare createEntity(EntityManager em) {
        PriceCompare priceCompare = new PriceCompare()
            .compareCode(DEFAULT_COMPARE_CODE)
            .status(DEFAULT_STATUS)
            .description(DEFAULT_DESCRIPTION)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return priceCompare;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceCompare createUpdatedEntity(EntityManager em) {
        PriceCompare priceCompare = new PriceCompare()
            .compareCode(UPDATED_COMPARE_CODE)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return priceCompare;
    }

    @BeforeEach
    public void initTest() {
        priceCompare = createEntity(em);
    }

    @Test
    @Transactional
    public void createPriceCompare() throws Exception {
        int databaseSizeBeforeCreate = priceCompareRepository.findAll().size();
        // Create the PriceCompare
        restPriceCompareMockMvc.perform(post("/api/price-compares")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(priceCompare)))
            .andExpect(status().isCreated());

        // Validate the PriceCompare in the database
        List<PriceCompare> priceCompareList = priceCompareRepository.findAll();
        assertThat(priceCompareList).hasSize(databaseSizeBeforeCreate + 1);
        PriceCompare testPriceCompare = priceCompareList.get(priceCompareList.size() - 1);
        assertThat(testPriceCompare.getCompareCode()).isEqualTo(DEFAULT_COMPARE_CODE);
        assertThat(testPriceCompare.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPriceCompare.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPriceCompare.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPriceCompare.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPriceCompare.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testPriceCompare.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createPriceCompareWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = priceCompareRepository.findAll().size();

        // Create the PriceCompare with an existing ID
        priceCompare.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPriceCompareMockMvc.perform(post("/api/price-compares")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(priceCompare)))
            .andExpect(status().isBadRequest());

        // Validate the PriceCompare in the database
        List<PriceCompare> priceCompareList = priceCompareRepository.findAll();
        assertThat(priceCompareList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCompareCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = priceCompareRepository.findAll().size();
        // set the field null
        priceCompare.setCompareCode(null);

        // Create the PriceCompare, which fails.


        restPriceCompareMockMvc.perform(post("/api/price-compares")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(priceCompare)))
            .andExpect(status().isBadRequest());

        List<PriceCompare> priceCompareList = priceCompareRepository.findAll();
        assertThat(priceCompareList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = priceCompareRepository.findAll().size();
        // set the field null
        priceCompare.setStatus(null);

        // Create the PriceCompare, which fails.


        restPriceCompareMockMvc.perform(post("/api/price-compares")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(priceCompare)))
            .andExpect(status().isBadRequest());

        List<PriceCompare> priceCompareList = priceCompareRepository.findAll();
        assertThat(priceCompareList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPriceCompares() throws Exception {
        // Initialize the database
        priceCompareRepository.saveAndFlush(priceCompare);

        // Get all the priceCompareList
        restPriceCompareMockMvc.perform(get("/api/price-compares?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(priceCompare.getId().intValue())))
            .andExpect(jsonPath("$.[*].compareCode").value(hasItem(DEFAULT_COMPARE_CODE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getPriceCompare() throws Exception {
        // Initialize the database
        priceCompareRepository.saveAndFlush(priceCompare);

        // Get the priceCompare
        restPriceCompareMockMvc.perform(get("/api/price-compares/{id}", priceCompare.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(priceCompare.getId().intValue()))
            .andExpect(jsonPath("$.compareCode").value(DEFAULT_COMPARE_CODE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPriceCompare() throws Exception {
        // Get the priceCompare
        restPriceCompareMockMvc.perform(get("/api/price-compares/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePriceCompare() throws Exception {
        // Initialize the database
        priceCompareRepository.saveAndFlush(priceCompare);

        int databaseSizeBeforeUpdate = priceCompareRepository.findAll().size();

        // Update the priceCompare
        PriceCompare updatedPriceCompare = priceCompareRepository.findById(priceCompare.getId()).get();
        // Disconnect from session so that the updates on updatedPriceCompare are not directly saved in db
        em.detach(updatedPriceCompare);
        updatedPriceCompare
            .compareCode(UPDATED_COMPARE_CODE)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restPriceCompareMockMvc.perform(put("/api/price-compares")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPriceCompare)))
            .andExpect(status().isOk());

        // Validate the PriceCompare in the database
        List<PriceCompare> priceCompareList = priceCompareRepository.findAll();
        assertThat(priceCompareList).hasSize(databaseSizeBeforeUpdate);
        PriceCompare testPriceCompare = priceCompareList.get(priceCompareList.size() - 1);
        assertThat(testPriceCompare.getCompareCode()).isEqualTo(UPDATED_COMPARE_CODE);
        assertThat(testPriceCompare.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPriceCompare.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPriceCompare.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPriceCompare.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPriceCompare.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testPriceCompare.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingPriceCompare() throws Exception {
        int databaseSizeBeforeUpdate = priceCompareRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceCompareMockMvc.perform(put("/api/price-compares")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(priceCompare)))
            .andExpect(status().isBadRequest());

        // Validate the PriceCompare in the database
        List<PriceCompare> priceCompareList = priceCompareRepository.findAll();
        assertThat(priceCompareList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePriceCompare() throws Exception {
        // Initialize the database
        priceCompareRepository.saveAndFlush(priceCompare);

        int databaseSizeBeforeDelete = priceCompareRepository.findAll().size();

        // Delete the priceCompare
        restPriceCompareMockMvc.perform(delete("/api/price-compares/{id}", priceCompare.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PriceCompare> priceCompareList = priceCompareRepository.findAll();
        assertThat(priceCompareList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
