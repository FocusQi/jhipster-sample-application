package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiScoreGradeConfig;
import com.mega.project.srm.repository.KpiScoreGradeConfigRepository;
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
 * Integration tests for the {@link KpiScoreGradeConfigResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiScoreGradeConfigResourceIT {
    private static final Integer DEFAULT_START_RANGE = 1;
    private static final Integer UPDATED_START_RANGE = 2;

    private static final Instant DEFAULT_END_RANGE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_RANGE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_GRADE = "AAAAAAAAAA";
    private static final String UPDATED_GRADE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_NEED_IMPROVE = false;
    private static final Boolean UPDATED_NEED_IMPROVE = true;

    private static final String DEFAULT_GRADE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_GRADE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_OPER_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_OPER_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiScoreGradeConfigRepository kpiScoreGradeConfigRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiScoreGradeConfigMockMvc;

    private KpiScoreGradeConfig kpiScoreGradeConfig;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiScoreGradeConfig createEntity(EntityManager em) {
        KpiScoreGradeConfig kpiScoreGradeConfig = new KpiScoreGradeConfig()
            .startRange(DEFAULT_START_RANGE)
            .endRange(DEFAULT_END_RANGE)
            .grade(DEFAULT_GRADE)
            .needImprove(DEFAULT_NEED_IMPROVE)
            .gradeDescription(DEFAULT_GRADE_DESCRIPTION)
            .operDescription(DEFAULT_OPER_DESCRIPTION)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiScoreGradeConfig;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiScoreGradeConfig createUpdatedEntity(EntityManager em) {
        KpiScoreGradeConfig kpiScoreGradeConfig = new KpiScoreGradeConfig()
            .startRange(UPDATED_START_RANGE)
            .endRange(UPDATED_END_RANGE)
            .grade(UPDATED_GRADE)
            .needImprove(UPDATED_NEED_IMPROVE)
            .gradeDescription(UPDATED_GRADE_DESCRIPTION)
            .operDescription(UPDATED_OPER_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiScoreGradeConfig;
    }

    @BeforeEach
    public void initTest() {
        kpiScoreGradeConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiScoreGradeConfig() throws Exception {
        int databaseSizeBeforeCreate = kpiScoreGradeConfigRepository.findAll().size();
        // Create the KpiScoreGradeConfig
        restKpiScoreGradeConfigMockMvc
            .perform(
                post("/api/kpi-score-grade-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreGradeConfig))
            )
            .andExpect(status().isCreated());

        // Validate the KpiScoreGradeConfig in the database
        List<KpiScoreGradeConfig> kpiScoreGradeConfigList = kpiScoreGradeConfigRepository.findAll();
        assertThat(kpiScoreGradeConfigList).hasSize(databaseSizeBeforeCreate + 1);
        KpiScoreGradeConfig testKpiScoreGradeConfig = kpiScoreGradeConfigList.get(kpiScoreGradeConfigList.size() - 1);
        assertThat(testKpiScoreGradeConfig.getStartRange()).isEqualTo(DEFAULT_START_RANGE);
        assertThat(testKpiScoreGradeConfig.getEndRange()).isEqualTo(DEFAULT_END_RANGE);
        assertThat(testKpiScoreGradeConfig.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testKpiScoreGradeConfig.isNeedImprove()).isEqualTo(DEFAULT_NEED_IMPROVE);
        assertThat(testKpiScoreGradeConfig.getGradeDescription()).isEqualTo(DEFAULT_GRADE_DESCRIPTION);
        assertThat(testKpiScoreGradeConfig.getOperDescription()).isEqualTo(DEFAULT_OPER_DESCRIPTION);
        assertThat(testKpiScoreGradeConfig.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiScoreGradeConfig.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiScoreGradeConfig.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiScoreGradeConfig.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiScoreGradeConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiScoreGradeConfigRepository.findAll().size();

        // Create the KpiScoreGradeConfig with an existing ID
        kpiScoreGradeConfig.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiScoreGradeConfigMockMvc
            .perform(
                post("/api/kpi-score-grade-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreGradeConfig))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiScoreGradeConfig in the database
        List<KpiScoreGradeConfig> kpiScoreGradeConfigList = kpiScoreGradeConfigRepository.findAll();
        assertThat(kpiScoreGradeConfigList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStartRangeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiScoreGradeConfigRepository.findAll().size();
        // set the field null
        kpiScoreGradeConfig.setStartRange(null);

        // Create the KpiScoreGradeConfig, which fails.

        restKpiScoreGradeConfigMockMvc
            .perform(
                post("/api/kpi-score-grade-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreGradeConfig))
            )
            .andExpect(status().isBadRequest());

        List<KpiScoreGradeConfig> kpiScoreGradeConfigList = kpiScoreGradeConfigRepository.findAll();
        assertThat(kpiScoreGradeConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndRangeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiScoreGradeConfigRepository.findAll().size();
        // set the field null
        kpiScoreGradeConfig.setEndRange(null);

        // Create the KpiScoreGradeConfig, which fails.

        restKpiScoreGradeConfigMockMvc
            .perform(
                post("/api/kpi-score-grade-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreGradeConfig))
            )
            .andExpect(status().isBadRequest());

        List<KpiScoreGradeConfig> kpiScoreGradeConfigList = kpiScoreGradeConfigRepository.findAll();
        assertThat(kpiScoreGradeConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGradeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiScoreGradeConfigRepository.findAll().size();
        // set the field null
        kpiScoreGradeConfig.setGrade(null);

        // Create the KpiScoreGradeConfig, which fails.

        restKpiScoreGradeConfigMockMvc
            .perform(
                post("/api/kpi-score-grade-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreGradeConfig))
            )
            .andExpect(status().isBadRequest());

        List<KpiScoreGradeConfig> kpiScoreGradeConfigList = kpiScoreGradeConfigRepository.findAll();
        assertThat(kpiScoreGradeConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKpiScoreGradeConfigs() throws Exception {
        // Initialize the database
        kpiScoreGradeConfigRepository.saveAndFlush(kpiScoreGradeConfig);

        // Get all the kpiScoreGradeConfigList
        restKpiScoreGradeConfigMockMvc
            .perform(get("/api/kpi-score-grade-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiScoreGradeConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].startRange").value(hasItem(DEFAULT_START_RANGE)))
            .andExpect(jsonPath("$.[*].endRange").value(hasItem(DEFAULT_END_RANGE.toString())))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].needImprove").value(hasItem(DEFAULT_NEED_IMPROVE.booleanValue())))
            .andExpect(jsonPath("$.[*].gradeDescription").value(hasItem(DEFAULT_GRADE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].operDescription").value(hasItem(DEFAULT_OPER_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiScoreGradeConfig() throws Exception {
        // Initialize the database
        kpiScoreGradeConfigRepository.saveAndFlush(kpiScoreGradeConfig);

        // Get the kpiScoreGradeConfig
        restKpiScoreGradeConfigMockMvc
            .perform(get("/api/kpi-score-grade-configs/{id}", kpiScoreGradeConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiScoreGradeConfig.getId().intValue()))
            .andExpect(jsonPath("$.startRange").value(DEFAULT_START_RANGE))
            .andExpect(jsonPath("$.endRange").value(DEFAULT_END_RANGE.toString()))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.needImprove").value(DEFAULT_NEED_IMPROVE.booleanValue()))
            .andExpect(jsonPath("$.gradeDescription").value(DEFAULT_GRADE_DESCRIPTION))
            .andExpect(jsonPath("$.operDescription").value(DEFAULT_OPER_DESCRIPTION))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiScoreGradeConfig() throws Exception {
        // Get the kpiScoreGradeConfig
        restKpiScoreGradeConfigMockMvc.perform(get("/api/kpi-score-grade-configs/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiScoreGradeConfig() throws Exception {
        // Initialize the database
        kpiScoreGradeConfigRepository.saveAndFlush(kpiScoreGradeConfig);

        int databaseSizeBeforeUpdate = kpiScoreGradeConfigRepository.findAll().size();

        // Update the kpiScoreGradeConfig
        KpiScoreGradeConfig updatedKpiScoreGradeConfig = kpiScoreGradeConfigRepository.findById(kpiScoreGradeConfig.getId()).get();
        // Disconnect from session so that the updates on updatedKpiScoreGradeConfig are not directly saved in db
        em.detach(updatedKpiScoreGradeConfig);
        updatedKpiScoreGradeConfig
            .startRange(UPDATED_START_RANGE)
            .endRange(UPDATED_END_RANGE)
            .grade(UPDATED_GRADE)
            .needImprove(UPDATED_NEED_IMPROVE)
            .gradeDescription(UPDATED_GRADE_DESCRIPTION)
            .operDescription(UPDATED_OPER_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiScoreGradeConfigMockMvc
            .perform(
                put("/api/kpi-score-grade-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiScoreGradeConfig))
            )
            .andExpect(status().isOk());

        // Validate the KpiScoreGradeConfig in the database
        List<KpiScoreGradeConfig> kpiScoreGradeConfigList = kpiScoreGradeConfigRepository.findAll();
        assertThat(kpiScoreGradeConfigList).hasSize(databaseSizeBeforeUpdate);
        KpiScoreGradeConfig testKpiScoreGradeConfig = kpiScoreGradeConfigList.get(kpiScoreGradeConfigList.size() - 1);
        assertThat(testKpiScoreGradeConfig.getStartRange()).isEqualTo(UPDATED_START_RANGE);
        assertThat(testKpiScoreGradeConfig.getEndRange()).isEqualTo(UPDATED_END_RANGE);
        assertThat(testKpiScoreGradeConfig.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testKpiScoreGradeConfig.isNeedImprove()).isEqualTo(UPDATED_NEED_IMPROVE);
        assertThat(testKpiScoreGradeConfig.getGradeDescription()).isEqualTo(UPDATED_GRADE_DESCRIPTION);
        assertThat(testKpiScoreGradeConfig.getOperDescription()).isEqualTo(UPDATED_OPER_DESCRIPTION);
        assertThat(testKpiScoreGradeConfig.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiScoreGradeConfig.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiScoreGradeConfig.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiScoreGradeConfig.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiScoreGradeConfig() throws Exception {
        int databaseSizeBeforeUpdate = kpiScoreGradeConfigRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiScoreGradeConfigMockMvc
            .perform(
                put("/api/kpi-score-grade-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreGradeConfig))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiScoreGradeConfig in the database
        List<KpiScoreGradeConfig> kpiScoreGradeConfigList = kpiScoreGradeConfigRepository.findAll();
        assertThat(kpiScoreGradeConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiScoreGradeConfig() throws Exception {
        // Initialize the database
        kpiScoreGradeConfigRepository.saveAndFlush(kpiScoreGradeConfig);

        int databaseSizeBeforeDelete = kpiScoreGradeConfigRepository.findAll().size();

        // Delete the kpiScoreGradeConfig
        restKpiScoreGradeConfigMockMvc
            .perform(delete("/api/kpi-score-grade-configs/{id}", kpiScoreGradeConfig.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiScoreGradeConfig> kpiScoreGradeConfigList = kpiScoreGradeConfigRepository.findAll();
        assertThat(kpiScoreGradeConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
