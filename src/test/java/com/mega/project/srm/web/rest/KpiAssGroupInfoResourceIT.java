package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiAssGroupInfo;
import com.mega.project.srm.domain.enumeration.AssGroupStatus;
import com.mega.project.srm.repository.KpiAssGroupInfoRepository;
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
 * Integration tests for the {@link KpiAssGroupInfoResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiAssGroupInfoResourceIT {
    private static final String DEFAULT_KPI_CODE = "AAAAAAAAAA";
    private static final String UPDATED_KPI_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_SCORE = 1;
    private static final Integer UPDATED_TOTAL_SCORE = 2;

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final AssGroupStatus DEFAULT_STATUS = AssGroupStatus.ASSESSING;
    private static final AssGroupStatus UPDATED_STATUS = AssGroupStatus.COMPLETE;

    private static final String DEFAULT_GRADE = "AAAAAAAAAA";
    private static final String UPDATED_GRADE = "BBBBBBBBBB";

    private static final String DEFAULT_GRADE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_GRADE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiAssGroupInfoRepository kpiAssGroupInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiAssGroupInfoMockMvc;

    private KpiAssGroupInfo kpiAssGroupInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiAssGroupInfo createEntity(EntityManager em) {
        KpiAssGroupInfo kpiAssGroupInfo = new KpiAssGroupInfo()
            .kpiCode(DEFAULT_KPI_CODE)
            .totalScore(DEFAULT_TOTAL_SCORE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .status(DEFAULT_STATUS)
            .grade(DEFAULT_GRADE)
            .gradeDescription(DEFAULT_GRADE_DESCRIPTION)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiAssGroupInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiAssGroupInfo createUpdatedEntity(EntityManager em) {
        KpiAssGroupInfo kpiAssGroupInfo = new KpiAssGroupInfo()
            .kpiCode(UPDATED_KPI_CODE)
            .totalScore(UPDATED_TOTAL_SCORE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .status(UPDATED_STATUS)
            .grade(UPDATED_GRADE)
            .gradeDescription(UPDATED_GRADE_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiAssGroupInfo;
    }

    @BeforeEach
    public void initTest() {
        kpiAssGroupInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiAssGroupInfo() throws Exception {
        int databaseSizeBeforeCreate = kpiAssGroupInfoRepository.findAll().size();
        // Create the KpiAssGroupInfo
        restKpiAssGroupInfoMockMvc
            .perform(
                post("/api/kpi-ass-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssGroupInfo))
            )
            .andExpect(status().isCreated());

        // Validate the KpiAssGroupInfo in the database
        List<KpiAssGroupInfo> kpiAssGroupInfoList = kpiAssGroupInfoRepository.findAll();
        assertThat(kpiAssGroupInfoList).hasSize(databaseSizeBeforeCreate + 1);
        KpiAssGroupInfo testKpiAssGroupInfo = kpiAssGroupInfoList.get(kpiAssGroupInfoList.size() - 1);
        assertThat(testKpiAssGroupInfo.getKpiCode()).isEqualTo(DEFAULT_KPI_CODE);
        assertThat(testKpiAssGroupInfo.getTotalScore()).isEqualTo(DEFAULT_TOTAL_SCORE);
        assertThat(testKpiAssGroupInfo.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testKpiAssGroupInfo.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testKpiAssGroupInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testKpiAssGroupInfo.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testKpiAssGroupInfo.getGradeDescription()).isEqualTo(DEFAULT_GRADE_DESCRIPTION);
        assertThat(testKpiAssGroupInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiAssGroupInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiAssGroupInfo.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiAssGroupInfo.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiAssGroupInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiAssGroupInfoRepository.findAll().size();

        // Create the KpiAssGroupInfo with an existing ID
        kpiAssGroupInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiAssGroupInfoMockMvc
            .perform(
                post("/api/kpi-ass-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssGroupInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiAssGroupInfo in the database
        List<KpiAssGroupInfo> kpiAssGroupInfoList = kpiAssGroupInfoRepository.findAll();
        assertThat(kpiAssGroupInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkKpiCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssGroupInfoRepository.findAll().size();
        // set the field null
        kpiAssGroupInfo.setKpiCode(null);

        // Create the KpiAssGroupInfo, which fails.

        restKpiAssGroupInfoMockMvc
            .perform(
                post("/api/kpi-ass-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssGroupInfo))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssGroupInfo> kpiAssGroupInfoList = kpiAssGroupInfoRepository.findAll();
        assertThat(kpiAssGroupInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssGroupInfoRepository.findAll().size();
        // set the field null
        kpiAssGroupInfo.setStartDate(null);

        // Create the KpiAssGroupInfo, which fails.

        restKpiAssGroupInfoMockMvc
            .perform(
                post("/api/kpi-ass-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssGroupInfo))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssGroupInfo> kpiAssGroupInfoList = kpiAssGroupInfoRepository.findAll();
        assertThat(kpiAssGroupInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssGroupInfoRepository.findAll().size();
        // set the field null
        kpiAssGroupInfo.setEndDate(null);

        // Create the KpiAssGroupInfo, which fails.

        restKpiAssGroupInfoMockMvc
            .perform(
                post("/api/kpi-ass-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssGroupInfo))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssGroupInfo> kpiAssGroupInfoList = kpiAssGroupInfoRepository.findAll();
        assertThat(kpiAssGroupInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssGroupInfoRepository.findAll().size();
        // set the field null
        kpiAssGroupInfo.setStatus(null);

        // Create the KpiAssGroupInfo, which fails.

        restKpiAssGroupInfoMockMvc
            .perform(
                post("/api/kpi-ass-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssGroupInfo))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssGroupInfo> kpiAssGroupInfoList = kpiAssGroupInfoRepository.findAll();
        assertThat(kpiAssGroupInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKpiAssGroupInfos() throws Exception {
        // Initialize the database
        kpiAssGroupInfoRepository.saveAndFlush(kpiAssGroupInfo);

        // Get all the kpiAssGroupInfoList
        restKpiAssGroupInfoMockMvc
            .perform(get("/api/kpi-ass-group-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiAssGroupInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].kpiCode").value(hasItem(DEFAULT_KPI_CODE)))
            .andExpect(jsonPath("$.[*].totalScore").value(hasItem(DEFAULT_TOTAL_SCORE)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].gradeDescription").value(hasItem(DEFAULT_GRADE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiAssGroupInfo() throws Exception {
        // Initialize the database
        kpiAssGroupInfoRepository.saveAndFlush(kpiAssGroupInfo);

        // Get the kpiAssGroupInfo
        restKpiAssGroupInfoMockMvc
            .perform(get("/api/kpi-ass-group-infos/{id}", kpiAssGroupInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiAssGroupInfo.getId().intValue()))
            .andExpect(jsonPath("$.kpiCode").value(DEFAULT_KPI_CODE))
            .andExpect(jsonPath("$.totalScore").value(DEFAULT_TOTAL_SCORE))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.gradeDescription").value(DEFAULT_GRADE_DESCRIPTION))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiAssGroupInfo() throws Exception {
        // Get the kpiAssGroupInfo
        restKpiAssGroupInfoMockMvc.perform(get("/api/kpi-ass-group-infos/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiAssGroupInfo() throws Exception {
        // Initialize the database
        kpiAssGroupInfoRepository.saveAndFlush(kpiAssGroupInfo);

        int databaseSizeBeforeUpdate = kpiAssGroupInfoRepository.findAll().size();

        // Update the kpiAssGroupInfo
        KpiAssGroupInfo updatedKpiAssGroupInfo = kpiAssGroupInfoRepository.findById(kpiAssGroupInfo.getId()).get();
        // Disconnect from session so that the updates on updatedKpiAssGroupInfo are not directly saved in db
        em.detach(updatedKpiAssGroupInfo);
        updatedKpiAssGroupInfo
            .kpiCode(UPDATED_KPI_CODE)
            .totalScore(UPDATED_TOTAL_SCORE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .status(UPDATED_STATUS)
            .grade(UPDATED_GRADE)
            .gradeDescription(UPDATED_GRADE_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiAssGroupInfoMockMvc
            .perform(
                put("/api/kpi-ass-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiAssGroupInfo))
            )
            .andExpect(status().isOk());

        // Validate the KpiAssGroupInfo in the database
        List<KpiAssGroupInfo> kpiAssGroupInfoList = kpiAssGroupInfoRepository.findAll();
        assertThat(kpiAssGroupInfoList).hasSize(databaseSizeBeforeUpdate);
        KpiAssGroupInfo testKpiAssGroupInfo = kpiAssGroupInfoList.get(kpiAssGroupInfoList.size() - 1);
        assertThat(testKpiAssGroupInfo.getKpiCode()).isEqualTo(UPDATED_KPI_CODE);
        assertThat(testKpiAssGroupInfo.getTotalScore()).isEqualTo(UPDATED_TOTAL_SCORE);
        assertThat(testKpiAssGroupInfo.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testKpiAssGroupInfo.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testKpiAssGroupInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testKpiAssGroupInfo.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testKpiAssGroupInfo.getGradeDescription()).isEqualTo(UPDATED_GRADE_DESCRIPTION);
        assertThat(testKpiAssGroupInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiAssGroupInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiAssGroupInfo.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiAssGroupInfo.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiAssGroupInfo() throws Exception {
        int databaseSizeBeforeUpdate = kpiAssGroupInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiAssGroupInfoMockMvc
            .perform(
                put("/api/kpi-ass-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssGroupInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiAssGroupInfo in the database
        List<KpiAssGroupInfo> kpiAssGroupInfoList = kpiAssGroupInfoRepository.findAll();
        assertThat(kpiAssGroupInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiAssGroupInfo() throws Exception {
        // Initialize the database
        kpiAssGroupInfoRepository.saveAndFlush(kpiAssGroupInfo);

        int databaseSizeBeforeDelete = kpiAssGroupInfoRepository.findAll().size();

        // Delete the kpiAssGroupInfo
        restKpiAssGroupInfoMockMvc
            .perform(delete("/api/kpi-ass-group-infos/{id}", kpiAssGroupInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiAssGroupInfo> kpiAssGroupInfoList = kpiAssGroupInfoRepository.findAll();
        assertThat(kpiAssGroupInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
