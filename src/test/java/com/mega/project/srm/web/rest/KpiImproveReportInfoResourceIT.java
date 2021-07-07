package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiImproveReportInfo;
import com.mega.project.srm.domain.enumeration.ApprovalStatus;
import com.mega.project.srm.repository.KpiImproveReportInfoRepository;
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
 * Integration tests for the {@link KpiImproveReportInfoResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiImproveReportInfoResourceIT {
    private static final LocalDate DEFAULT_REPLY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REPLY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_APPROVAL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPROVAL_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ApprovalStatus DEFAULT_STATUS = ApprovalStatus.OK;
    private static final ApprovalStatus UPDATED_STATUS = ApprovalStatus.NG;

    private static final String DEFAULT_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_IMPROVE_APPRAISE = "AAAAAAAAAA";
    private static final String UPDATED_IMPROVE_APPRAISE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiImproveReportInfoRepository kpiImproveReportInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiImproveReportInfoMockMvc;

    private KpiImproveReportInfo kpiImproveReportInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiImproveReportInfo createEntity(EntityManager em) {
        KpiImproveReportInfo kpiImproveReportInfo = new KpiImproveReportInfo()
            .replyDate(DEFAULT_REPLY_DATE)
            .approvalDate(DEFAULT_APPROVAL_DATE)
            .status(DEFAULT_STATUS)
            .filePath(DEFAULT_FILE_PATH)
            .improveAppraise(DEFAULT_IMPROVE_APPRAISE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiImproveReportInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiImproveReportInfo createUpdatedEntity(EntityManager em) {
        KpiImproveReportInfo kpiImproveReportInfo = new KpiImproveReportInfo()
            .replyDate(UPDATED_REPLY_DATE)
            .approvalDate(UPDATED_APPROVAL_DATE)
            .status(UPDATED_STATUS)
            .filePath(UPDATED_FILE_PATH)
            .improveAppraise(UPDATED_IMPROVE_APPRAISE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiImproveReportInfo;
    }

    @BeforeEach
    public void initTest() {
        kpiImproveReportInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiImproveReportInfo() throws Exception {
        int databaseSizeBeforeCreate = kpiImproveReportInfoRepository.findAll().size();
        // Create the KpiImproveReportInfo
        restKpiImproveReportInfoMockMvc
            .perform(
                post("/api/kpi-improve-report-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiImproveReportInfo))
            )
            .andExpect(status().isCreated());

        // Validate the KpiImproveReportInfo in the database
        List<KpiImproveReportInfo> kpiImproveReportInfoList = kpiImproveReportInfoRepository.findAll();
        assertThat(kpiImproveReportInfoList).hasSize(databaseSizeBeforeCreate + 1);
        KpiImproveReportInfo testKpiImproveReportInfo = kpiImproveReportInfoList.get(kpiImproveReportInfoList.size() - 1);
        assertThat(testKpiImproveReportInfo.getReplyDate()).isEqualTo(DEFAULT_REPLY_DATE);
        assertThat(testKpiImproveReportInfo.getApprovalDate()).isEqualTo(DEFAULT_APPROVAL_DATE);
        assertThat(testKpiImproveReportInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testKpiImproveReportInfo.getFilePath()).isEqualTo(DEFAULT_FILE_PATH);
        assertThat(testKpiImproveReportInfo.getImproveAppraise()).isEqualTo(DEFAULT_IMPROVE_APPRAISE);
        assertThat(testKpiImproveReportInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiImproveReportInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiImproveReportInfo.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiImproveReportInfo.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiImproveReportInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiImproveReportInfoRepository.findAll().size();

        // Create the KpiImproveReportInfo with an existing ID
        kpiImproveReportInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiImproveReportInfoMockMvc
            .perform(
                post("/api/kpi-improve-report-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiImproveReportInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiImproveReportInfo in the database
        List<KpiImproveReportInfo> kpiImproveReportInfoList = kpiImproveReportInfoRepository.findAll();
        assertThat(kpiImproveReportInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKpiImproveReportInfos() throws Exception {
        // Initialize the database
        kpiImproveReportInfoRepository.saveAndFlush(kpiImproveReportInfo);

        // Get all the kpiImproveReportInfoList
        restKpiImproveReportInfoMockMvc
            .perform(get("/api/kpi-improve-report-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiImproveReportInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].replyDate").value(hasItem(DEFAULT_REPLY_DATE.toString())))
            .andExpect(jsonPath("$.[*].approvalDate").value(hasItem(DEFAULT_APPROVAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].filePath").value(hasItem(DEFAULT_FILE_PATH)))
            .andExpect(jsonPath("$.[*].improveAppraise").value(hasItem(DEFAULT_IMPROVE_APPRAISE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiImproveReportInfo() throws Exception {
        // Initialize the database
        kpiImproveReportInfoRepository.saveAndFlush(kpiImproveReportInfo);

        // Get the kpiImproveReportInfo
        restKpiImproveReportInfoMockMvc
            .perform(get("/api/kpi-improve-report-infos/{id}", kpiImproveReportInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiImproveReportInfo.getId().intValue()))
            .andExpect(jsonPath("$.replyDate").value(DEFAULT_REPLY_DATE.toString()))
            .andExpect(jsonPath("$.approvalDate").value(DEFAULT_APPROVAL_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.filePath").value(DEFAULT_FILE_PATH))
            .andExpect(jsonPath("$.improveAppraise").value(DEFAULT_IMPROVE_APPRAISE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiImproveReportInfo() throws Exception {
        // Get the kpiImproveReportInfo
        restKpiImproveReportInfoMockMvc.perform(get("/api/kpi-improve-report-infos/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiImproveReportInfo() throws Exception {
        // Initialize the database
        kpiImproveReportInfoRepository.saveAndFlush(kpiImproveReportInfo);

        int databaseSizeBeforeUpdate = kpiImproveReportInfoRepository.findAll().size();

        // Update the kpiImproveReportInfo
        KpiImproveReportInfo updatedKpiImproveReportInfo = kpiImproveReportInfoRepository.findById(kpiImproveReportInfo.getId()).get();
        // Disconnect from session so that the updates on updatedKpiImproveReportInfo are not directly saved in db
        em.detach(updatedKpiImproveReportInfo);
        updatedKpiImproveReportInfo
            .replyDate(UPDATED_REPLY_DATE)
            .approvalDate(UPDATED_APPROVAL_DATE)
            .status(UPDATED_STATUS)
            .filePath(UPDATED_FILE_PATH)
            .improveAppraise(UPDATED_IMPROVE_APPRAISE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiImproveReportInfoMockMvc
            .perform(
                put("/api/kpi-improve-report-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiImproveReportInfo))
            )
            .andExpect(status().isOk());

        // Validate the KpiImproveReportInfo in the database
        List<KpiImproveReportInfo> kpiImproveReportInfoList = kpiImproveReportInfoRepository.findAll();
        assertThat(kpiImproveReportInfoList).hasSize(databaseSizeBeforeUpdate);
        KpiImproveReportInfo testKpiImproveReportInfo = kpiImproveReportInfoList.get(kpiImproveReportInfoList.size() - 1);
        assertThat(testKpiImproveReportInfo.getReplyDate()).isEqualTo(UPDATED_REPLY_DATE);
        assertThat(testKpiImproveReportInfo.getApprovalDate()).isEqualTo(UPDATED_APPROVAL_DATE);
        assertThat(testKpiImproveReportInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testKpiImproveReportInfo.getFilePath()).isEqualTo(UPDATED_FILE_PATH);
        assertThat(testKpiImproveReportInfo.getImproveAppraise()).isEqualTo(UPDATED_IMPROVE_APPRAISE);
        assertThat(testKpiImproveReportInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiImproveReportInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiImproveReportInfo.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiImproveReportInfo.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiImproveReportInfo() throws Exception {
        int databaseSizeBeforeUpdate = kpiImproveReportInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiImproveReportInfoMockMvc
            .perform(
                put("/api/kpi-improve-report-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiImproveReportInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiImproveReportInfo in the database
        List<KpiImproveReportInfo> kpiImproveReportInfoList = kpiImproveReportInfoRepository.findAll();
        assertThat(kpiImproveReportInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiImproveReportInfo() throws Exception {
        // Initialize the database
        kpiImproveReportInfoRepository.saveAndFlush(kpiImproveReportInfo);

        int databaseSizeBeforeDelete = kpiImproveReportInfoRepository.findAll().size();

        // Delete the kpiImproveReportInfo
        restKpiImproveReportInfoMockMvc
            .perform(delete("/api/kpi-improve-report-infos/{id}", kpiImproveReportInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiImproveReportInfo> kpiImproveReportInfoList = kpiImproveReportInfoRepository.findAll();
        assertThat(kpiImproveReportInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
