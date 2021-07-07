package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.BiddingOpener;
import com.mycompany.myapp.repository.BiddingOpenerRepository;

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
 * Integration tests for the {@link BiddingOpenerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BiddingOpenerResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SECRET_KEY = "AAAAAAAAAA";
    private static final String UPDATED_SECRET_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BiddingOpenerRepository biddingOpenerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBiddingOpenerMockMvc;

    private BiddingOpener biddingOpener;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingOpener createEntity(EntityManager em) {
        BiddingOpener biddingOpener = new BiddingOpener()
            .userId(DEFAULT_USER_ID)
            .secretKey(DEFAULT_SECRET_KEY)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return biddingOpener;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingOpener createUpdatedEntity(EntityManager em) {
        BiddingOpener biddingOpener = new BiddingOpener()
            .userId(UPDATED_USER_ID)
            .secretKey(UPDATED_SECRET_KEY)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return biddingOpener;
    }

    @BeforeEach
    public void initTest() {
        biddingOpener = createEntity(em);
    }

    @Test
    @Transactional
    public void createBiddingOpener() throws Exception {
        int databaseSizeBeforeCreate = biddingOpenerRepository.findAll().size();
        // Create the BiddingOpener
        restBiddingOpenerMockMvc.perform(post("/api/bidding-openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(biddingOpener)))
            .andExpect(status().isCreated());

        // Validate the BiddingOpener in the database
        List<BiddingOpener> biddingOpenerList = biddingOpenerRepository.findAll();
        assertThat(biddingOpenerList).hasSize(databaseSizeBeforeCreate + 1);
        BiddingOpener testBiddingOpener = biddingOpenerList.get(biddingOpenerList.size() - 1);
        assertThat(testBiddingOpener.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testBiddingOpener.getSecretKey()).isEqualTo(DEFAULT_SECRET_KEY);
        assertThat(testBiddingOpener.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBiddingOpener.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBiddingOpener.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testBiddingOpener.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBiddingOpenerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = biddingOpenerRepository.findAll().size();

        // Create the BiddingOpener with an existing ID
        biddingOpener.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBiddingOpenerMockMvc.perform(post("/api/bidding-openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(biddingOpener)))
            .andExpect(status().isBadRequest());

        // Validate the BiddingOpener in the database
        List<BiddingOpener> biddingOpenerList = biddingOpenerRepository.findAll();
        assertThat(biddingOpenerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingOpenerRepository.findAll().size();
        // set the field null
        biddingOpener.setUserId(null);

        // Create the BiddingOpener, which fails.


        restBiddingOpenerMockMvc.perform(post("/api/bidding-openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(biddingOpener)))
            .andExpect(status().isBadRequest());

        List<BiddingOpener> biddingOpenerList = biddingOpenerRepository.findAll();
        assertThat(biddingOpenerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecretKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingOpenerRepository.findAll().size();
        // set the field null
        biddingOpener.setSecretKey(null);

        // Create the BiddingOpener, which fails.


        restBiddingOpenerMockMvc.perform(post("/api/bidding-openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(biddingOpener)))
            .andExpect(status().isBadRequest());

        List<BiddingOpener> biddingOpenerList = biddingOpenerRepository.findAll();
        assertThat(biddingOpenerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBiddingOpeners() throws Exception {
        // Initialize the database
        biddingOpenerRepository.saveAndFlush(biddingOpener);

        // Get all the biddingOpenerList
        restBiddingOpenerMockMvc.perform(get("/api/bidding-openers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(biddingOpener.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].secretKey").value(hasItem(DEFAULT_SECRET_KEY)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getBiddingOpener() throws Exception {
        // Initialize the database
        biddingOpenerRepository.saveAndFlush(biddingOpener);

        // Get the biddingOpener
        restBiddingOpenerMockMvc.perform(get("/api/bidding-openers/{id}", biddingOpener.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(biddingOpener.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.secretKey").value(DEFAULT_SECRET_KEY))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBiddingOpener() throws Exception {
        // Get the biddingOpener
        restBiddingOpenerMockMvc.perform(get("/api/bidding-openers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBiddingOpener() throws Exception {
        // Initialize the database
        biddingOpenerRepository.saveAndFlush(biddingOpener);

        int databaseSizeBeforeUpdate = biddingOpenerRepository.findAll().size();

        // Update the biddingOpener
        BiddingOpener updatedBiddingOpener = biddingOpenerRepository.findById(biddingOpener.getId()).get();
        // Disconnect from session so that the updates on updatedBiddingOpener are not directly saved in db
        em.detach(updatedBiddingOpener);
        updatedBiddingOpener
            .userId(UPDATED_USER_ID)
            .secretKey(UPDATED_SECRET_KEY)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restBiddingOpenerMockMvc.perform(put("/api/bidding-openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBiddingOpener)))
            .andExpect(status().isOk());

        // Validate the BiddingOpener in the database
        List<BiddingOpener> biddingOpenerList = biddingOpenerRepository.findAll();
        assertThat(biddingOpenerList).hasSize(databaseSizeBeforeUpdate);
        BiddingOpener testBiddingOpener = biddingOpenerList.get(biddingOpenerList.size() - 1);
        assertThat(testBiddingOpener.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBiddingOpener.getSecretKey()).isEqualTo(UPDATED_SECRET_KEY);
        assertThat(testBiddingOpener.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBiddingOpener.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBiddingOpener.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testBiddingOpener.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBiddingOpener() throws Exception {
        int databaseSizeBeforeUpdate = biddingOpenerRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBiddingOpenerMockMvc.perform(put("/api/bidding-openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(biddingOpener)))
            .andExpect(status().isBadRequest());

        // Validate the BiddingOpener in the database
        List<BiddingOpener> biddingOpenerList = biddingOpenerRepository.findAll();
        assertThat(biddingOpenerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBiddingOpener() throws Exception {
        // Initialize the database
        biddingOpenerRepository.saveAndFlush(biddingOpener);

        int databaseSizeBeforeDelete = biddingOpenerRepository.findAll().size();

        // Delete the biddingOpener
        restBiddingOpenerMockMvc.perform(delete("/api/bidding-openers/{id}", biddingOpener.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BiddingOpener> biddingOpenerList = biddingOpenerRepository.findAll();
        assertThat(biddingOpenerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
