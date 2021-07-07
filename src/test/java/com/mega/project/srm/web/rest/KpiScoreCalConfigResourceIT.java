package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiScoreCalConfig;
import com.mega.project.srm.repository.KpiScoreCalConfigRepository;
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
 * Integration tests for the {@link KpiScoreCalConfigResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiScoreCalConfigResourceIT {
    private static final String DEFAULT_TEMPLATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiScoreCalConfigRepository kpiScoreCalConfigRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiScoreCalConfigMockMvc;

    private KpiScoreCalConfig kpiScoreCalConfig;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiScoreCalConfig createEntity(EntityManager em) {
        KpiScoreCalConfig kpiScoreCalConfig = new KpiScoreCalConfig()
            .templateName(DEFAULT_TEMPLATE_NAME)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiScoreCalConfig;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiScoreCalConfig createUpdatedEntity(EntityManager em) {
        KpiScoreCalConfig kpiScoreCalConfig = new KpiScoreCalConfig()
            .templateName(UPDATED_TEMPLATE_NAME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiScoreCalConfig;
    }

    @BeforeEach
    public void initTest() {
        kpiScoreCalConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiScoreCalConfig() throws Exception {
        int databaseSizeBeforeCreate = kpiScoreCalConfigRepository.findAll().size();
        // Create the KpiScoreCalConfig
        restKpiScoreCalConfigMockMvc
            .perform(
                post("/api/kpi-score-cal-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreCalConfig))
            )
            .andExpect(status().isCreated());

        // Validate the KpiScoreCalConfig in the database
        List<KpiScoreCalConfig> kpiScoreCalConfigList = kpiScoreCalConfigRepository.findAll();
        assertThat(kpiScoreCalConfigList).hasSize(databaseSizeBeforeCreate + 1);
        KpiScoreCalConfig testKpiScoreCalConfig = kpiScoreCalConfigList.get(kpiScoreCalConfigList.size() - 1);
        assertThat(testKpiScoreCalConfig.getTemplateName()).isEqualTo(DEFAULT_TEMPLATE_NAME);
        assertThat(testKpiScoreCalConfig.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiScoreCalConfig.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiScoreCalConfig.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiScoreCalConfig.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiScoreCalConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiScoreCalConfigRepository.findAll().size();

        // Create the KpiScoreCalConfig with an existing ID
        kpiScoreCalConfig.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiScoreCalConfigMockMvc
            .perform(
                post("/api/kpi-score-cal-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreCalConfig))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiScoreCalConfig in the database
        List<KpiScoreCalConfig> kpiScoreCalConfigList = kpiScoreCalConfigRepository.findAll();
        assertThat(kpiScoreCalConfigList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTemplateNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiScoreCalConfigRepository.findAll().size();
        // set the field null
        kpiScoreCalConfig.setTemplateName(null);

        // Create the KpiScoreCalConfig, which fails.

        restKpiScoreCalConfigMockMvc
            .perform(
                post("/api/kpi-score-cal-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreCalConfig))
            )
            .andExpect(status().isBadRequest());

        List<KpiScoreCalConfig> kpiScoreCalConfigList = kpiScoreCalConfigRepository.findAll();
        assertThat(kpiScoreCalConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKpiScoreCalConfigs() throws Exception {
        // Initialize the database
        kpiScoreCalConfigRepository.saveAndFlush(kpiScoreCalConfig);

        // Get all the kpiScoreCalConfigList
        restKpiScoreCalConfigMockMvc
            .perform(get("/api/kpi-score-cal-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiScoreCalConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].templateName").value(hasItem(DEFAULT_TEMPLATE_NAME)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiScoreCalConfig() throws Exception {
        // Initialize the database
        kpiScoreCalConfigRepository.saveAndFlush(kpiScoreCalConfig);

        // Get the kpiScoreCalConfig
        restKpiScoreCalConfigMockMvc
            .perform(get("/api/kpi-score-cal-configs/{id}", kpiScoreCalConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiScoreCalConfig.getId().intValue()))
            .andExpect(jsonPath("$.templateName").value(DEFAULT_TEMPLATE_NAME))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiScoreCalConfig() throws Exception {
        // Get the kpiScoreCalConfig
        restKpiScoreCalConfigMockMvc.perform(get("/api/kpi-score-cal-configs/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiScoreCalConfig() throws Exception {
        // Initialize the database
        kpiScoreCalConfigRepository.saveAndFlush(kpiScoreCalConfig);

        int databaseSizeBeforeUpdate = kpiScoreCalConfigRepository.findAll().size();

        // Update the kpiScoreCalConfig
        KpiScoreCalConfig updatedKpiScoreCalConfig = kpiScoreCalConfigRepository.findById(kpiScoreCalConfig.getId()).get();
        // Disconnect from session so that the updates on updatedKpiScoreCalConfig are not directly saved in db
        em.detach(updatedKpiScoreCalConfig);
        updatedKpiScoreCalConfig
            .templateName(UPDATED_TEMPLATE_NAME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiScoreCalConfigMockMvc
            .perform(
                put("/api/kpi-score-cal-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiScoreCalConfig))
            )
            .andExpect(status().isOk());

        // Validate the KpiScoreCalConfig in the database
        List<KpiScoreCalConfig> kpiScoreCalConfigList = kpiScoreCalConfigRepository.findAll();
        assertThat(kpiScoreCalConfigList).hasSize(databaseSizeBeforeUpdate);
        KpiScoreCalConfig testKpiScoreCalConfig = kpiScoreCalConfigList.get(kpiScoreCalConfigList.size() - 1);
        assertThat(testKpiScoreCalConfig.getTemplateName()).isEqualTo(UPDATED_TEMPLATE_NAME);
        assertThat(testKpiScoreCalConfig.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiScoreCalConfig.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiScoreCalConfig.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiScoreCalConfig.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiScoreCalConfig() throws Exception {
        int databaseSizeBeforeUpdate = kpiScoreCalConfigRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiScoreCalConfigMockMvc
            .perform(
                put("/api/kpi-score-cal-configs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreCalConfig))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiScoreCalConfig in the database
        List<KpiScoreCalConfig> kpiScoreCalConfigList = kpiScoreCalConfigRepository.findAll();
        assertThat(kpiScoreCalConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiScoreCalConfig() throws Exception {
        // Initialize the database
        kpiScoreCalConfigRepository.saveAndFlush(kpiScoreCalConfig);

        int databaseSizeBeforeDelete = kpiScoreCalConfigRepository.findAll().size();

        // Delete the kpiScoreCalConfig
        restKpiScoreCalConfigMockMvc
            .perform(delete("/api/kpi-score-cal-configs/{id}", kpiScoreCalConfig.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiScoreCalConfig> kpiScoreCalConfigList = kpiScoreCalConfigRepository.findAll();
        assertThat(kpiScoreCalConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
