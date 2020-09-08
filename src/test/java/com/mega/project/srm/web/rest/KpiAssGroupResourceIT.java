package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiAssGroup;
import com.mega.project.srm.domain.enumeration.AssGroupStatus;
import com.mega.project.srm.repository.KpiAssGroupRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link KpiAssGroupResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiAssGroupResourceIT {
    private static final String DEFAULT_AG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_AG_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_AG_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_AG_YEAR = "BBBBBBBBBB";

    private static final String DEFAULT_AG_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_AG_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_AG_PERIOD = "AAAAAAAAAA";
    private static final String UPDATED_AG_PERIOD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final AssGroupStatus DEFAULT_STATUS = AssGroupStatus.ASSESSING;
    private static final AssGroupStatus UPDATED_STATUS = AssGroupStatus.COMPLETE;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiAssGroupRepository kpiAssGroupRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiAssGroupMockMvc;

    private KpiAssGroup kpiAssGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiAssGroup createEntity(EntityManager em) {
        KpiAssGroup kpiAssGroup = new KpiAssGroup()
            .agCode(DEFAULT_AG_CODE)
            .agYear(DEFAULT_AG_YEAR)
            .agType(DEFAULT_AG_TYPE)
            .agPeriod(DEFAULT_AG_PERIOD)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .status(DEFAULT_STATUS)
            .remark(DEFAULT_REMARK)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiAssGroup;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiAssGroup createUpdatedEntity(EntityManager em) {
        KpiAssGroup kpiAssGroup = new KpiAssGroup()
            .agCode(UPDATED_AG_CODE)
            .agYear(UPDATED_AG_YEAR)
            .agType(UPDATED_AG_TYPE)
            .agPeriod(UPDATED_AG_PERIOD)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .status(UPDATED_STATUS)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiAssGroup;
    }

    @BeforeEach
    public void initTest() {
        kpiAssGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiAssGroup() throws Exception {
        int databaseSizeBeforeCreate = kpiAssGroupRepository.findAll().size();
        // Create the KpiAssGroup
        restKpiAssGroupMockMvc
            .perform(
                post("/api/kpi-ass-groups").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiAssGroup))
            )
            .andExpect(status().isCreated());

        // Validate the KpiAssGroup in the database
        List<KpiAssGroup> kpiAssGroupList = kpiAssGroupRepository.findAll();
        assertThat(kpiAssGroupList).hasSize(databaseSizeBeforeCreate + 1);
        KpiAssGroup testKpiAssGroup = kpiAssGroupList.get(kpiAssGroupList.size() - 1);
        assertThat(testKpiAssGroup.getAgCode()).isEqualTo(DEFAULT_AG_CODE);
        assertThat(testKpiAssGroup.getAgYear()).isEqualTo(DEFAULT_AG_YEAR);
        assertThat(testKpiAssGroup.getAgType()).isEqualTo(DEFAULT_AG_TYPE);
        assertThat(testKpiAssGroup.getAgPeriod()).isEqualTo(DEFAULT_AG_PERIOD);
        assertThat(testKpiAssGroup.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testKpiAssGroup.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testKpiAssGroup.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testKpiAssGroup.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testKpiAssGroup.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiAssGroup.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiAssGroup.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiAssGroup.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiAssGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiAssGroupRepository.findAll().size();

        // Create the KpiAssGroup with an existing ID
        kpiAssGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiAssGroupMockMvc
            .perform(
                post("/api/kpi-ass-groups").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiAssGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiAssGroup in the database
        List<KpiAssGroup> kpiAssGroupList = kpiAssGroupRepository.findAll();
        assertThat(kpiAssGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAgCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssGroupRepository.findAll().size();
        // set the field null
        kpiAssGroup.setAgCode(null);

        // Create the KpiAssGroup, which fails.

        restKpiAssGroupMockMvc
            .perform(
                post("/api/kpi-ass-groups").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiAssGroup))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssGroup> kpiAssGroupList = kpiAssGroupRepository.findAll();
        assertThat(kpiAssGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssGroupRepository.findAll().size();
        // set the field null
        kpiAssGroup.setAgYear(null);

        // Create the KpiAssGroup, which fails.

        restKpiAssGroupMockMvc
            .perform(
                post("/api/kpi-ass-groups").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiAssGroup))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssGroup> kpiAssGroupList = kpiAssGroupRepository.findAll();
        assertThat(kpiAssGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssGroupRepository.findAll().size();
        // set the field null
        kpiAssGroup.setAgType(null);

        // Create the KpiAssGroup, which fails.

        restKpiAssGroupMockMvc
            .perform(
                post("/api/kpi-ass-groups").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiAssGroup))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssGroup> kpiAssGroupList = kpiAssGroupRepository.findAll();
        assertThat(kpiAssGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssGroupRepository.findAll().size();
        // set the field null
        kpiAssGroup.setStartDate(null);

        // Create the KpiAssGroup, which fails.

        restKpiAssGroupMockMvc
            .perform(
                post("/api/kpi-ass-groups").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiAssGroup))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssGroup> kpiAssGroupList = kpiAssGroupRepository.findAll();
        assertThat(kpiAssGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssGroupRepository.findAll().size();
        // set the field null
        kpiAssGroup.setEndDate(null);

        // Create the KpiAssGroup, which fails.

        restKpiAssGroupMockMvc
            .perform(
                post("/api/kpi-ass-groups").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiAssGroup))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssGroup> kpiAssGroupList = kpiAssGroupRepository.findAll();
        assertThat(kpiAssGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssGroupRepository.findAll().size();
        // set the field null
        kpiAssGroup.setStatus(null);

        // Create the KpiAssGroup, which fails.

        restKpiAssGroupMockMvc
            .perform(
                post("/api/kpi-ass-groups").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiAssGroup))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssGroup> kpiAssGroupList = kpiAssGroupRepository.findAll();
        assertThat(kpiAssGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKpiAssGroups() throws Exception {
        // Initialize the database
        kpiAssGroupRepository.saveAndFlush(kpiAssGroup);

        // Get all the kpiAssGroupList
        restKpiAssGroupMockMvc
            .perform(get("/api/kpi-ass-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiAssGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].agCode").value(hasItem(DEFAULT_AG_CODE)))
            .andExpect(jsonPath("$.[*].agYear").value(hasItem(DEFAULT_AG_YEAR)))
            .andExpect(jsonPath("$.[*].agType").value(hasItem(DEFAULT_AG_TYPE)))
            .andExpect(jsonPath("$.[*].agPeriod").value(hasItem(DEFAULT_AG_PERIOD)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiAssGroup() throws Exception {
        // Initialize the database
        kpiAssGroupRepository.saveAndFlush(kpiAssGroup);

        // Get the kpiAssGroup
        restKpiAssGroupMockMvc
            .perform(get("/api/kpi-ass-groups/{id}", kpiAssGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiAssGroup.getId().intValue()))
            .andExpect(jsonPath("$.agCode").value(DEFAULT_AG_CODE))
            .andExpect(jsonPath("$.agYear").value(DEFAULT_AG_YEAR))
            .andExpect(jsonPath("$.agType").value(DEFAULT_AG_TYPE))
            .andExpect(jsonPath("$.agPeriod").value(DEFAULT_AG_PERIOD))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiAssGroup() throws Exception {
        // Get the kpiAssGroup
        restKpiAssGroupMockMvc.perform(get("/api/kpi-ass-groups/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiAssGroup() throws Exception {
        // Initialize the database
        kpiAssGroupRepository.saveAndFlush(kpiAssGroup);

        int databaseSizeBeforeUpdate = kpiAssGroupRepository.findAll().size();

        // Update the kpiAssGroup
        KpiAssGroup updatedKpiAssGroup = kpiAssGroupRepository.findById(kpiAssGroup.getId()).get();
        // Disconnect from session so that the updates on updatedKpiAssGroup are not directly saved in db
        em.detach(updatedKpiAssGroup);
        updatedKpiAssGroup
            .agCode(UPDATED_AG_CODE)
            .agYear(UPDATED_AG_YEAR)
            .agType(UPDATED_AG_TYPE)
            .agPeriod(UPDATED_AG_PERIOD)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .status(UPDATED_STATUS)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiAssGroupMockMvc
            .perform(
                put("/api/kpi-ass-groups")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiAssGroup))
            )
            .andExpect(status().isOk());

        // Validate the KpiAssGroup in the database
        List<KpiAssGroup> kpiAssGroupList = kpiAssGroupRepository.findAll();
        assertThat(kpiAssGroupList).hasSize(databaseSizeBeforeUpdate);
        KpiAssGroup testKpiAssGroup = kpiAssGroupList.get(kpiAssGroupList.size() - 1);
        assertThat(testKpiAssGroup.getAgCode()).isEqualTo(UPDATED_AG_CODE);
        assertThat(testKpiAssGroup.getAgYear()).isEqualTo(UPDATED_AG_YEAR);
        assertThat(testKpiAssGroup.getAgType()).isEqualTo(UPDATED_AG_TYPE);
        assertThat(testKpiAssGroup.getAgPeriod()).isEqualTo(UPDATED_AG_PERIOD);
        assertThat(testKpiAssGroup.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testKpiAssGroup.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testKpiAssGroup.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testKpiAssGroup.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testKpiAssGroup.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiAssGroup.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiAssGroup.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiAssGroup.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiAssGroup() throws Exception {
        int databaseSizeBeforeUpdate = kpiAssGroupRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiAssGroupMockMvc
            .perform(
                put("/api/kpi-ass-groups").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiAssGroup))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiAssGroup in the database
        List<KpiAssGroup> kpiAssGroupList = kpiAssGroupRepository.findAll();
        assertThat(kpiAssGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiAssGroup() throws Exception {
        // Initialize the database
        kpiAssGroupRepository.saveAndFlush(kpiAssGroup);

        int databaseSizeBeforeDelete = kpiAssGroupRepository.findAll().size();

        // Delete the kpiAssGroup
        restKpiAssGroupMockMvc
            .perform(delete("/api/kpi-ass-groups/{id}", kpiAssGroup.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiAssGroup> kpiAssGroupList = kpiAssGroupRepository.findAll();
        assertThat(kpiAssGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
