package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Opener;
import com.mycompany.myapp.repository.OpenerRepository;

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
 * Integration tests for the {@link OpenerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OpenerResourceIT {

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
    private OpenerRepository openerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOpenerMockMvc;

    private Opener opener;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Opener createEntity(EntityManager em) {
        Opener opener = new Opener()
            .userId(DEFAULT_USER_ID)
            .secretKey(DEFAULT_SECRET_KEY)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return opener;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Opener createUpdatedEntity(EntityManager em) {
        Opener opener = new Opener()
            .userId(UPDATED_USER_ID)
            .secretKey(UPDATED_SECRET_KEY)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return opener;
    }

    @BeforeEach
    public void initTest() {
        opener = createEntity(em);
    }

    @Test
    @Transactional
    public void createOpener() throws Exception {
        int databaseSizeBeforeCreate = openerRepository.findAll().size();
        // Create the Opener
        restOpenerMockMvc.perform(post("/api/openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opener)))
            .andExpect(status().isCreated());

        // Validate the Opener in the database
        List<Opener> openerList = openerRepository.findAll();
        assertThat(openerList).hasSize(databaseSizeBeforeCreate + 1);
        Opener testOpener = openerList.get(openerList.size() - 1);
        assertThat(testOpener.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testOpener.getSecretKey()).isEqualTo(DEFAULT_SECRET_KEY);
        assertThat(testOpener.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOpener.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testOpener.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testOpener.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createOpenerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = openerRepository.findAll().size();

        // Create the Opener with an existing ID
        opener.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOpenerMockMvc.perform(post("/api/openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opener)))
            .andExpect(status().isBadRequest());

        // Validate the Opener in the database
        List<Opener> openerList = openerRepository.findAll();
        assertThat(openerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = openerRepository.findAll().size();
        // set the field null
        opener.setUserId(null);

        // Create the Opener, which fails.


        restOpenerMockMvc.perform(post("/api/openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opener)))
            .andExpect(status().isBadRequest());

        List<Opener> openerList = openerRepository.findAll();
        assertThat(openerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecretKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = openerRepository.findAll().size();
        // set the field null
        opener.setSecretKey(null);

        // Create the Opener, which fails.


        restOpenerMockMvc.perform(post("/api/openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opener)))
            .andExpect(status().isBadRequest());

        List<Opener> openerList = openerRepository.findAll();
        assertThat(openerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOpeners() throws Exception {
        // Initialize the database
        openerRepository.saveAndFlush(opener);

        // Get all the openerList
        restOpenerMockMvc.perform(get("/api/openers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(opener.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].secretKey").value(hasItem(DEFAULT_SECRET_KEY)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getOpener() throws Exception {
        // Initialize the database
        openerRepository.saveAndFlush(opener);

        // Get the opener
        restOpenerMockMvc.perform(get("/api/openers/{id}", opener.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(opener.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.secretKey").value(DEFAULT_SECRET_KEY))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingOpener() throws Exception {
        // Get the opener
        restOpenerMockMvc.perform(get("/api/openers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOpener() throws Exception {
        // Initialize the database
        openerRepository.saveAndFlush(opener);

        int databaseSizeBeforeUpdate = openerRepository.findAll().size();

        // Update the opener
        Opener updatedOpener = openerRepository.findById(opener.getId()).get();
        // Disconnect from session so that the updates on updatedOpener are not directly saved in db
        em.detach(updatedOpener);
        updatedOpener
            .userId(UPDATED_USER_ID)
            .secretKey(UPDATED_SECRET_KEY)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restOpenerMockMvc.perform(put("/api/openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOpener)))
            .andExpect(status().isOk());

        // Validate the Opener in the database
        List<Opener> openerList = openerRepository.findAll();
        assertThat(openerList).hasSize(databaseSizeBeforeUpdate);
        Opener testOpener = openerList.get(openerList.size() - 1);
        assertThat(testOpener.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testOpener.getSecretKey()).isEqualTo(UPDATED_SECRET_KEY);
        assertThat(testOpener.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOpener.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testOpener.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testOpener.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOpener() throws Exception {
        int databaseSizeBeforeUpdate = openerRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOpenerMockMvc.perform(put("/api/openers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(opener)))
            .andExpect(status().isBadRequest());

        // Validate the Opener in the database
        List<Opener> openerList = openerRepository.findAll();
        assertThat(openerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOpener() throws Exception {
        // Initialize the database
        openerRepository.saveAndFlush(opener);

        int databaseSizeBeforeDelete = openerRepository.findAll().size();

        // Delete the opener
        restOpenerMockMvc.perform(delete("/api/openers/{id}", opener.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Opener> openerList = openerRepository.findAll();
        assertThat(openerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
