package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.BiddingMaterialRound;
import com.mega.project.srm.repository.BiddingMaterialRoundRepository;
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
 * Integration tests for the {@link BiddingMaterialRoundResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BiddingMaterialRoundResourceIT {
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
    private BiddingMaterialRoundRepository biddingMaterialRoundRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBiddingMaterialRoundMockMvc;

    private BiddingMaterialRound biddingMaterialRound;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingMaterialRound createEntity(EntityManager em) {
        BiddingMaterialRound biddingMaterialRound = new BiddingMaterialRound()
            .needQty(DEFAULT_NEED_QTY)
            .priceCeiling(DEFAULT_PRICE_CEILING)
            .fileUrl(DEFAULT_FILE_URL)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return biddingMaterialRound;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingMaterialRound createUpdatedEntity(EntityManager em) {
        BiddingMaterialRound biddingMaterialRound = new BiddingMaterialRound()
            .needQty(UPDATED_NEED_QTY)
            .priceCeiling(UPDATED_PRICE_CEILING)
            .fileUrl(UPDATED_FILE_URL)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return biddingMaterialRound;
    }

    @BeforeEach
    public void initTest() {
        biddingMaterialRound = createEntity(em);
    }

    @Test
    @Transactional
    public void createBiddingMaterialRound() throws Exception {
        int databaseSizeBeforeCreate = biddingMaterialRoundRepository.findAll().size();
        // Create the BiddingMaterialRound
        restBiddingMaterialRoundMockMvc
            .perform(
                post("/api/bidding-material-rounds")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingMaterialRound))
            )
            .andExpect(status().isCreated());

        // Validate the BiddingMaterialRound in the database
        List<BiddingMaterialRound> biddingMaterialRoundList = biddingMaterialRoundRepository.findAll();
        assertThat(biddingMaterialRoundList).hasSize(databaseSizeBeforeCreate + 1);
        BiddingMaterialRound testBiddingMaterialRound = biddingMaterialRoundList.get(biddingMaterialRoundList.size() - 1);
        assertThat(testBiddingMaterialRound.getNeedQty()).isEqualTo(DEFAULT_NEED_QTY);
        assertThat(testBiddingMaterialRound.getPriceCeiling()).isEqualTo(DEFAULT_PRICE_CEILING);
        assertThat(testBiddingMaterialRound.getFileUrl()).isEqualTo(DEFAULT_FILE_URL);
        assertThat(testBiddingMaterialRound.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBiddingMaterialRound.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBiddingMaterialRound.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testBiddingMaterialRound.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBiddingMaterialRoundWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = biddingMaterialRoundRepository.findAll().size();

        // Create the BiddingMaterialRound with an existing ID
        biddingMaterialRound.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBiddingMaterialRoundMockMvc
            .perform(
                post("/api/bidding-material-rounds")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingMaterialRound))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingMaterialRound in the database
        List<BiddingMaterialRound> biddingMaterialRoundList = biddingMaterialRoundRepository.findAll();
        assertThat(biddingMaterialRoundList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNeedQtyIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingMaterialRoundRepository.findAll().size();
        // set the field null
        biddingMaterialRound.setNeedQty(null);

        // Create the BiddingMaterialRound, which fails.

        restBiddingMaterialRoundMockMvc
            .perform(
                post("/api/bidding-material-rounds")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingMaterialRound))
            )
            .andExpect(status().isBadRequest());

        List<BiddingMaterialRound> biddingMaterialRoundList = biddingMaterialRoundRepository.findAll();
        assertThat(biddingMaterialRoundList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBiddingMaterialRounds() throws Exception {
        // Initialize the database
        biddingMaterialRoundRepository.saveAndFlush(biddingMaterialRound);

        // Get all the biddingMaterialRoundList
        restBiddingMaterialRoundMockMvc
            .perform(get("/api/bidding-material-rounds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(biddingMaterialRound.getId().intValue())))
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
    public void getBiddingMaterialRound() throws Exception {
        // Initialize the database
        biddingMaterialRoundRepository.saveAndFlush(biddingMaterialRound);

        // Get the biddingMaterialRound
        restBiddingMaterialRoundMockMvc
            .perform(get("/api/bidding-material-rounds/{id}", biddingMaterialRound.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(biddingMaterialRound.getId().intValue()))
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
    public void getNonExistingBiddingMaterialRound() throws Exception {
        // Get the biddingMaterialRound
        restBiddingMaterialRoundMockMvc.perform(get("/api/bidding-material-rounds/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBiddingMaterialRound() throws Exception {
        // Initialize the database
        biddingMaterialRoundRepository.saveAndFlush(biddingMaterialRound);

        int databaseSizeBeforeUpdate = biddingMaterialRoundRepository.findAll().size();

        // Update the biddingMaterialRound
        BiddingMaterialRound updatedBiddingMaterialRound = biddingMaterialRoundRepository.findById(biddingMaterialRound.getId()).get();
        // Disconnect from session so that the updates on updatedBiddingMaterialRound are not directly saved in db
        em.detach(updatedBiddingMaterialRound);
        updatedBiddingMaterialRound
            .needQty(UPDATED_NEED_QTY)
            .priceCeiling(UPDATED_PRICE_CEILING)
            .fileUrl(UPDATED_FILE_URL)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restBiddingMaterialRoundMockMvc
            .perform(
                put("/api/bidding-material-rounds")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBiddingMaterialRound))
            )
            .andExpect(status().isOk());

        // Validate the BiddingMaterialRound in the database
        List<BiddingMaterialRound> biddingMaterialRoundList = biddingMaterialRoundRepository.findAll();
        assertThat(biddingMaterialRoundList).hasSize(databaseSizeBeforeUpdate);
        BiddingMaterialRound testBiddingMaterialRound = biddingMaterialRoundList.get(biddingMaterialRoundList.size() - 1);
        assertThat(testBiddingMaterialRound.getNeedQty()).isEqualTo(UPDATED_NEED_QTY);
        assertThat(testBiddingMaterialRound.getPriceCeiling()).isEqualTo(UPDATED_PRICE_CEILING);
        assertThat(testBiddingMaterialRound.getFileUrl()).isEqualTo(UPDATED_FILE_URL);
        assertThat(testBiddingMaterialRound.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBiddingMaterialRound.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBiddingMaterialRound.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testBiddingMaterialRound.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBiddingMaterialRound() throws Exception {
        int databaseSizeBeforeUpdate = biddingMaterialRoundRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBiddingMaterialRoundMockMvc
            .perform(
                put("/api/bidding-material-rounds")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingMaterialRound))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingMaterialRound in the database
        List<BiddingMaterialRound> biddingMaterialRoundList = biddingMaterialRoundRepository.findAll();
        assertThat(biddingMaterialRoundList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBiddingMaterialRound() throws Exception {
        // Initialize the database
        biddingMaterialRoundRepository.saveAndFlush(biddingMaterialRound);

        int databaseSizeBeforeDelete = biddingMaterialRoundRepository.findAll().size();

        // Delete the biddingMaterialRound
        restBiddingMaterialRoundMockMvc
            .perform(delete("/api/bidding-material-rounds/{id}", biddingMaterialRound.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BiddingMaterialRound> biddingMaterialRoundList = biddingMaterialRoundRepository.findAll();
        assertThat(biddingMaterialRoundList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
