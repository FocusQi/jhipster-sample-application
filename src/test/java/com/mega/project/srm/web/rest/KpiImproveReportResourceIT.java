package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiImproveReport;
import com.mega.project.srm.domain.enumeration.ImproveReportStatus;
import com.mega.project.srm.domain.enumeration.ImproveType;
import com.mega.project.srm.repository.KpiImproveReportRepository;
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
 * Integration tests for the {@link KpiImproveReportResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiImproveReportResourceIT {
    private static final ImproveType DEFAULT_TYPE = ImproveType.NORMAL;
    private static final ImproveType UPDATED_TYPE = ImproveType.ABNORMAL;

    private static final LocalDate DEFAULT_APPROVAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPROVAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_APPROVAL_TIMES = 1;
    private static final Integer UPDATED_APPROVAL_TIMES = 2;

    private static final LocalDate DEFAULT_CREATE_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_VALID_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VALID_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ImproveReportStatus DEFAULT_STATUS = ImproveReportStatus.NOT_SEND;
    private static final ImproveReportStatus UPDATED_STATUS = ImproveReportStatus.REPLY;

    private static final String DEFAULT_TEMPLATE_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_FILE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_ABNORMAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ABNORMAL_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ABNORMAL_CREATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ABNORMAL_CREATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ABNORMAL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ABNORMAL_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ABNORMAL_GRADE = "AAAAAAAAAA";
    private static final String UPDATED_ABNORMAL_GRADE = "BBBBBBBBBB";

    private static final String DEFAULT_ABNORMAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ABNORMAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ABNORMAL_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_ABNORMAL_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiImproveReportRepository kpiImproveReportRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiImproveReportMockMvc;

    private KpiImproveReport kpiImproveReport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiImproveReport createEntity(EntityManager em) {
        KpiImproveReport kpiImproveReport = new KpiImproveReport()
            .type(DEFAULT_TYPE)
            .approvalDate(DEFAULT_APPROVAL_DATE)
            .approvalTimes(DEFAULT_APPROVAL_TIMES)
            .createTime(DEFAULT_CREATE_TIME)
            .validDate(DEFAULT_VALID_DATE)
            .status(DEFAULT_STATUS)
            .templateFilePath(DEFAULT_TEMPLATE_FILE_PATH)
            .abnormalCode(DEFAULT_ABNORMAL_CODE)
            .abnormalCreateDate(DEFAULT_ABNORMAL_CREATE_DATE)
            .abnormalType(DEFAULT_ABNORMAL_TYPE)
            .abnormalGrade(DEFAULT_ABNORMAL_GRADE)
            .abnormalName(DEFAULT_ABNORMAL_NAME)
            .abnormalContent(DEFAULT_ABNORMAL_CONTENT)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiImproveReport;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiImproveReport createUpdatedEntity(EntityManager em) {
        KpiImproveReport kpiImproveReport = new KpiImproveReport()
            .type(UPDATED_TYPE)
            .approvalDate(UPDATED_APPROVAL_DATE)
            .approvalTimes(UPDATED_APPROVAL_TIMES)
            .createTime(UPDATED_CREATE_TIME)
            .validDate(UPDATED_VALID_DATE)
            .status(UPDATED_STATUS)
            .templateFilePath(UPDATED_TEMPLATE_FILE_PATH)
            .abnormalCode(UPDATED_ABNORMAL_CODE)
            .abnormalCreateDate(UPDATED_ABNORMAL_CREATE_DATE)
            .abnormalType(UPDATED_ABNORMAL_TYPE)
            .abnormalGrade(UPDATED_ABNORMAL_GRADE)
            .abnormalName(UPDATED_ABNORMAL_NAME)
            .abnormalContent(UPDATED_ABNORMAL_CONTENT)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiImproveReport;
    }

    @BeforeEach
    public void initTest() {
        kpiImproveReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiImproveReport() throws Exception {
        int databaseSizeBeforeCreate = kpiImproveReportRepository.findAll().size();
        // Create the KpiImproveReport
        restKpiImproveReportMockMvc
            .perform(
                post("/api/kpi-improve-reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiImproveReport))
            )
            .andExpect(status().isCreated());

        // Validate the KpiImproveReport in the database
        List<KpiImproveReport> kpiImproveReportList = kpiImproveReportRepository.findAll();
        assertThat(kpiImproveReportList).hasSize(databaseSizeBeforeCreate + 1);
        KpiImproveReport testKpiImproveReport = kpiImproveReportList.get(kpiImproveReportList.size() - 1);
        assertThat(testKpiImproveReport.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testKpiImproveReport.getApprovalDate()).isEqualTo(DEFAULT_APPROVAL_DATE);
        assertThat(testKpiImproveReport.getApprovalTimes()).isEqualTo(DEFAULT_APPROVAL_TIMES);
        assertThat(testKpiImproveReport.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testKpiImproveReport.getValidDate()).isEqualTo(DEFAULT_VALID_DATE);
        assertThat(testKpiImproveReport.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testKpiImproveReport.getTemplateFilePath()).isEqualTo(DEFAULT_TEMPLATE_FILE_PATH);
        assertThat(testKpiImproveReport.getAbnormalCode()).isEqualTo(DEFAULT_ABNORMAL_CODE);
        assertThat(testKpiImproveReport.getAbnormalCreateDate()).isEqualTo(DEFAULT_ABNORMAL_CREATE_DATE);
        assertThat(testKpiImproveReport.getAbnormalType()).isEqualTo(DEFAULT_ABNORMAL_TYPE);
        assertThat(testKpiImproveReport.getAbnormalGrade()).isEqualTo(DEFAULT_ABNORMAL_GRADE);
        assertThat(testKpiImproveReport.getAbnormalName()).isEqualTo(DEFAULT_ABNORMAL_NAME);
        assertThat(testKpiImproveReport.getAbnormalContent()).isEqualTo(DEFAULT_ABNORMAL_CONTENT);
        assertThat(testKpiImproveReport.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiImproveReport.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiImproveReport.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiImproveReport.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiImproveReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiImproveReportRepository.findAll().size();

        // Create the KpiImproveReport with an existing ID
        kpiImproveReport.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiImproveReportMockMvc
            .perform(
                post("/api/kpi-improve-reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiImproveReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiImproveReport in the database
        List<KpiImproveReport> kpiImproveReportList = kpiImproveReportRepository.findAll();
        assertThat(kpiImproveReportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiImproveReportRepository.findAll().size();
        // set the field null
        kpiImproveReport.setType(null);

        // Create the KpiImproveReport, which fails.

        restKpiImproveReportMockMvc
            .perform(
                post("/api/kpi-improve-reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiImproveReport))
            )
            .andExpect(status().isBadRequest());

        List<KpiImproveReport> kpiImproveReportList = kpiImproveReportRepository.findAll();
        assertThat(kpiImproveReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApprovalTimesIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiImproveReportRepository.findAll().size();
        // set the field null
        kpiImproveReport.setApprovalTimes(null);

        // Create the KpiImproveReport, which fails.

        restKpiImproveReportMockMvc
            .perform(
                post("/api/kpi-improve-reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiImproveReport))
            )
            .andExpect(status().isBadRequest());

        List<KpiImproveReport> kpiImproveReportList = kpiImproveReportRepository.findAll();
        assertThat(kpiImproveReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiImproveReportRepository.findAll().size();
        // set the field null
        kpiImproveReport.setStatus(null);

        // Create the KpiImproveReport, which fails.

        restKpiImproveReportMockMvc
            .perform(
                post("/api/kpi-improve-reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiImproveReport))
            )
            .andExpect(status().isBadRequest());

        List<KpiImproveReport> kpiImproveReportList = kpiImproveReportRepository.findAll();
        assertThat(kpiImproveReportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKpiImproveReports() throws Exception {
        // Initialize the database
        kpiImproveReportRepository.saveAndFlush(kpiImproveReport);

        // Get all the kpiImproveReportList
        restKpiImproveReportMockMvc
            .perform(get("/api/kpi-improve-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiImproveReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].approvalDate").value(hasItem(DEFAULT_APPROVAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].approvalTimes").value(hasItem(DEFAULT_APPROVAL_TIMES)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].validDate").value(hasItem(DEFAULT_VALID_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].templateFilePath").value(hasItem(DEFAULT_TEMPLATE_FILE_PATH)))
            .andExpect(jsonPath("$.[*].abnormalCode").value(hasItem(DEFAULT_ABNORMAL_CODE)))
            .andExpect(jsonPath("$.[*].abnormalCreateDate").value(hasItem(DEFAULT_ABNORMAL_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].abnormalType").value(hasItem(DEFAULT_ABNORMAL_TYPE)))
            .andExpect(jsonPath("$.[*].abnormalGrade").value(hasItem(DEFAULT_ABNORMAL_GRADE)))
            .andExpect(jsonPath("$.[*].abnormalName").value(hasItem(DEFAULT_ABNORMAL_NAME)))
            .andExpect(jsonPath("$.[*].abnormalContent").value(hasItem(DEFAULT_ABNORMAL_CONTENT)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiImproveReport() throws Exception {
        // Initialize the database
        kpiImproveReportRepository.saveAndFlush(kpiImproveReport);

        // Get the kpiImproveReport
        restKpiImproveReportMockMvc
            .perform(get("/api/kpi-improve-reports/{id}", kpiImproveReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiImproveReport.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.approvalDate").value(DEFAULT_APPROVAL_DATE.toString()))
            .andExpect(jsonPath("$.approvalTimes").value(DEFAULT_APPROVAL_TIMES))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.validDate").value(DEFAULT_VALID_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.templateFilePath").value(DEFAULT_TEMPLATE_FILE_PATH))
            .andExpect(jsonPath("$.abnormalCode").value(DEFAULT_ABNORMAL_CODE))
            .andExpect(jsonPath("$.abnormalCreateDate").value(DEFAULT_ABNORMAL_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.abnormalType").value(DEFAULT_ABNORMAL_TYPE))
            .andExpect(jsonPath("$.abnormalGrade").value(DEFAULT_ABNORMAL_GRADE))
            .andExpect(jsonPath("$.abnormalName").value(DEFAULT_ABNORMAL_NAME))
            .andExpect(jsonPath("$.abnormalContent").value(DEFAULT_ABNORMAL_CONTENT))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiImproveReport() throws Exception {
        // Get the kpiImproveReport
        restKpiImproveReportMockMvc.perform(get("/api/kpi-improve-reports/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiImproveReport() throws Exception {
        // Initialize the database
        kpiImproveReportRepository.saveAndFlush(kpiImproveReport);

        int databaseSizeBeforeUpdate = kpiImproveReportRepository.findAll().size();

        // Update the kpiImproveReport
        KpiImproveReport updatedKpiImproveReport = kpiImproveReportRepository.findById(kpiImproveReport.getId()).get();
        // Disconnect from session so that the updates on updatedKpiImproveReport are not directly saved in db
        em.detach(updatedKpiImproveReport);
        updatedKpiImproveReport
            .type(UPDATED_TYPE)
            .approvalDate(UPDATED_APPROVAL_DATE)
            .approvalTimes(UPDATED_APPROVAL_TIMES)
            .createTime(UPDATED_CREATE_TIME)
            .validDate(UPDATED_VALID_DATE)
            .status(UPDATED_STATUS)
            .templateFilePath(UPDATED_TEMPLATE_FILE_PATH)
            .abnormalCode(UPDATED_ABNORMAL_CODE)
            .abnormalCreateDate(UPDATED_ABNORMAL_CREATE_DATE)
            .abnormalType(UPDATED_ABNORMAL_TYPE)
            .abnormalGrade(UPDATED_ABNORMAL_GRADE)
            .abnormalName(UPDATED_ABNORMAL_NAME)
            .abnormalContent(UPDATED_ABNORMAL_CONTENT)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiImproveReportMockMvc
            .perform(
                put("/api/kpi-improve-reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiImproveReport))
            )
            .andExpect(status().isOk());

        // Validate the KpiImproveReport in the database
        List<KpiImproveReport> kpiImproveReportList = kpiImproveReportRepository.findAll();
        assertThat(kpiImproveReportList).hasSize(databaseSizeBeforeUpdate);
        KpiImproveReport testKpiImproveReport = kpiImproveReportList.get(kpiImproveReportList.size() - 1);
        assertThat(testKpiImproveReport.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testKpiImproveReport.getApprovalDate()).isEqualTo(UPDATED_APPROVAL_DATE);
        assertThat(testKpiImproveReport.getApprovalTimes()).isEqualTo(UPDATED_APPROVAL_TIMES);
        assertThat(testKpiImproveReport.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testKpiImproveReport.getValidDate()).isEqualTo(UPDATED_VALID_DATE);
        assertThat(testKpiImproveReport.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testKpiImproveReport.getTemplateFilePath()).isEqualTo(UPDATED_TEMPLATE_FILE_PATH);
        assertThat(testKpiImproveReport.getAbnormalCode()).isEqualTo(UPDATED_ABNORMAL_CODE);
        assertThat(testKpiImproveReport.getAbnormalCreateDate()).isEqualTo(UPDATED_ABNORMAL_CREATE_DATE);
        assertThat(testKpiImproveReport.getAbnormalType()).isEqualTo(UPDATED_ABNORMAL_TYPE);
        assertThat(testKpiImproveReport.getAbnormalGrade()).isEqualTo(UPDATED_ABNORMAL_GRADE);
        assertThat(testKpiImproveReport.getAbnormalName()).isEqualTo(UPDATED_ABNORMAL_NAME);
        assertThat(testKpiImproveReport.getAbnormalContent()).isEqualTo(UPDATED_ABNORMAL_CONTENT);
        assertThat(testKpiImproveReport.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiImproveReport.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiImproveReport.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiImproveReport.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiImproveReport() throws Exception {
        int databaseSizeBeforeUpdate = kpiImproveReportRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiImproveReportMockMvc
            .perform(
                put("/api/kpi-improve-reports")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiImproveReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiImproveReport in the database
        List<KpiImproveReport> kpiImproveReportList = kpiImproveReportRepository.findAll();
        assertThat(kpiImproveReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiImproveReport() throws Exception {
        // Initialize the database
        kpiImproveReportRepository.saveAndFlush(kpiImproveReport);

        int databaseSizeBeforeDelete = kpiImproveReportRepository.findAll().size();

        // Delete the kpiImproveReport
        restKpiImproveReportMockMvc
            .perform(delete("/api/kpi-improve-reports/{id}", kpiImproveReport.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiImproveReport> kpiImproveReportList = kpiImproveReportRepository.findAll();
        assertThat(kpiImproveReportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
