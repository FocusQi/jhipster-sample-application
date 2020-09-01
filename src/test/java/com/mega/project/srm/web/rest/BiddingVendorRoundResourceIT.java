package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.BiddingVendorRound;
import com.mega.project.srm.repository.BiddingVendorRoundRepository;
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
 * Integration tests for the {@link BiddingVendorRoundResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BiddingVendorRoundResourceIT {
    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BiddingVendorRoundRepository biddingVendorRoundRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBiddingVendorRoundMockMvc;

    private BiddingVendorRound biddingVendorRound;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingVendorRound createEntity(EntityManager em) {
        BiddingVendorRound biddingVendorRound = new BiddingVendorRound()
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return biddingVendorRound;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingVendorRound createUpdatedEntity(EntityManager em) {
        BiddingVendorRound biddingVendorRound = new BiddingVendorRound()
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return biddingVendorRound;
    }

    @BeforeEach
    public void initTest() {
        biddingVendorRound = createEntity(em);
    }

    @Test
    @Transactional
    public void createBiddingVendorRound() throws Exception {
        int databaseSizeBeforeCreate = biddingVendorRoundRepository.findAll().size();
        // Create the BiddingVendorRound
        restBiddingVendorRoundMockMvc
            .perform(
                post("/api/bidding-vendor-rounds")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingVendorRound))
            )
            .andExpect(status().isCreated());

        // Validate the BiddingVendorRound in the database
        List<BiddingVendorRound> biddingVendorRoundList = biddingVendorRoundRepository.findAll();
        assertThat(biddingVendorRoundList).hasSize(databaseSizeBeforeCreate + 1);
        BiddingVendorRound testBiddingVendorRound = biddingVendorRoundList.get(biddingVendorRoundList.size() - 1);
        assertThat(testBiddingVendorRound.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBiddingVendorRound.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBiddingVendorRound.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testBiddingVendorRound.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBiddingVendorRoundWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = biddingVendorRoundRepository.findAll().size();

        // Create the BiddingVendorRound with an existing ID
        biddingVendorRound.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBiddingVendorRoundMockMvc
            .perform(
                post("/api/bidding-vendor-rounds")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingVendorRound))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingVendorRound in the database
        List<BiddingVendorRound> biddingVendorRoundList = biddingVendorRoundRepository.findAll();
        assertThat(biddingVendorRoundList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBiddingVendorRounds() throws Exception {
        // Initialize the database
        biddingVendorRoundRepository.saveAndFlush(biddingVendorRound);

        // Get all the biddingVendorRoundList
        restBiddingVendorRoundMockMvc
            .perform(get("/api/bidding-vendor-rounds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(biddingVendorRound.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getBiddingVendorRound() throws Exception {
        // Initialize the database
        biddingVendorRoundRepository.saveAndFlush(biddingVendorRound);

        // Get the biddingVendorRound
        restBiddingVendorRoundMockMvc
            .perform(get("/api/bidding-vendor-rounds/{id}", biddingVendorRound.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(biddingVendorRound.getId().intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBiddingVendorRound() throws Exception {
        // Get the biddingVendorRound
        restBiddingVendorRoundMockMvc.perform(get("/api/bidding-vendor-rounds/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBiddingVendorRound() throws Exception {
        // Initialize the database
        biddingVendorRoundRepository.saveAndFlush(biddingVendorRound);

        int databaseSizeBeforeUpdate = biddingVendorRoundRepository.findAll().size();

        // Update the biddingVendorRound
        BiddingVendorRound updatedBiddingVendorRound = biddingVendorRoundRepository.findById(biddingVendorRound.getId()).get();
        // Disconnect from session so that the updates on updatedBiddingVendorRound are not directly saved in db
        em.detach(updatedBiddingVendorRound);
        updatedBiddingVendorRound
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restBiddingVendorRoundMockMvc
            .perform(
                put("/api/bidding-vendor-rounds")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBiddingVendorRound))
            )
            .andExpect(status().isOk());

        // Validate the BiddingVendorRound in the database
        List<BiddingVendorRound> biddingVendorRoundList = biddingVendorRoundRepository.findAll();
        assertThat(biddingVendorRoundList).hasSize(databaseSizeBeforeUpdate);
        BiddingVendorRound testBiddingVendorRound = biddingVendorRoundList.get(biddingVendorRoundList.size() - 1);
        assertThat(testBiddingVendorRound.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBiddingVendorRound.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBiddingVendorRound.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testBiddingVendorRound.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBiddingVendorRound() throws Exception {
        int databaseSizeBeforeUpdate = biddingVendorRoundRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBiddingVendorRoundMockMvc
            .perform(
                put("/api/bidding-vendor-rounds")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingVendorRound))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingVendorRound in the database
        List<BiddingVendorRound> biddingVendorRoundList = biddingVendorRoundRepository.findAll();
        assertThat(biddingVendorRoundList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBiddingVendorRound() throws Exception {
        // Initialize the database
        biddingVendorRoundRepository.saveAndFlush(biddingVendorRound);

        int databaseSizeBeforeDelete = biddingVendorRoundRepository.findAll().size();

        // Delete the biddingVendorRound
        restBiddingVendorRoundMockMvc
            .perform(delete("/api/bidding-vendor-rounds/{id}", biddingVendorRound.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BiddingVendorRound> biddingVendorRoundList = biddingVendorRoundRepository.findAll();
        assertThat(biddingVendorRoundList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
