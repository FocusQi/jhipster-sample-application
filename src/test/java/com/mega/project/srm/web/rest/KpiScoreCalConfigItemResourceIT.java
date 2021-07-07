package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiScoreCalConfigItem;
import com.mega.project.srm.repository.KpiScoreCalConfigItemRepository;
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
 * Integration tests for the {@link KpiScoreCalConfigItemResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiScoreCalConfigItemResourceIT {
    private static final String DEFAULT_UNIT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiScoreCalConfigItemRepository kpiScoreCalConfigItemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiScoreCalConfigItemMockMvc;

    private KpiScoreCalConfigItem kpiScoreCalConfigItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiScoreCalConfigItem createEntity(EntityManager em) {
        KpiScoreCalConfigItem kpiScoreCalConfigItem = new KpiScoreCalConfigItem()
            .unitType(DEFAULT_UNIT_TYPE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiScoreCalConfigItem;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiScoreCalConfigItem createUpdatedEntity(EntityManager em) {
        KpiScoreCalConfigItem kpiScoreCalConfigItem = new KpiScoreCalConfigItem()
            .unitType(UPDATED_UNIT_TYPE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiScoreCalConfigItem;
    }

    @BeforeEach
    public void initTest() {
        kpiScoreCalConfigItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiScoreCalConfigItem() throws Exception {
        int databaseSizeBeforeCreate = kpiScoreCalConfigItemRepository.findAll().size();
        // Create the KpiScoreCalConfigItem
        restKpiScoreCalConfigItemMockMvc
            .perform(
                post("/api/kpi-score-cal-config-items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreCalConfigItem))
            )
            .andExpect(status().isCreated());

        // Validate the KpiScoreCalConfigItem in the database
        List<KpiScoreCalConfigItem> kpiScoreCalConfigItemList = kpiScoreCalConfigItemRepository.findAll();
        assertThat(kpiScoreCalConfigItemList).hasSize(databaseSizeBeforeCreate + 1);
        KpiScoreCalConfigItem testKpiScoreCalConfigItem = kpiScoreCalConfigItemList.get(kpiScoreCalConfigItemList.size() - 1);
        assertThat(testKpiScoreCalConfigItem.getUnitType()).isEqualTo(DEFAULT_UNIT_TYPE);
        assertThat(testKpiScoreCalConfigItem.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiScoreCalConfigItem.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiScoreCalConfigItem.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiScoreCalConfigItem.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiScoreCalConfigItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiScoreCalConfigItemRepository.findAll().size();

        // Create the KpiScoreCalConfigItem with an existing ID
        kpiScoreCalConfigItem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiScoreCalConfigItemMockMvc
            .perform(
                post("/api/kpi-score-cal-config-items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreCalConfigItem))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiScoreCalConfigItem in the database
        List<KpiScoreCalConfigItem> kpiScoreCalConfigItemList = kpiScoreCalConfigItemRepository.findAll();
        assertThat(kpiScoreCalConfigItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKpiScoreCalConfigItems() throws Exception {
        // Initialize the database
        kpiScoreCalConfigItemRepository.saveAndFlush(kpiScoreCalConfigItem);

        // Get all the kpiScoreCalConfigItemList
        restKpiScoreCalConfigItemMockMvc
            .perform(get("/api/kpi-score-cal-config-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiScoreCalConfigItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].unitType").value(hasItem(DEFAULT_UNIT_TYPE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiScoreCalConfigItem() throws Exception {
        // Initialize the database
        kpiScoreCalConfigItemRepository.saveAndFlush(kpiScoreCalConfigItem);

        // Get the kpiScoreCalConfigItem
        restKpiScoreCalConfigItemMockMvc
            .perform(get("/api/kpi-score-cal-config-items/{id}", kpiScoreCalConfigItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiScoreCalConfigItem.getId().intValue()))
            .andExpect(jsonPath("$.unitType").value(DEFAULT_UNIT_TYPE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiScoreCalConfigItem() throws Exception {
        // Get the kpiScoreCalConfigItem
        restKpiScoreCalConfigItemMockMvc
            .perform(get("/api/kpi-score-cal-config-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiScoreCalConfigItem() throws Exception {
        // Initialize the database
        kpiScoreCalConfigItemRepository.saveAndFlush(kpiScoreCalConfigItem);

        int databaseSizeBeforeUpdate = kpiScoreCalConfigItemRepository.findAll().size();

        // Update the kpiScoreCalConfigItem
        KpiScoreCalConfigItem updatedKpiScoreCalConfigItem = kpiScoreCalConfigItemRepository.findById(kpiScoreCalConfigItem.getId()).get();
        // Disconnect from session so that the updates on updatedKpiScoreCalConfigItem are not directly saved in db
        em.detach(updatedKpiScoreCalConfigItem);
        updatedKpiScoreCalConfigItem
            .unitType(UPDATED_UNIT_TYPE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiScoreCalConfigItemMockMvc
            .perform(
                put("/api/kpi-score-cal-config-items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiScoreCalConfigItem))
            )
            .andExpect(status().isOk());

        // Validate the KpiScoreCalConfigItem in the database
        List<KpiScoreCalConfigItem> kpiScoreCalConfigItemList = kpiScoreCalConfigItemRepository.findAll();
        assertThat(kpiScoreCalConfigItemList).hasSize(databaseSizeBeforeUpdate);
        KpiScoreCalConfigItem testKpiScoreCalConfigItem = kpiScoreCalConfigItemList.get(kpiScoreCalConfigItemList.size() - 1);
        assertThat(testKpiScoreCalConfigItem.getUnitType()).isEqualTo(UPDATED_UNIT_TYPE);
        assertThat(testKpiScoreCalConfigItem.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiScoreCalConfigItem.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiScoreCalConfigItem.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiScoreCalConfigItem.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiScoreCalConfigItem() throws Exception {
        int databaseSizeBeforeUpdate = kpiScoreCalConfigItemRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiScoreCalConfigItemMockMvc
            .perform(
                put("/api/kpi-score-cal-config-items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(kpiScoreCalConfigItem))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiScoreCalConfigItem in the database
        List<KpiScoreCalConfigItem> kpiScoreCalConfigItemList = kpiScoreCalConfigItemRepository.findAll();
        assertThat(kpiScoreCalConfigItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiScoreCalConfigItem() throws Exception {
        // Initialize the database
        kpiScoreCalConfigItemRepository.saveAndFlush(kpiScoreCalConfigItem);

        int databaseSizeBeforeDelete = kpiScoreCalConfigItemRepository.findAll().size();

        // Delete the kpiScoreCalConfigItem
        restKpiScoreCalConfigItemMockMvc
            .perform(delete("/api/kpi-score-cal-config-items/{id}", kpiScoreCalConfigItem.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiScoreCalConfigItem> kpiScoreCalConfigItemList = kpiScoreCalConfigItemRepository.findAll();
        assertThat(kpiScoreCalConfigItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
