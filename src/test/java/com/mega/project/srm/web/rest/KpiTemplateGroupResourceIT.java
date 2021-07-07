package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiTemplateGroup;
import com.mega.project.srm.repository.KpiTemplateGroupRepository;
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
 * Integration tests for the {@link KpiTemplateGroupResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiTemplateGroupResourceIT {
    private static final String DEFAULT_TEMPLATE_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiTemplateGroupRepository kpiTemplateGroupRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiTemplateGroupMockMvc;

    private KpiTemplateGroup kpiTemplateGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiTemplateGroup createEntity(EntityManager em) {
        KpiTemplateGroup kpiTemplateGroup = new KpiTemplateGroup()
            .templateGroupName(DEFAULT_TEMPLATE_GROUP_NAME)
            .description(DEFAULT_DESCRIPTION)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiTemplateGroup;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiTemplateGroup createUpdatedEntity(EntityManager em) {
        KpiTemplateGroup kpiTemplateGroup = new KpiTemplateGroup()
            .templateGroupName(UPDATED_TEMPLATE_GROUP_NAME)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiTemplateGroup;
    }

    @BeforeEach
    public void initTest() {
        kpiTemplateGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiTemplateGroup() throws Exception {
        int databaseSizeBeforeCreate = kpiTemplateGroupRepository.findAll().size();
        // Create the KpiTemplateGroup
        restKpiTemplateGroupMockMvc
            .perform(
                post("/api/kpi-template-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiTemplateGroup))
            )
            .andExpect(status().isCreated());

        // Validate the KpiTemplateGroup in the database
        List<KpiTemplateGroup> kpiTemplateGroupList = kpiTemplateGroupRepository.findAll();
        assertThat(kpiTemplateGroupList).hasSize(databaseSizeBeforeCreate + 1);
        KpiTemplateGroup testKpiTemplateGroup = kpiTemplateGroupList.get(kpiTemplateGroupList.size() - 1);
        assertThat(testKpiTemplateGroup.getTemplateGroupName()).isEqualTo(DEFAULT_TEMPLATE_GROUP_NAME);
        assertThat(testKpiTemplateGroup.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testKpiTemplateGroup.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testKpiTemplateGroup.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiTemplateGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiTemplateGroup.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiTemplateGroup.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiTemplateGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiTemplateGroupRepository.findAll().size();

        // Create the KpiTemplateGroup with an existing ID
        kpiTemplateGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiTemplateGroupMockMvc
            .perform(
                post("/api/kpi-template-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiTemplateGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiTemplateGroup in the database
        List<KpiTemplateGroup> kpiTemplateGroupList = kpiTemplateGroupRepository.findAll();
        assertThat(kpiTemplateGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTemplateGroupNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiTemplateGroupRepository.findAll().size();
        // set the field null
        kpiTemplateGroup.setTemplateGroupName(null);

        // Create the KpiTemplateGroup, which fails.

        restKpiTemplateGroupMockMvc
            .perform(
                post("/api/kpi-template-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiTemplateGroup))
            )
            .andExpect(status().isBadRequest());

        List<KpiTemplateGroup> kpiTemplateGroupList = kpiTemplateGroupRepository.findAll();
        assertThat(kpiTemplateGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKpiTemplateGroups() throws Exception {
        // Initialize the database
        kpiTemplateGroupRepository.saveAndFlush(kpiTemplateGroup);

        // Get all the kpiTemplateGroupList
        restKpiTemplateGroupMockMvc
            .perform(get("/api/kpi-template-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiTemplateGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].templateGroupName").value(hasItem(DEFAULT_TEMPLATE_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiTemplateGroup() throws Exception {
        // Initialize the database
        kpiTemplateGroupRepository.saveAndFlush(kpiTemplateGroup);

        // Get the kpiTemplateGroup
        restKpiTemplateGroupMockMvc
            .perform(get("/api/kpi-template-groups/{id}", kpiTemplateGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiTemplateGroup.getId().intValue()))
            .andExpect(jsonPath("$.templateGroupName").value(DEFAULT_TEMPLATE_GROUP_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiTemplateGroup() throws Exception {
        // Get the kpiTemplateGroup
        restKpiTemplateGroupMockMvc.perform(get("/api/kpi-template-groups/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiTemplateGroup() throws Exception {
        // Initialize the database
        kpiTemplateGroupRepository.saveAndFlush(kpiTemplateGroup);

        int databaseSizeBeforeUpdate = kpiTemplateGroupRepository.findAll().size();

        // Update the kpiTemplateGroup
        KpiTemplateGroup updatedKpiTemplateGroup = kpiTemplateGroupRepository.findById(kpiTemplateGroup.getId()).get();
        // Disconnect from session so that the updates on updatedKpiTemplateGroup are not directly saved in db
        em.detach(updatedKpiTemplateGroup);
        updatedKpiTemplateGroup
            .templateGroupName(UPDATED_TEMPLATE_GROUP_NAME)
            .description(UPDATED_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiTemplateGroupMockMvc
            .perform(
                put("/api/kpi-template-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiTemplateGroup))
            )
            .andExpect(status().isOk());

        // Validate the KpiTemplateGroup in the database
        List<KpiTemplateGroup> kpiTemplateGroupList = kpiTemplateGroupRepository.findAll();
        assertThat(kpiTemplateGroupList).hasSize(databaseSizeBeforeUpdate);
        KpiTemplateGroup testKpiTemplateGroup = kpiTemplateGroupList.get(kpiTemplateGroupList.size() - 1);
        assertThat(testKpiTemplateGroup.getTemplateGroupName()).isEqualTo(UPDATED_TEMPLATE_GROUP_NAME);
        assertThat(testKpiTemplateGroup.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testKpiTemplateGroup.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testKpiTemplateGroup.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiTemplateGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiTemplateGroup.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiTemplateGroup.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiTemplateGroup() throws Exception {
        int databaseSizeBeforeUpdate = kpiTemplateGroupRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiTemplateGroupMockMvc
            .perform(
                put("/api/kpi-template-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiTemplateGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiTemplateGroup in the database
        List<KpiTemplateGroup> kpiTemplateGroupList = kpiTemplateGroupRepository.findAll();
        assertThat(kpiTemplateGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiTemplateGroup() throws Exception {
        // Initialize the database
        kpiTemplateGroupRepository.saveAndFlush(kpiTemplateGroup);

        int databaseSizeBeforeDelete = kpiTemplateGroupRepository.findAll().size();

        // Delete the kpiTemplateGroup
        restKpiTemplateGroupMockMvc
            .perform(delete("/api/kpi-template-groups/{id}", kpiTemplateGroup.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiTemplateGroup> kpiTemplateGroupList = kpiTemplateGroupRepository.findAll();
        assertThat(kpiTemplateGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
