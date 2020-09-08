package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiAssTemplateGroup;
import com.mega.project.srm.repository.KpiAssTemplateGroupRepository;
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
 * Integration tests for the {@link KpiAssTemplateGroupResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiAssTemplateGroupResourceIT {
    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_TEMPLATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiAssTemplateGroupRepository kpiAssTemplateGroupRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiAssTemplateGroupMockMvc;

    private KpiAssTemplateGroup kpiAssTemplateGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiAssTemplateGroup createEntity(EntityManager em) {
        KpiAssTemplateGroup kpiAssTemplateGroup = new KpiAssTemplateGroup()
            .score(DEFAULT_SCORE)
            .weight(DEFAULT_WEIGHT)
            .templateName(DEFAULT_TEMPLATE_NAME)
            .roleName(DEFAULT_ROLE_NAME)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiAssTemplateGroup;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiAssTemplateGroup createUpdatedEntity(EntityManager em) {
        KpiAssTemplateGroup kpiAssTemplateGroup = new KpiAssTemplateGroup()
            .score(UPDATED_SCORE)
            .weight(UPDATED_WEIGHT)
            .templateName(UPDATED_TEMPLATE_NAME)
            .roleName(UPDATED_ROLE_NAME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiAssTemplateGroup;
    }

    @BeforeEach
    public void initTest() {
        kpiAssTemplateGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiAssTemplateGroup() throws Exception {
        int databaseSizeBeforeCreate = kpiAssTemplateGroupRepository.findAll().size();
        // Create the KpiAssTemplateGroup
        restKpiAssTemplateGroupMockMvc
            .perform(
                post("/api/kpi-ass-template-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplateGroup))
            )
            .andExpect(status().isCreated());

        // Validate the KpiAssTemplateGroup in the database
        List<KpiAssTemplateGroup> kpiAssTemplateGroupList = kpiAssTemplateGroupRepository.findAll();
        assertThat(kpiAssTemplateGroupList).hasSize(databaseSizeBeforeCreate + 1);
        KpiAssTemplateGroup testKpiAssTemplateGroup = kpiAssTemplateGroupList.get(kpiAssTemplateGroupList.size() - 1);
        assertThat(testKpiAssTemplateGroup.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testKpiAssTemplateGroup.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testKpiAssTemplateGroup.getTemplateName()).isEqualTo(DEFAULT_TEMPLATE_NAME);
        assertThat(testKpiAssTemplateGroup.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
        assertThat(testKpiAssTemplateGroup.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiAssTemplateGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiAssTemplateGroup.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiAssTemplateGroup.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiAssTemplateGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiAssTemplateGroupRepository.findAll().size();

        // Create the KpiAssTemplateGroup with an existing ID
        kpiAssTemplateGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiAssTemplateGroupMockMvc
            .perform(
                post("/api/kpi-ass-template-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplateGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiAssTemplateGroup in the database
        List<KpiAssTemplateGroup> kpiAssTemplateGroupList = kpiAssTemplateGroupRepository.findAll();
        assertThat(kpiAssTemplateGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssTemplateGroupRepository.findAll().size();
        // set the field null
        kpiAssTemplateGroup.setWeight(null);

        // Create the KpiAssTemplateGroup, which fails.

        restKpiAssTemplateGroupMockMvc
            .perform(
                post("/api/kpi-ass-template-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplateGroup))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssTemplateGroup> kpiAssTemplateGroupList = kpiAssTemplateGroupRepository.findAll();
        assertThat(kpiAssTemplateGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKpiAssTemplateGroups() throws Exception {
        // Initialize the database
        kpiAssTemplateGroupRepository.saveAndFlush(kpiAssTemplateGroup);

        // Get all the kpiAssTemplateGroupList
        restKpiAssTemplateGroupMockMvc
            .perform(get("/api/kpi-ass-template-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiAssTemplateGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].templateName").value(hasItem(DEFAULT_TEMPLATE_NAME)))
            .andExpect(jsonPath("$.[*].roleName").value(hasItem(DEFAULT_ROLE_NAME)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiAssTemplateGroup() throws Exception {
        // Initialize the database
        kpiAssTemplateGroupRepository.saveAndFlush(kpiAssTemplateGroup);

        // Get the kpiAssTemplateGroup
        restKpiAssTemplateGroupMockMvc
            .perform(get("/api/kpi-ass-template-groups/{id}", kpiAssTemplateGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiAssTemplateGroup.getId().intValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.templateName").value(DEFAULT_TEMPLATE_NAME))
            .andExpect(jsonPath("$.roleName").value(DEFAULT_ROLE_NAME))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiAssTemplateGroup() throws Exception {
        // Get the kpiAssTemplateGroup
        restKpiAssTemplateGroupMockMvc.perform(get("/api/kpi-ass-template-groups/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiAssTemplateGroup() throws Exception {
        // Initialize the database
        kpiAssTemplateGroupRepository.saveAndFlush(kpiAssTemplateGroup);

        int databaseSizeBeforeUpdate = kpiAssTemplateGroupRepository.findAll().size();

        // Update the kpiAssTemplateGroup
        KpiAssTemplateGroup updatedKpiAssTemplateGroup = kpiAssTemplateGroupRepository.findById(kpiAssTemplateGroup.getId()).get();
        // Disconnect from session so that the updates on updatedKpiAssTemplateGroup are not directly saved in db
        em.detach(updatedKpiAssTemplateGroup);
        updatedKpiAssTemplateGroup
            .score(UPDATED_SCORE)
            .weight(UPDATED_WEIGHT)
            .templateName(UPDATED_TEMPLATE_NAME)
            .roleName(UPDATED_ROLE_NAME)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiAssTemplateGroupMockMvc
            .perform(
                put("/api/kpi-ass-template-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiAssTemplateGroup))
            )
            .andExpect(status().isOk());

        // Validate the KpiAssTemplateGroup in the database
        List<KpiAssTemplateGroup> kpiAssTemplateGroupList = kpiAssTemplateGroupRepository.findAll();
        assertThat(kpiAssTemplateGroupList).hasSize(databaseSizeBeforeUpdate);
        KpiAssTemplateGroup testKpiAssTemplateGroup = kpiAssTemplateGroupList.get(kpiAssTemplateGroupList.size() - 1);
        assertThat(testKpiAssTemplateGroup.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testKpiAssTemplateGroup.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testKpiAssTemplateGroup.getTemplateName()).isEqualTo(UPDATED_TEMPLATE_NAME);
        assertThat(testKpiAssTemplateGroup.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testKpiAssTemplateGroup.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiAssTemplateGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiAssTemplateGroup.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiAssTemplateGroup.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiAssTemplateGroup() throws Exception {
        int databaseSizeBeforeUpdate = kpiAssTemplateGroupRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiAssTemplateGroupMockMvc
            .perform(
                put("/api/kpi-ass-template-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplateGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiAssTemplateGroup in the database
        List<KpiAssTemplateGroup> kpiAssTemplateGroupList = kpiAssTemplateGroupRepository.findAll();
        assertThat(kpiAssTemplateGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiAssTemplateGroup() throws Exception {
        // Initialize the database
        kpiAssTemplateGroupRepository.saveAndFlush(kpiAssTemplateGroup);

        int databaseSizeBeforeDelete = kpiAssTemplateGroupRepository.findAll().size();

        // Delete the kpiAssTemplateGroup
        restKpiAssTemplateGroupMockMvc
            .perform(delete("/api/kpi-ass-template-groups/{id}", kpiAssTemplateGroup.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiAssTemplateGroup> kpiAssTemplateGroupList = kpiAssTemplateGroupRepository.findAll();
        assertThat(kpiAssTemplateGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
