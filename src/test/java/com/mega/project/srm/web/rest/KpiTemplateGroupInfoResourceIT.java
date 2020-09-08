package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiTemplateGroupInfo;
import com.mega.project.srm.repository.KpiTemplateGroupInfoRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link KpiTemplateGroupInfoResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiTemplateGroupInfoResourceIT {
    private static final Integer DEFAULT_SCORING_WEIGHT = 1;
    private static final Integer UPDATED_SCORING_WEIGHT = 2;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiTemplateGroupInfoRepository kpiTemplateGroupInfoRepository;

    @Mock
    private KpiTemplateGroupInfoRepository kpiTemplateGroupInfoRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiTemplateGroupInfoMockMvc;

    private KpiTemplateGroupInfo kpiTemplateGroupInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiTemplateGroupInfo createEntity(EntityManager em) {
        KpiTemplateGroupInfo kpiTemplateGroupInfo = new KpiTemplateGroupInfo()
            .scoringWeight(DEFAULT_SCORING_WEIGHT)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiTemplateGroupInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiTemplateGroupInfo createUpdatedEntity(EntityManager em) {
        KpiTemplateGroupInfo kpiTemplateGroupInfo = new KpiTemplateGroupInfo()
            .scoringWeight(UPDATED_SCORING_WEIGHT)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiTemplateGroupInfo;
    }

    @BeforeEach
    public void initTest() {
        kpiTemplateGroupInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiTemplateGroupInfo() throws Exception {
        int databaseSizeBeforeCreate = kpiTemplateGroupInfoRepository.findAll().size();
        // Create the KpiTemplateGroupInfo
        restKpiTemplateGroupInfoMockMvc
            .perform(
                post("/api/kpi-template-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiTemplateGroupInfo))
            )
            .andExpect(status().isCreated());

        // Validate the KpiTemplateGroupInfo in the database
        List<KpiTemplateGroupInfo> kpiTemplateGroupInfoList = kpiTemplateGroupInfoRepository.findAll();
        assertThat(kpiTemplateGroupInfoList).hasSize(databaseSizeBeforeCreate + 1);
        KpiTemplateGroupInfo testKpiTemplateGroupInfo = kpiTemplateGroupInfoList.get(kpiTemplateGroupInfoList.size() - 1);
        assertThat(testKpiTemplateGroupInfo.getScoringWeight()).isEqualTo(DEFAULT_SCORING_WEIGHT);
        assertThat(testKpiTemplateGroupInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiTemplateGroupInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiTemplateGroupInfo.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiTemplateGroupInfo.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiTemplateGroupInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiTemplateGroupInfoRepository.findAll().size();

        // Create the KpiTemplateGroupInfo with an existing ID
        kpiTemplateGroupInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiTemplateGroupInfoMockMvc
            .perform(
                post("/api/kpi-template-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiTemplateGroupInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiTemplateGroupInfo in the database
        List<KpiTemplateGroupInfo> kpiTemplateGroupInfoList = kpiTemplateGroupInfoRepository.findAll();
        assertThat(kpiTemplateGroupInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKpiTemplateGroupInfos() throws Exception {
        // Initialize the database
        kpiTemplateGroupInfoRepository.saveAndFlush(kpiTemplateGroupInfo);

        // Get all the kpiTemplateGroupInfoList
        restKpiTemplateGroupInfoMockMvc
            .perform(get("/api/kpi-template-group-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiTemplateGroupInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].scoringWeight").value(hasItem(DEFAULT_SCORING_WEIGHT)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllKpiTemplateGroupInfosWithEagerRelationshipsIsEnabled() throws Exception {
        when(kpiTemplateGroupInfoRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restKpiTemplateGroupInfoMockMvc.perform(get("/api/kpi-template-group-infos?eagerload=true")).andExpect(status().isOk());

        verify(kpiTemplateGroupInfoRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllKpiTemplateGroupInfosWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(kpiTemplateGroupInfoRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restKpiTemplateGroupInfoMockMvc.perform(get("/api/kpi-template-group-infos?eagerload=true")).andExpect(status().isOk());

        verify(kpiTemplateGroupInfoRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getKpiTemplateGroupInfo() throws Exception {
        // Initialize the database
        kpiTemplateGroupInfoRepository.saveAndFlush(kpiTemplateGroupInfo);

        // Get the kpiTemplateGroupInfo
        restKpiTemplateGroupInfoMockMvc
            .perform(get("/api/kpi-template-group-infos/{id}", kpiTemplateGroupInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiTemplateGroupInfo.getId().intValue()))
            .andExpect(jsonPath("$.scoringWeight").value(DEFAULT_SCORING_WEIGHT))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiTemplateGroupInfo() throws Exception {
        // Get the kpiTemplateGroupInfo
        restKpiTemplateGroupInfoMockMvc.perform(get("/api/kpi-template-group-infos/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiTemplateGroupInfo() throws Exception {
        // Initialize the database
        kpiTemplateGroupInfoRepository.saveAndFlush(kpiTemplateGroupInfo);

        int databaseSizeBeforeUpdate = kpiTemplateGroupInfoRepository.findAll().size();

        // Update the kpiTemplateGroupInfo
        KpiTemplateGroupInfo updatedKpiTemplateGroupInfo = kpiTemplateGroupInfoRepository.findById(kpiTemplateGroupInfo.getId()).get();
        // Disconnect from session so that the updates on updatedKpiTemplateGroupInfo are not directly saved in db
        em.detach(updatedKpiTemplateGroupInfo);
        updatedKpiTemplateGroupInfo
            .scoringWeight(UPDATED_SCORING_WEIGHT)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiTemplateGroupInfoMockMvc
            .perform(
                put("/api/kpi-template-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiTemplateGroupInfo))
            )
            .andExpect(status().isOk());

        // Validate the KpiTemplateGroupInfo in the database
        List<KpiTemplateGroupInfo> kpiTemplateGroupInfoList = kpiTemplateGroupInfoRepository.findAll();
        assertThat(kpiTemplateGroupInfoList).hasSize(databaseSizeBeforeUpdate);
        KpiTemplateGroupInfo testKpiTemplateGroupInfo = kpiTemplateGroupInfoList.get(kpiTemplateGroupInfoList.size() - 1);
        assertThat(testKpiTemplateGroupInfo.getScoringWeight()).isEqualTo(UPDATED_SCORING_WEIGHT);
        assertThat(testKpiTemplateGroupInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiTemplateGroupInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiTemplateGroupInfo.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiTemplateGroupInfo.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiTemplateGroupInfo() throws Exception {
        int databaseSizeBeforeUpdate = kpiTemplateGroupInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiTemplateGroupInfoMockMvc
            .perform(
                put("/api/kpi-template-group-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiTemplateGroupInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiTemplateGroupInfo in the database
        List<KpiTemplateGroupInfo> kpiTemplateGroupInfoList = kpiTemplateGroupInfoRepository.findAll();
        assertThat(kpiTemplateGroupInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiTemplateGroupInfo() throws Exception {
        // Initialize the database
        kpiTemplateGroupInfoRepository.saveAndFlush(kpiTemplateGroupInfo);

        int databaseSizeBeforeDelete = kpiTemplateGroupInfoRepository.findAll().size();

        // Delete the kpiTemplateGroupInfo
        restKpiTemplateGroupInfoMockMvc
            .perform(delete("/api/kpi-template-group-infos/{id}", kpiTemplateGroupInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiTemplateGroupInfo> kpiTemplateGroupInfoList = kpiTemplateGroupInfoRepository.findAll();
        assertThat(kpiTemplateGroupInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
