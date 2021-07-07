package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiAssTemplate;
import com.mega.project.srm.repository.KpiAssTemplateRepository;
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
 * Integration tests for the {@link KpiAssTemplateResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiAssTemplateResourceIT {
    private static final String DEFAULT_TEMPLATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiAssTemplateRepository kpiAssTemplateRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiAssTemplateMockMvc;

    private KpiAssTemplate kpiAssTemplate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiAssTemplate createEntity(EntityManager em) {
        KpiAssTemplate kpiAssTemplate = new KpiAssTemplate()
            .templateName(DEFAULT_TEMPLATE_NAME)
            .filePath(DEFAULT_FILE_PATH)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiAssTemplate;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiAssTemplate createUpdatedEntity(EntityManager em) {
        KpiAssTemplate kpiAssTemplate = new KpiAssTemplate()
            .templateName(UPDATED_TEMPLATE_NAME)
            .filePath(UPDATED_FILE_PATH)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiAssTemplate;
    }

    @BeforeEach
    public void initTest() {
        kpiAssTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiAssTemplate() throws Exception {
        int databaseSizeBeforeCreate = kpiAssTemplateRepository.findAll().size();
        // Create the KpiAssTemplate
        restKpiAssTemplateMockMvc
            .perform(
                post("/api/kpi-ass-templates")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplate))
            )
            .andExpect(status().isCreated());

        // Validate the KpiAssTemplate in the database
        List<KpiAssTemplate> kpiAssTemplateList = kpiAssTemplateRepository.findAll();
        assertThat(kpiAssTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        KpiAssTemplate testKpiAssTemplate = kpiAssTemplateList.get(kpiAssTemplateList.size() - 1);
        assertThat(testKpiAssTemplate.getTemplateName()).isEqualTo(DEFAULT_TEMPLATE_NAME);
        assertThat(testKpiAssTemplate.getFilePath()).isEqualTo(DEFAULT_FILE_PATH);
        assertThat(testKpiAssTemplate.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiAssTemplate.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiAssTemplate.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiAssTemplate.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiAssTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiAssTemplateRepository.findAll().size();

        // Create the KpiAssTemplate with an existing ID
        kpiAssTemplate.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiAssTemplateMockMvc
            .perform(
                post("/api/kpi-ass-templates")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplate))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiAssTemplate in the database
        List<KpiAssTemplate> kpiAssTemplateList = kpiAssTemplateRepository.findAll();
        assertThat(kpiAssTemplateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKpiAssTemplates() throws Exception {
        // Initialize the database
        kpiAssTemplateRepository.saveAndFlush(kpiAssTemplate);

        // Get all the kpiAssTemplateList
        restKpiAssTemplateMockMvc
            .perform(get("/api/kpi-ass-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiAssTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].templateName").value(hasItem(DEFAULT_TEMPLATE_NAME)))
            .andExpect(jsonPath("$.[*].filePath").value(hasItem(DEFAULT_FILE_PATH)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiAssTemplate() throws Exception {
        // Initialize the database
        kpiAssTemplateRepository.saveAndFlush(kpiAssTemplate);

        // Get the kpiAssTemplate
        restKpiAssTemplateMockMvc
            .perform(get("/api/kpi-ass-templates/{id}", kpiAssTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiAssTemplate.getId().intValue()))
            .andExpect(jsonPath("$.templateName").value(DEFAULT_TEMPLATE_NAME))
            .andExpect(jsonPath("$.filePath").value(DEFAULT_FILE_PATH))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiAssTemplate() throws Exception {
        // Get the kpiAssTemplate
        restKpiAssTemplateMockMvc.perform(get("/api/kpi-ass-templates/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiAssTemplate() throws Exception {
        // Initialize the database
        kpiAssTemplateRepository.saveAndFlush(kpiAssTemplate);

        int databaseSizeBeforeUpdate = kpiAssTemplateRepository.findAll().size();

        // Update the kpiAssTemplate
        KpiAssTemplate updatedKpiAssTemplate = kpiAssTemplateRepository.findById(kpiAssTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedKpiAssTemplate are not directly saved in db
        em.detach(updatedKpiAssTemplate);
        updatedKpiAssTemplate
            .templateName(UPDATED_TEMPLATE_NAME)
            .filePath(UPDATED_FILE_PATH)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiAssTemplateMockMvc
            .perform(
                put("/api/kpi-ass-templates")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiAssTemplate))
            )
            .andExpect(status().isOk());

        // Validate the KpiAssTemplate in the database
        List<KpiAssTemplate> kpiAssTemplateList = kpiAssTemplateRepository.findAll();
        assertThat(kpiAssTemplateList).hasSize(databaseSizeBeforeUpdate);
        KpiAssTemplate testKpiAssTemplate = kpiAssTemplateList.get(kpiAssTemplateList.size() - 1);
        assertThat(testKpiAssTemplate.getTemplateName()).isEqualTo(UPDATED_TEMPLATE_NAME);
        assertThat(testKpiAssTemplate.getFilePath()).isEqualTo(UPDATED_FILE_PATH);
        assertThat(testKpiAssTemplate.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiAssTemplate.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiAssTemplate.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiAssTemplate.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiAssTemplate() throws Exception {
        int databaseSizeBeforeUpdate = kpiAssTemplateRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiAssTemplateMockMvc
            .perform(
                put("/api/kpi-ass-templates")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplate))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiAssTemplate in the database
        List<KpiAssTemplate> kpiAssTemplateList = kpiAssTemplateRepository.findAll();
        assertThat(kpiAssTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiAssTemplate() throws Exception {
        // Initialize the database
        kpiAssTemplateRepository.saveAndFlush(kpiAssTemplate);

        int databaseSizeBeforeDelete = kpiAssTemplateRepository.findAll().size();

        // Delete the kpiAssTemplate
        restKpiAssTemplateMockMvc
            .perform(delete("/api/kpi-ass-templates/{id}", kpiAssTemplate.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiAssTemplate> kpiAssTemplateList = kpiAssTemplateRepository.findAll();
        assertThat(kpiAssTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
