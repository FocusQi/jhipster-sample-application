package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.KpiCalItem;
import com.mega.project.srm.domain.enumeration.CalculationItemType;
import com.mega.project.srm.repository.KpiCalItemRepository;
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
 * Integration tests for the {@link KpiCalItemResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KpiCalItemResourceIT {
    private static final CalculationItemType DEFAULT_TYPE = CalculationItemType.AUTO;
    private static final CalculationItemType UPDATED_TYPE = CalculationItemType.MANUAL;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CAL_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAL_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private KpiCalItemRepository kpiCalItemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKpiCalItemMockMvc;

    private KpiCalItem kpiCalItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiCalItem createEntity(EntityManager em) {
        KpiCalItem kpiCalItem = new KpiCalItem()
            .type(DEFAULT_TYPE)
            .title(DEFAULT_TITLE)
            .calItemName(DEFAULT_CAL_ITEM_NAME)
            .description(DEFAULT_DESCRIPTION)
            .weight(DEFAULT_WEIGHT)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return kpiCalItem;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KpiCalItem createUpdatedEntity(EntityManager em) {
        KpiCalItem kpiCalItem = new KpiCalItem()
            .type(UPDATED_TYPE)
            .title(UPDATED_TITLE)
            .calItemName(UPDATED_CAL_ITEM_NAME)
            .description(UPDATED_DESCRIPTION)
            .weight(UPDATED_WEIGHT)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return kpiCalItem;
    }

    @BeforeEach
    public void initTest() {
        kpiCalItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createKpiCalItem() throws Exception {
        int databaseSizeBeforeCreate = kpiCalItemRepository.findAll().size();
        // Create the KpiCalItem
        restKpiCalItemMockMvc
            .perform(
                post("/api/kpi-cal-items").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiCalItem))
            )
            .andExpect(status().isCreated());

        // Validate the KpiCalItem in the database
        List<KpiCalItem> kpiCalItemList = kpiCalItemRepository.findAll();
        assertThat(kpiCalItemList).hasSize(databaseSizeBeforeCreate + 1);
        KpiCalItem testKpiCalItem = kpiCalItemList.get(kpiCalItemList.size() - 1);
        assertThat(testKpiCalItem.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testKpiCalItem.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testKpiCalItem.getCalItemName()).isEqualTo(DEFAULT_CAL_ITEM_NAME);
        assertThat(testKpiCalItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testKpiCalItem.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testKpiCalItem.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testKpiCalItem.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testKpiCalItem.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testKpiCalItem.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createKpiCalItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kpiCalItemRepository.findAll().size();

        // Create the KpiCalItem with an existing ID
        kpiCalItem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKpiCalItemMockMvc
            .perform(
                post("/api/kpi-cal-items").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiCalItem))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiCalItem in the database
        List<KpiCalItem> kpiCalItemList = kpiCalItemRepository.findAll();
        assertThat(kpiCalItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiCalItemRepository.findAll().size();
        // set the field null
        kpiCalItem.setType(null);

        // Create the KpiCalItem, which fails.

        restKpiCalItemMockMvc
            .perform(
                post("/api/kpi-cal-items").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiCalItem))
            )
            .andExpect(status().isBadRequest());

        List<KpiCalItem> kpiCalItemList = kpiCalItemRepository.findAll();
        assertThat(kpiCalItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiCalItemRepository.findAll().size();
        // set the field null
        kpiCalItem.setTitle(null);

        // Create the KpiCalItem, which fails.

        restKpiCalItemMockMvc
            .perform(
                post("/api/kpi-cal-items").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiCalItem))
            )
            .andExpect(status().isBadRequest());

        List<KpiCalItem> kpiCalItemList = kpiCalItemRepository.findAll();
        assertThat(kpiCalItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCalItemNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiCalItemRepository.findAll().size();
        // set the field null
        kpiCalItem.setCalItemName(null);

        // Create the KpiCalItem, which fails.

        restKpiCalItemMockMvc
            .perform(
                post("/api/kpi-cal-items").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiCalItem))
            )
            .andExpect(status().isBadRequest());

        List<KpiCalItem> kpiCalItemList = kpiCalItemRepository.findAll();
        assertThat(kpiCalItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = kpiCalItemRepository.findAll().size();
        // set the field null
        kpiCalItem.setWeight(null);

        // Create the KpiCalItem, which fails.

        restKpiCalItemMockMvc
            .perform(
                post("/api/kpi-cal-items").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiCalItem))
            )
            .andExpect(status().isBadRequest());

        List<KpiCalItem> kpiCalItemList = kpiCalItemRepository.findAll();
        assertThat(kpiCalItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKpiCalItems() throws Exception {
        // Initialize the database
        kpiCalItemRepository.saveAndFlush(kpiCalItem);

        // Get all the kpiCalItemList
        restKpiCalItemMockMvc
            .perform(get("/api/kpi-cal-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kpiCalItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].calItemName").value(hasItem(DEFAULT_CAL_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getKpiCalItem() throws Exception {
        // Initialize the database
        kpiCalItemRepository.saveAndFlush(kpiCalItem);

        // Get the kpiCalItem
        restKpiCalItemMockMvc
            .perform(get("/api/kpi-cal-items/{id}", kpiCalItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kpiCalItem.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.calItemName").value(DEFAULT_CAL_ITEM_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKpiCalItem() throws Exception {
        // Get the kpiCalItem
        restKpiCalItemMockMvc.perform(get("/api/kpi-cal-items/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKpiCalItem() throws Exception {
        // Initialize the database
        kpiCalItemRepository.saveAndFlush(kpiCalItem);

        int databaseSizeBeforeUpdate = kpiCalItemRepository.findAll().size();

        // Update the kpiCalItem
        KpiCalItem updatedKpiCalItem = kpiCalItemRepository.findById(kpiCalItem.getId()).get();
        // Disconnect from session so that the updates on updatedKpiCalItem are not directly saved in db
        em.detach(updatedKpiCalItem);
        updatedKpiCalItem
            .type(UPDATED_TYPE)
            .title(UPDATED_TITLE)
            .calItemName(UPDATED_CAL_ITEM_NAME)
            .description(UPDATED_DESCRIPTION)
            .weight(UPDATED_WEIGHT)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restKpiCalItemMockMvc
            .perform(
                put("/api/kpi-cal-items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedKpiCalItem))
            )
            .andExpect(status().isOk());

        // Validate the KpiCalItem in the database
        List<KpiCalItem> kpiCalItemList = kpiCalItemRepository.findAll();
        assertThat(kpiCalItemList).hasSize(databaseSizeBeforeUpdate);
        KpiCalItem testKpiCalItem = kpiCalItemList.get(kpiCalItemList.size() - 1);
        assertThat(testKpiCalItem.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testKpiCalItem.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testKpiCalItem.getCalItemName()).isEqualTo(UPDATED_CAL_ITEM_NAME);
        assertThat(testKpiCalItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testKpiCalItem.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testKpiCalItem.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testKpiCalItem.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testKpiCalItem.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testKpiCalItem.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingKpiCalItem() throws Exception {
        int databaseSizeBeforeUpdate = kpiCalItemRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKpiCalItemMockMvc
            .perform(
                put("/api/kpi-cal-items").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(kpiCalItem))
            )
            .andExpect(status().isBadRequest());

        // Validate the KpiCalItem in the database
        List<KpiCalItem> kpiCalItemList = kpiCalItemRepository.findAll();
        assertThat(kpiCalItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKpiCalItem() throws Exception {
        // Initialize the database
        kpiCalItemRepository.saveAndFlush(kpiCalItem);

        int databaseSizeBeforeDelete = kpiCalItemRepository.findAll().size();

        // Delete the kpiCalItem
        restKpiCalItemMockMvc
            .perform(delete("/api/kpi-cal-items/{id}", kpiCalItem.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KpiCalItem> kpiCalItemList = kpiCalItemRepository.findAll();
        assertThat(kpiCalItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
