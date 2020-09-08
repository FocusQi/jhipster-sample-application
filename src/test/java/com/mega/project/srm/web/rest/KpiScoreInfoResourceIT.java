package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiScoreInfo;
import com.mega.project.srm.domain.enumeration.CalculationItemType;
import com.mega.project.srm.repository.KpiScoreInfoRepository;
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
 * Integration tests for the {@link KpiScoreInfoResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiScoreInfoResourceIT {
    private static final CalculationItemType DEFAULT_TYPE = CalculationItemType.AUTO;
    private static final CalculationItemType UPDATED_TYPE = CalculationItemType.MANUAL;

    private static final String DEFAULT_CAL_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAL_ITEM_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final Integer DEFAULT_CHANGE_SCORE = 1;
    private static final Integer UPDATED_CHANGE_SCORE = 2;

    private static final String DEFAULT_CHANGE_REASON = "AAAAAAAAAA";
    private static final String UPDATED_CHANGE_REASON = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CHANGE = false;
    private static final Boolean UPDATED_IS_CHANGE = true;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiScoreInfoRepository kpiScoreInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiScoreInfoMockMvc;

    private KpiScoreInfo kpiScoreInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiScoreInfo createEntity(EntityManager em) {
        KpiScoreInfo kpiScoreInfo = new KpiScoreInfo()
            .type(DEFAULT_TYPE)
            .calItemName(DEFAULT_CAL_ITEM_NAME)
            .weight(DEFAULT_WEIGHT)
            .score(DEFAULT_SCORE)
            .changeScore(DEFAULT_CHANGE_SCORE)
            .changeReason(DEFAULT_CHANGE_REASON)
            .isChange(DEFAULT_IS_CHANGE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiScoreInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiScoreInfo createUpdatedEntity(EntityManager em) {
        KpiScoreInfo kpiScoreInfo = new KpiScoreInfo()
            .type(UPDATED_TYPE)
            .calItemName(UPDATED_CAL_ITEM_NAME)
            .weight(UPDATED_WEIGHT)
            .score(UPDATED_SCORE)
            .changeScore(UPDATED_CHANGE_SCORE)
            .changeReason(UPDATED_CHANGE_REASON)
            .isChange(UPDATED_IS_CHANGE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiScoreInfo;
    }

    @BeforeEach
    public void initTest() {
        kpiScoreInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiScoreInfo() throws Exception {
        int databaseSizeBeforeCreate = kpiScoreInfoRepository.findAll().size();
        // Create the KpiScoreInfo
        restKpiScoreInfoMockMvc
            .perform(
                post("/api/kpi-score-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreInfo))
            )
            .andExpect(status().isCreated());

        // Validate the KpiScoreInfo in the database
        List<KpiScoreInfo> kpiScoreInfoList = kpiScoreInfoRepository.findAll();
        assertThat(kpiScoreInfoList).hasSize(databaseSizeBeforeCreate + 1);
        KpiScoreInfo testKpiScoreInfo = kpiScoreInfoList.get(kpiScoreInfoList.size() - 1);
        assertThat(testKpiScoreInfo.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testKpiScoreInfo.getCalItemName()).isEqualTo(DEFAULT_CAL_ITEM_NAME);
        assertThat(testKpiScoreInfo.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testKpiScoreInfo.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testKpiScoreInfo.getChangeScore()).isEqualTo(DEFAULT_CHANGE_SCORE);
        assertThat(testKpiScoreInfo.getChangeReason()).isEqualTo(DEFAULT_CHANGE_REASON);
        assertThat(testKpiScoreInfo.isIsChange()).isEqualTo(DEFAULT_IS_CHANGE);
        assertThat(testKpiScoreInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiScoreInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiScoreInfo.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiScoreInfo.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiScoreInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiScoreInfoRepository.findAll().size();

        // Create the KpiScoreInfo with an existing ID
        kpiScoreInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiScoreInfoMockMvc
            .perform(
                post("/api/kpi-score-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiScoreInfo in the database
        List<KpiScoreInfo> kpiScoreInfoList = kpiScoreInfoRepository.findAll();
        assertThat(kpiScoreInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiScoreInfoRepository.findAll().size();
        // set the field null
        kpiScoreInfo.setType(null);

        // Create the KpiScoreInfo, which fails.

        restKpiScoreInfoMockMvc
            .perform(
                post("/api/kpi-score-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreInfo))
            )
            .andExpect(status().isBadRequest());

        List<KpiScoreInfo> kpiScoreInfoList = kpiScoreInfoRepository.findAll();
        assertThat(kpiScoreInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCalItemNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiScoreInfoRepository.findAll().size();
        // set the field null
        kpiScoreInfo.setCalItemName(null);

        // Create the KpiScoreInfo, which fails.

        restKpiScoreInfoMockMvc
            .perform(
                post("/api/kpi-score-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreInfo))
            )
            .andExpect(status().isBadRequest());

        List<KpiScoreInfo> kpiScoreInfoList = kpiScoreInfoRepository.findAll();
        assertThat(kpiScoreInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiScoreInfoRepository.findAll().size();
        // set the field null
        kpiScoreInfo.setWeight(null);

        // Create the KpiScoreInfo, which fails.

        restKpiScoreInfoMockMvc
            .perform(
                post("/api/kpi-score-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreInfo))
            )
            .andExpect(status().isBadRequest());

        List<KpiScoreInfo> kpiScoreInfoList = kpiScoreInfoRepository.findAll();
        assertThat(kpiScoreInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKpiScoreInfos() throws Exception {
        // Initialize the database
        kpiScoreInfoRepository.saveAndFlush(kpiScoreInfo);

        // Get all the kpiScoreInfoList
        restKpiScoreInfoMockMvc
            .perform(get("/api/kpi-score-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiScoreInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].calItemName").value(hasItem(DEFAULT_CAL_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].changeScore").value(hasItem(DEFAULT_CHANGE_SCORE)))
            .andExpect(jsonPath("$.[*].changeReason").value(hasItem(DEFAULT_CHANGE_REASON)))
            .andExpect(jsonPath("$.[*].isChange").value(hasItem(DEFAULT_IS_CHANGE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiScoreInfo() throws Exception {
        // Initialize the database
        kpiScoreInfoRepository.saveAndFlush(kpiScoreInfo);

        // Get the kpiScoreInfo
        restKpiScoreInfoMockMvc
            .perform(get("/api/kpi-score-infos/{id}", kpiScoreInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiScoreInfo.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.calItemName").value(DEFAULT_CAL_ITEM_NAME))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.changeScore").value(DEFAULT_CHANGE_SCORE))
            .andExpect(jsonPath("$.changeReason").value(DEFAULT_CHANGE_REASON))
            .andExpect(jsonPath("$.isChange").value(DEFAULT_IS_CHANGE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiScoreInfo() throws Exception {
        // Get the kpiScoreInfo
        restKpiScoreInfoMockMvc.perform(get("/api/kpi-score-infos/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiScoreInfo() throws Exception {
        // Initialize the database
        kpiScoreInfoRepository.saveAndFlush(kpiScoreInfo);

        int databaseSizeBeforeUpdate = kpiScoreInfoRepository.findAll().size();

        // Update the kpiScoreInfo
        KpiScoreInfo updatedKpiScoreInfo = kpiScoreInfoRepository.findById(kpiScoreInfo.getId()).get();
        // Disconnect from session so that the updates on updatedKpiScoreInfo are not directly saved in db
        em.detach(updatedKpiScoreInfo);
        updatedKpiScoreInfo
            .type(UPDATED_TYPE)
            .calItemName(UPDATED_CAL_ITEM_NAME)
            .weight(UPDATED_WEIGHT)
            .score(UPDATED_SCORE)
            .changeScore(UPDATED_CHANGE_SCORE)
            .changeReason(UPDATED_CHANGE_REASON)
            .isChange(UPDATED_IS_CHANGE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiScoreInfoMockMvc
            .perform(
                put("/api/kpi-score-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiScoreInfo))
            )
            .andExpect(status().isOk());

        // Validate the KpiScoreInfo in the database
        List<KpiScoreInfo> kpiScoreInfoList = kpiScoreInfoRepository.findAll();
        assertThat(kpiScoreInfoList).hasSize(databaseSizeBeforeUpdate);
        KpiScoreInfo testKpiScoreInfo = kpiScoreInfoList.get(kpiScoreInfoList.size() - 1);
        assertThat(testKpiScoreInfo.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testKpiScoreInfo.getCalItemName()).isEqualTo(UPDATED_CAL_ITEM_NAME);
        assertThat(testKpiScoreInfo.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testKpiScoreInfo.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testKpiScoreInfo.getChangeScore()).isEqualTo(UPDATED_CHANGE_SCORE);
        assertThat(testKpiScoreInfo.getChangeReason()).isEqualTo(UPDATED_CHANGE_REASON);
        assertThat(testKpiScoreInfo.isIsChange()).isEqualTo(UPDATED_IS_CHANGE);
        assertThat(testKpiScoreInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiScoreInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiScoreInfo.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiScoreInfo.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiScoreInfo() throws Exception {
        int databaseSizeBeforeUpdate = kpiScoreInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiScoreInfoMockMvc
            .perform(
                put("/api/kpi-score-infos").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiScoreInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiScoreInfo in the database
        List<KpiScoreInfo> kpiScoreInfoList = kpiScoreInfoRepository.findAll();
        assertThat(kpiScoreInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiScoreInfo() throws Exception {
        // Initialize the database
        kpiScoreInfoRepository.saveAndFlush(kpiScoreInfo);

        int databaseSizeBeforeDelete = kpiScoreInfoRepository.findAll().size();

        // Delete the kpiScoreInfo
        restKpiScoreInfoMockMvc
            .perform(delete("/api/kpi-score-infos/{id}", kpiScoreInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiScoreInfo> kpiScoreInfoList = kpiScoreInfoRepository.findAll();
        assertThat(kpiScoreInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
