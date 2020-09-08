package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiAssTemplateInfo;
import com.mega.project.srm.domain.enumeration.AssStatus;
import com.mega.project.srm.repository.KpiAssTemplateInfoRepository;
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
 * Integration tests for the {@link KpiAssTemplateInfoResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiAssTemplateInfoResourceIT {
    private static final String DEFAULT_ASS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ASS_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_SCORE = 1;
    private static final Integer UPDATED_TOTAL_SCORE = 2;

    private static final AssStatus DEFAULT_STATUS = AssStatus.COMPLETE;
    private static final AssStatus UPDATED_STATUS = AssStatus.UNDER_REVIEW;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiAssTemplateInfoRepository kpiAssTemplateInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiAssTemplateInfoMockMvc;

    private KpiAssTemplateInfo kpiAssTemplateInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiAssTemplateInfo createEntity(EntityManager em) {
        KpiAssTemplateInfo kpiAssTemplateInfo = new KpiAssTemplateInfo()
            .assCode(DEFAULT_ASS_CODE)
            .totalScore(DEFAULT_TOTAL_SCORE)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiAssTemplateInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiAssTemplateInfo createUpdatedEntity(EntityManager em) {
        KpiAssTemplateInfo kpiAssTemplateInfo = new KpiAssTemplateInfo()
            .assCode(UPDATED_ASS_CODE)
            .totalScore(UPDATED_TOTAL_SCORE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiAssTemplateInfo;
    }

    @BeforeEach
    public void initTest() {
        kpiAssTemplateInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiAssTemplateInfo() throws Exception {
        int databaseSizeBeforeCreate = kpiAssTemplateInfoRepository.findAll().size();
        // Create the KpiAssTemplateInfo
        restKpiAssTemplateInfoMockMvc
            .perform(
                post("/api/kpi-ass-template-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplateInfo))
            )
            .andExpect(status().isCreated());

        // Validate the KpiAssTemplateInfo in the database
        List<KpiAssTemplateInfo> kpiAssTemplateInfoList = kpiAssTemplateInfoRepository.findAll();
        assertThat(kpiAssTemplateInfoList).hasSize(databaseSizeBeforeCreate + 1);
        KpiAssTemplateInfo testKpiAssTemplateInfo = kpiAssTemplateInfoList.get(kpiAssTemplateInfoList.size() - 1);
        assertThat(testKpiAssTemplateInfo.getAssCode()).isEqualTo(DEFAULT_ASS_CODE);
        assertThat(testKpiAssTemplateInfo.getTotalScore()).isEqualTo(DEFAULT_TOTAL_SCORE);
        assertThat(testKpiAssTemplateInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testKpiAssTemplateInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiAssTemplateInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiAssTemplateInfo.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiAssTemplateInfo.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiAssTemplateInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiAssTemplateInfoRepository.findAll().size();

        // Create the KpiAssTemplateInfo with an existing ID
        kpiAssTemplateInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiAssTemplateInfoMockMvc
            .perform(
                post("/api/kpi-ass-template-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplateInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiAssTemplateInfo in the database
        List<KpiAssTemplateInfo> kpiAssTemplateInfoList = kpiAssTemplateInfoRepository.findAll();
        assertThat(kpiAssTemplateInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAssCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssTemplateInfoRepository.findAll().size();
        // set the field null
        kpiAssTemplateInfo.setAssCode(null);

        // Create the KpiAssTemplateInfo, which fails.

        restKpiAssTemplateInfoMockMvc
            .perform(
                post("/api/kpi-ass-template-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplateInfo))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssTemplateInfo> kpiAssTemplateInfoList = kpiAssTemplateInfoRepository.findAll();
        assertThat(kpiAssTemplateInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiAssTemplateInfoRepository.findAll().size();
        // set the field null
        kpiAssTemplateInfo.setStatus(null);

        // Create the KpiAssTemplateInfo, which fails.

        restKpiAssTemplateInfoMockMvc
            .perform(
                post("/api/kpi-ass-template-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplateInfo))
            )
            .andExpect(status().isBadRequest());

        List<KpiAssTemplateInfo> kpiAssTemplateInfoList = kpiAssTemplateInfoRepository.findAll();
        assertThat(kpiAssTemplateInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKpiAssTemplateInfos() throws Exception {
        // Initialize the database
        kpiAssTemplateInfoRepository.saveAndFlush(kpiAssTemplateInfo);

        // Get all the kpiAssTemplateInfoList
        restKpiAssTemplateInfoMockMvc
            .perform(get("/api/kpi-ass-template-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiAssTemplateInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].assCode").value(hasItem(DEFAULT_ASS_CODE)))
            .andExpect(jsonPath("$.[*].totalScore").value(hasItem(DEFAULT_TOTAL_SCORE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiAssTemplateInfo() throws Exception {
        // Initialize the database
        kpiAssTemplateInfoRepository.saveAndFlush(kpiAssTemplateInfo);

        // Get the kpiAssTemplateInfo
        restKpiAssTemplateInfoMockMvc
            .perform(get("/api/kpi-ass-template-infos/{id}", kpiAssTemplateInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiAssTemplateInfo.getId().intValue()))
            .andExpect(jsonPath("$.assCode").value(DEFAULT_ASS_CODE))
            .andExpect(jsonPath("$.totalScore").value(DEFAULT_TOTAL_SCORE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiAssTemplateInfo() throws Exception {
        // Get the kpiAssTemplateInfo
        restKpiAssTemplateInfoMockMvc.perform(get("/api/kpi-ass-template-infos/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiAssTemplateInfo() throws Exception {
        // Initialize the database
        kpiAssTemplateInfoRepository.saveAndFlush(kpiAssTemplateInfo);

        int databaseSizeBeforeUpdate = kpiAssTemplateInfoRepository.findAll().size();

        // Update the kpiAssTemplateInfo
        KpiAssTemplateInfo updatedKpiAssTemplateInfo = kpiAssTemplateInfoRepository.findById(kpiAssTemplateInfo.getId()).get();
        // Disconnect from session so that the updates on updatedKpiAssTemplateInfo are not directly saved in db
        em.detach(updatedKpiAssTemplateInfo);
        updatedKpiAssTemplateInfo
            .assCode(UPDATED_ASS_CODE)
            .totalScore(UPDATED_TOTAL_SCORE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiAssTemplateInfoMockMvc
            .perform(
                put("/api/kpi-ass-template-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiAssTemplateInfo))
            )
            .andExpect(status().isOk());

        // Validate the KpiAssTemplateInfo in the database
        List<KpiAssTemplateInfo> kpiAssTemplateInfoList = kpiAssTemplateInfoRepository.findAll();
        assertThat(kpiAssTemplateInfoList).hasSize(databaseSizeBeforeUpdate);
        KpiAssTemplateInfo testKpiAssTemplateInfo = kpiAssTemplateInfoList.get(kpiAssTemplateInfoList.size() - 1);
        assertThat(testKpiAssTemplateInfo.getAssCode()).isEqualTo(UPDATED_ASS_CODE);
        assertThat(testKpiAssTemplateInfo.getTotalScore()).isEqualTo(UPDATED_TOTAL_SCORE);
        assertThat(testKpiAssTemplateInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testKpiAssTemplateInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiAssTemplateInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiAssTemplateInfo.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiAssTemplateInfo.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiAssTemplateInfo() throws Exception {
        int databaseSizeBeforeUpdate = kpiAssTemplateInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiAssTemplateInfoMockMvc
            .perform(
                put("/api/kpi-ass-template-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiAssTemplateInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiAssTemplateInfo in the database
        List<KpiAssTemplateInfo> kpiAssTemplateInfoList = kpiAssTemplateInfoRepository.findAll();
        assertThat(kpiAssTemplateInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiAssTemplateInfo() throws Exception {
        // Initialize the database
        kpiAssTemplateInfoRepository.saveAndFlush(kpiAssTemplateInfo);

        int databaseSizeBeforeDelete = kpiAssTemplateInfoRepository.findAll().size();

        // Delete the kpiAssTemplateInfo
        restKpiAssTemplateInfoMockMvc
            .perform(delete("/api/kpi-ass-template-infos/{id}", kpiAssTemplateInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiAssTemplateInfo> kpiAssTemplateInfoList = kpiAssTemplateInfoRepository.findAll();
        assertThat(kpiAssTemplateInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
