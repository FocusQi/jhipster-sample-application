package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.BiddingPriceCompare;
import com.mega.project.srm.domain.enumeration.CompareStatus;
import com.mega.project.srm.repository.BiddingPriceCompareRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BiddingPriceCompareResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BiddingPriceCompareResourceIT {
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
    private BiddingPriceCompareRepository biddingPriceCompareRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBiddingPriceCompareMockMvc;

    private BiddingPriceCompare biddingPriceCompare;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingPriceCompare createEntity(EntityManager em) {
        BiddingPriceCompare biddingPriceCompare = new BiddingPriceCompare()
            .compareCode(DEFAULT_COMPARE_CODE)
            .status(DEFAULT_STATUS)
            .description(DEFAULT_DESCRIPTION)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return biddingPriceCompare;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingPriceCompare createUpdatedEntity(EntityManager em) {
        BiddingPriceCompare biddingPriceCompare = new BiddingPriceCompare()
            .compareCode(UPDATED_COMPARE_CODE)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return biddingPriceCompare;
    }

    @BeforeEach
    public void initTest() {
        biddingPriceCompare = createEntity(em);
    }

    @Test
    @Transactional
    public void createBiddingPriceCompare() throws Exception {
        int databaseSizeBeforeCreate = biddingPriceCompareRepository.findAll().size();
        // Create the BiddingPriceCompare
        restBiddingPriceCompareMockMvc
            .perform(
                post("/api/bidding-price-compares")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingPriceCompare))
            )
            .andExpect(status().isCreated());

        // Validate the BiddingPriceCompare in the database
        List<BiddingPriceCompare> biddingPriceCompareList = biddingPriceCompareRepository.findAll();
        assertThat(biddingPriceCompareList).hasSize(databaseSizeBeforeCreate + 1);
        BiddingPriceCompare testBiddingPriceCompare = biddingPriceCompareList.get(biddingPriceCompareList.size() - 1);
        assertThat(testBiddingPriceCompare.getCompareCode()).isEqualTo(DEFAULT_COMPARE_CODE);
        assertThat(testBiddingPriceCompare.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBiddingPriceCompare.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBiddingPriceCompare.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBiddingPriceCompare.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBiddingPriceCompare.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testBiddingPriceCompare.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBiddingPriceCompareWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = biddingPriceCompareRepository.findAll().size();

        // Create the BiddingPriceCompare with an existing ID
        biddingPriceCompare.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBiddingPriceCompareMockMvc
            .perform(
                post("/api/bidding-price-compares")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingPriceCompare))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingPriceCompare in the database
        List<BiddingPriceCompare> biddingPriceCompareList = biddingPriceCompareRepository.findAll();
        assertThat(biddingPriceCompareList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCompareCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingPriceCompareRepository.findAll().size();
        // set the field null
        biddingPriceCompare.setCompareCode(null);

        // Create the BiddingPriceCompare, which fails.

        restBiddingPriceCompareMockMvc
            .perform(
                post("/api/bidding-price-compares")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingPriceCompare))
            )
            .andExpect(status().isBadRequest());

        List<BiddingPriceCompare> biddingPriceCompareList = biddingPriceCompareRepository.findAll();
        assertThat(biddingPriceCompareList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingPriceCompareRepository.findAll().size();
        // set the field null
        biddingPriceCompare.setStatus(null);

        // Create the BiddingPriceCompare, which fails.

        restBiddingPriceCompareMockMvc
            .perform(
                post("/api/bidding-price-compares")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingPriceCompare))
            )
            .andExpect(status().isBadRequest());

        List<BiddingPriceCompare> biddingPriceCompareList = biddingPriceCompareRepository.findAll();
        assertThat(biddingPriceCompareList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBiddingPriceCompares() throws Exception {
        // Initialize the database
        biddingPriceCompareRepository.saveAndFlush(biddingPriceCompare);

        // Get all the biddingPriceCompareList
        restBiddingPriceCompareMockMvc
            .perform(get("/api/bidding-price-compares?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(biddingPriceCompare.getId().intValue())))
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
    public void getBiddingPriceCompare() throws Exception {
        // Initialize the database
        biddingPriceCompareRepository.saveAndFlush(biddingPriceCompare);

        // Get the biddingPriceCompare
        restBiddingPriceCompareMockMvc
            .perform(get("/api/bidding-price-compares/{id}", biddingPriceCompare.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(biddingPriceCompare.getId().intValue()))
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
    public void getNonExistingBiddingPriceCompare() throws Exception {
        // Get the biddingPriceCompare
        restBiddingPriceCompareMockMvc.perform(get("/api/bidding-price-compares/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBiddingPriceCompare() throws Exception {
        // Initialize the database
        biddingPriceCompareRepository.saveAndFlush(biddingPriceCompare);

        int databaseSizeBeforeUpdate = biddingPriceCompareRepository.findAll().size();

        // Update the biddingPriceCompare
        BiddingPriceCompare updatedBiddingPriceCompare = biddingPriceCompareRepository.findById(biddingPriceCompare.getId()).get();
        // Disconnect from session so that the updates on updatedBiddingPriceCompare are not directly saved in db
        em.detach(updatedBiddingPriceCompare);
        updatedBiddingPriceCompare
            .compareCode(UPDATED_COMPARE_CODE)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restBiddingPriceCompareMockMvc
            .perform(
                put("/api/bidding-price-compares")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBiddingPriceCompare))
            )
            .andExpect(status().isOk());

        // Validate the BiddingPriceCompare in the database
        List<BiddingPriceCompare> biddingPriceCompareList = biddingPriceCompareRepository.findAll();
        assertThat(biddingPriceCompareList).hasSize(databaseSizeBeforeUpdate);
        BiddingPriceCompare testBiddingPriceCompare = biddingPriceCompareList.get(biddingPriceCompareList.size() - 1);
        assertThat(testBiddingPriceCompare.getCompareCode()).isEqualTo(UPDATED_COMPARE_CODE);
        assertThat(testBiddingPriceCompare.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBiddingPriceCompare.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBiddingPriceCompare.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBiddingPriceCompare.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBiddingPriceCompare.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testBiddingPriceCompare.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBiddingPriceCompare() throws Exception {
        int databaseSizeBeforeUpdate = biddingPriceCompareRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBiddingPriceCompareMockMvc
            .perform(
                put("/api/bidding-price-compares")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingPriceCompare))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingPriceCompare in the database
        List<BiddingPriceCompare> biddingPriceCompareList = biddingPriceCompareRepository.findAll();
        assertThat(biddingPriceCompareList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBiddingPriceCompare() throws Exception {
        // Initialize the database
        biddingPriceCompareRepository.saveAndFlush(biddingPriceCompare);

        int databaseSizeBeforeDelete = biddingPriceCompareRepository.findAll().size();

        // Delete the biddingPriceCompare
        restBiddingPriceCompareMockMvc
            .perform(delete("/api/bidding-price-compares/{id}", biddingPriceCompare.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BiddingPriceCompare> biddingPriceCompareList = biddingPriceCompareRepository.findAll();
        assertThat(biddingPriceCompareList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
