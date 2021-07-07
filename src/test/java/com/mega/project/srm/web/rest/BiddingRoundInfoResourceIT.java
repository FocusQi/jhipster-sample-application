package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.BiddingRoundInfo;
import com.mega.project.srm.repository.BiddingRoundInfoRepository;
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
 * Integration tests for the {@link BiddingRoundInfoResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BiddingRoundInfoResourceIT {
    private static final Integer DEFAULT_ROUND = 1;
    private static final Integer UPDATED_ROUND = 2;

    private static final Instant DEFAULT_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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
    private BiddingRoundInfoRepository biddingRoundInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBiddingRoundInfoMockMvc;

    private BiddingRoundInfo biddingRoundInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingRoundInfo createEntity(EntityManager em) {
        BiddingRoundInfo biddingRoundInfo = new BiddingRoundInfo()
            .round(DEFAULT_ROUND)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME)
            .description(DEFAULT_DESCRIPTION)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return biddingRoundInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingRoundInfo createUpdatedEntity(EntityManager em) {
        BiddingRoundInfo biddingRoundInfo = new BiddingRoundInfo()
            .round(UPDATED_ROUND)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return biddingRoundInfo;
    }

    @BeforeEach
    public void initTest() {
        biddingRoundInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createBiddingRoundInfo() throws Exception {
        int databaseSizeBeforeCreate = biddingRoundInfoRepository.findAll().size();
        // Create the BiddingRoundInfo
        restBiddingRoundInfoMockMvc
            .perform(
                post("/api/bidding-round-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingRoundInfo))
            )
            .andExpect(status().isCreated());

        // Validate the BiddingRoundInfo in the database
        List<BiddingRoundInfo> biddingRoundInfoList = biddingRoundInfoRepository.findAll();
        assertThat(biddingRoundInfoList).hasSize(databaseSizeBeforeCreate + 1);
        BiddingRoundInfo testBiddingRoundInfo = biddingRoundInfoList.get(biddingRoundInfoList.size() - 1);
        assertThat(testBiddingRoundInfo.getRound()).isEqualTo(DEFAULT_ROUND);
        assertThat(testBiddingRoundInfo.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testBiddingRoundInfo.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testBiddingRoundInfo.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBiddingRoundInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBiddingRoundInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBiddingRoundInfo.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testBiddingRoundInfo.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBiddingRoundInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = biddingRoundInfoRepository.findAll().size();

        // Create the BiddingRoundInfo with an existing ID
        biddingRoundInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBiddingRoundInfoMockMvc
            .perform(
                post("/api/bidding-round-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingRoundInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingRoundInfo in the database
        List<BiddingRoundInfo> biddingRoundInfoList = biddingRoundInfoRepository.findAll();
        assertThat(biddingRoundInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRoundIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingRoundInfoRepository.findAll().size();
        // set the field null
        biddingRoundInfo.setRound(null);

        // Create the BiddingRoundInfo, which fails.

        restBiddingRoundInfoMockMvc
            .perform(
                post("/api/bidding-round-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingRoundInfo))
            )
            .andExpect(status().isBadRequest());

        List<BiddingRoundInfo> biddingRoundInfoList = biddingRoundInfoRepository.findAll();
        assertThat(biddingRoundInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingRoundInfoRepository.findAll().size();
        // set the field null
        biddingRoundInfo.setStartTime(null);

        // Create the BiddingRoundInfo, which fails.

        restBiddingRoundInfoMockMvc
            .perform(
                post("/api/bidding-round-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingRoundInfo))
            )
            .andExpect(status().isBadRequest());

        List<BiddingRoundInfo> biddingRoundInfoList = biddingRoundInfoRepository.findAll();
        assertThat(biddingRoundInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingRoundInfoRepository.findAll().size();
        // set the field null
        biddingRoundInfo.setEndTime(null);

        // Create the BiddingRoundInfo, which fails.

        restBiddingRoundInfoMockMvc
            .perform(
                post("/api/bidding-round-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingRoundInfo))
            )
            .andExpect(status().isBadRequest());

        List<BiddingRoundInfo> biddingRoundInfoList = biddingRoundInfoRepository.findAll();
        assertThat(biddingRoundInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBiddingRoundInfos() throws Exception {
        // Initialize the database
        biddingRoundInfoRepository.saveAndFlush(biddingRoundInfo);

        // Get all the biddingRoundInfoList
        restBiddingRoundInfoMockMvc
            .perform(get("/api/bidding-round-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(biddingRoundInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].round").value(hasItem(DEFAULT_ROUND)))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getBiddingRoundInfo() throws Exception {
        // Initialize the database
        biddingRoundInfoRepository.saveAndFlush(biddingRoundInfo);

        // Get the biddingRoundInfo
        restBiddingRoundInfoMockMvc
            .perform(get("/api/bidding-round-infos/{id}", biddingRoundInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(biddingRoundInfo.getId().intValue()))
            .andExpect(jsonPath("$.round").value(DEFAULT_ROUND))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBiddingRoundInfo() throws Exception {
        // Get the biddingRoundInfo
        restBiddingRoundInfoMockMvc.perform(get("/api/bidding-round-infos/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBiddingRoundInfo() throws Exception {
        // Initialize the database
        biddingRoundInfoRepository.saveAndFlush(biddingRoundInfo);

        int databaseSizeBeforeUpdate = biddingRoundInfoRepository.findAll().size();

        // Update the biddingRoundInfo
        BiddingRoundInfo updatedBiddingRoundInfo = biddingRoundInfoRepository.findById(biddingRoundInfo.getId()).get();
        // Disconnect from session so that the updates on updatedBiddingRoundInfo are not directly saved in db
        em.detach(updatedBiddingRoundInfo);
        updatedBiddingRoundInfo
            .round(UPDATED_ROUND)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restBiddingRoundInfoMockMvc
            .perform(
                put("/api/bidding-round-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBiddingRoundInfo))
            )
            .andExpect(status().isOk());

        // Validate the BiddingRoundInfo in the database
        List<BiddingRoundInfo> biddingRoundInfoList = biddingRoundInfoRepository.findAll();
        assertThat(biddingRoundInfoList).hasSize(databaseSizeBeforeUpdate);
        BiddingRoundInfo testBiddingRoundInfo = biddingRoundInfoList.get(biddingRoundInfoList.size() - 1);
        assertThat(testBiddingRoundInfo.getRound()).isEqualTo(UPDATED_ROUND);
        assertThat(testBiddingRoundInfo.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testBiddingRoundInfo.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testBiddingRoundInfo.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBiddingRoundInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBiddingRoundInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBiddingRoundInfo.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testBiddingRoundInfo.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBiddingRoundInfo() throws Exception {
        int databaseSizeBeforeUpdate = biddingRoundInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBiddingRoundInfoMockMvc
            .perform(
                put("/api/bidding-round-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingRoundInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingRoundInfo in the database
        List<BiddingRoundInfo> biddingRoundInfoList = biddingRoundInfoRepository.findAll();
        assertThat(biddingRoundInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBiddingRoundInfo() throws Exception {
        // Initialize the database
        biddingRoundInfoRepository.saveAndFlush(biddingRoundInfo);

        int databaseSizeBeforeDelete = biddingRoundInfoRepository.findAll().size();

        // Delete the biddingRoundInfo
        restBiddingRoundInfoMockMvc
            .perform(delete("/api/bidding-round-infos/{id}", biddingRoundInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BiddingRoundInfo> biddingRoundInfoList = biddingRoundInfoRepository.findAll();
        assertThat(biddingRoundInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
