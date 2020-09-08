package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiScoreReference;
import com.mega.project.srm.repository.KpiScoreReferenceRepository;
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
 * Integration tests for the {@link KpiScoreReferenceResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiScoreReferenceResourceIT {
    private static final Integer DEFAULT_START_RANGE = 1;
    private static final Integer UPDATED_START_RANGE = 2;

    private static final Instant DEFAULT_END_RANGE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_RANGE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiScoreReferenceRepository kpiScoreReferenceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiScoreReferenceMockMvc;

    private KpiScoreReference kpiScoreReference;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiScoreReference createEntity(EntityManager em) {
        KpiScoreReference kpiScoreReference = new KpiScoreReference()
            .startRange(DEFAULT_START_RANGE)
            .endRange(DEFAULT_END_RANGE)
            .score(DEFAULT_SCORE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiScoreReference;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiScoreReference createUpdatedEntity(EntityManager em) {
        KpiScoreReference kpiScoreReference = new KpiScoreReference()
            .startRange(UPDATED_START_RANGE)
            .endRange(UPDATED_END_RANGE)
            .score(UPDATED_SCORE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiScoreReference;
    }

    @BeforeEach
    public void initTest() {
        kpiScoreReference = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiScoreReference() throws Exception {
        int databaseSizeBeforeCreate = kpiScoreReferenceRepository.findAll().size();
        // Create the KpiScoreReference
        restKpiScoreReferenceMockMvc
            .perform(
                post("/api/kpi-score-references")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreReference))
            )
            .andExpect(status().isCreated());

        // Validate the KpiScoreReference in the database
        List<KpiScoreReference> kpiScoreReferenceList = kpiScoreReferenceRepository.findAll();
        assertThat(kpiScoreReferenceList).hasSize(databaseSizeBeforeCreate + 1);
        KpiScoreReference testKpiScoreReference = kpiScoreReferenceList.get(kpiScoreReferenceList.size() - 1);
        assertThat(testKpiScoreReference.getStartRange()).isEqualTo(DEFAULT_START_RANGE);
        assertThat(testKpiScoreReference.getEndRange()).isEqualTo(DEFAULT_END_RANGE);
        assertThat(testKpiScoreReference.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testKpiScoreReference.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiScoreReference.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiScoreReference.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiScoreReference.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiScoreReferenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiScoreReferenceRepository.findAll().size();

        // Create the KpiScoreReference with an existing ID
        kpiScoreReference.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiScoreReferenceMockMvc
            .perform(
                post("/api/kpi-score-references")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreReference))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiScoreReference in the database
        List<KpiScoreReference> kpiScoreReferenceList = kpiScoreReferenceRepository.findAll();
        assertThat(kpiScoreReferenceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStartRangeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiScoreReferenceRepository.findAll().size();
        // set the field null
        kpiScoreReference.setStartRange(null);

        // Create the KpiScoreReference, which fails.

        restKpiScoreReferenceMockMvc
            .perform(
                post("/api/kpi-score-references")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreReference))
            )
            .andExpect(status().isBadRequest());

        List<KpiScoreReference> kpiScoreReferenceList = kpiScoreReferenceRepository.findAll();
        assertThat(kpiScoreReferenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndRangeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiScoreReferenceRepository.findAll().size();
        // set the field null
        kpiScoreReference.setEndRange(null);

        // Create the KpiScoreReference, which fails.

        restKpiScoreReferenceMockMvc
            .perform(
                post("/api/kpi-score-references")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreReference))
            )
            .andExpect(status().isBadRequest());

        List<KpiScoreReference> kpiScoreReferenceList = kpiScoreReferenceRepository.findAll();
        assertThat(kpiScoreReferenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKpiScoreReferences() throws Exception {
        // Initialize the database
        kpiScoreReferenceRepository.saveAndFlush(kpiScoreReference);

        // Get all the kpiScoreReferenceList
        restKpiScoreReferenceMockMvc
            .perform(get("/api/kpi-score-references?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiScoreReference.getId().intValue())))
            .andExpect(jsonPath("$.[*].startRange").value(hasItem(DEFAULT_START_RANGE)))
            .andExpect(jsonPath("$.[*].endRange").value(hasItem(DEFAULT_END_RANGE.toString())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiScoreReference() throws Exception {
        // Initialize the database
        kpiScoreReferenceRepository.saveAndFlush(kpiScoreReference);

        // Get the kpiScoreReference
        restKpiScoreReferenceMockMvc
            .perform(get("/api/kpi-score-references/{id}", kpiScoreReference.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiScoreReference.getId().intValue()))
            .andExpect(jsonPath("$.startRange").value(DEFAULT_START_RANGE))
            .andExpect(jsonPath("$.endRange").value(DEFAULT_END_RANGE.toString()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiScoreReference() throws Exception {
        // Get the kpiScoreReference
        restKpiScoreReferenceMockMvc.perform(get("/api/kpi-score-references/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiScoreReference() throws Exception {
        // Initialize the database
        kpiScoreReferenceRepository.saveAndFlush(kpiScoreReference);

        int databaseSizeBeforeUpdate = kpiScoreReferenceRepository.findAll().size();

        // Update the kpiScoreReference
        KpiScoreReference updatedKpiScoreReference = kpiScoreReferenceRepository.findById(kpiScoreReference.getId()).get();
        // Disconnect from session so that the updates on updatedKpiScoreReference are not directly saved in db
        em.detach(updatedKpiScoreReference);
        updatedKpiScoreReference
            .startRange(UPDATED_START_RANGE)
            .endRange(UPDATED_END_RANGE)
            .score(UPDATED_SCORE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiScoreReferenceMockMvc
            .perform(
                put("/api/kpi-score-references")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiScoreReference))
            )
            .andExpect(status().isOk());

        // Validate the KpiScoreReference in the database
        List<KpiScoreReference> kpiScoreReferenceList = kpiScoreReferenceRepository.findAll();
        assertThat(kpiScoreReferenceList).hasSize(databaseSizeBeforeUpdate);
        KpiScoreReference testKpiScoreReference = kpiScoreReferenceList.get(kpiScoreReferenceList.size() - 1);
        assertThat(testKpiScoreReference.getStartRange()).isEqualTo(UPDATED_START_RANGE);
        assertThat(testKpiScoreReference.getEndRange()).isEqualTo(UPDATED_END_RANGE);
        assertThat(testKpiScoreReference.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testKpiScoreReference.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiScoreReference.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiScoreReference.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiScoreReference.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiScoreReference() throws Exception {
        int databaseSizeBeforeUpdate = kpiScoreReferenceRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiScoreReferenceMockMvc
            .perform(
                put("/api/kpi-score-references")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreReference))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiScoreReference in the database
        List<KpiScoreReference> kpiScoreReferenceList = kpiScoreReferenceRepository.findAll();
        assertThat(kpiScoreReferenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiScoreReference() throws Exception {
        // Initialize the database
        kpiScoreReferenceRepository.saveAndFlush(kpiScoreReference);

        int databaseSizeBeforeDelete = kpiScoreReferenceRepository.findAll().size();

        // Delete the kpiScoreReference
        restKpiScoreReferenceMockMvc
            .perform(delete("/api/kpi-score-references/{id}", kpiScoreReference.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiScoreReference> kpiScoreReferenceList = kpiScoreReferenceRepository.findAll();
        assertThat(kpiScoreReferenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
