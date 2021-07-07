package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.BomTemplateInfo;
import com.mycompany.myapp.repository.BomTemplateInfoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BomTemplateInfoResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BomTemplateInfoResourceIT {

    private static final String DEFAULT_UOM = "AAAAAAAAAA";
    private static final String UPDATED_UOM = "BBBBBBBBBB";

    private static final Double DEFAULT_USE_QTY = 1D;
    private static final Double UPDATED_USE_QTY = 2D;

    private static final Double DEFAULT_UNIT_PRICE = 1D;
    private static final Double UPDATED_UNIT_PRICE = 2D;

    private static final Double DEFAULT_TOTAL = 1D;
    private static final Double UPDATED_TOTAL = 2D;

    private static final Double DEFAULT_TAX = 1D;
    private static final Double UPDATED_TAX = 2D;

    private static final Double DEFAULT_TAX_TOTAL = 1D;
    private static final Double UPDATED_TAX_TOTAL = 2D;

    private static final Double DEFAULT_REMARK = 1D;
    private static final Double UPDATED_REMARK = 2D;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BomTemplateInfoRepository bomTemplateInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBomTemplateInfoMockMvc;

    private BomTemplateInfo bomTemplateInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BomTemplateInfo createEntity(EntityManager em) {
        BomTemplateInfo bomTemplateInfo = new BomTemplateInfo()
            .uom(DEFAULT_UOM)
            .useQty(DEFAULT_USE_QTY)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .total(DEFAULT_TOTAL)
            .tax(DEFAULT_TAX)
            .taxTotal(DEFAULT_TAX_TOTAL)
            .remark(DEFAULT_REMARK)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return bomTemplateInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BomTemplateInfo createUpdatedEntity(EntityManager em) {
        BomTemplateInfo bomTemplateInfo = new BomTemplateInfo()
            .uom(UPDATED_UOM)
            .useQty(UPDATED_USE_QTY)
            .unitPrice(UPDATED_UNIT_PRICE)
            .total(UPDATED_TOTAL)
            .tax(UPDATED_TAX)
            .taxTotal(UPDATED_TAX_TOTAL)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return bomTemplateInfo;
    }

    @BeforeEach
    public void initTest() {
        bomTemplateInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createBomTemplateInfo() throws Exception {
        int databaseSizeBeforeCreate = bomTemplateInfoRepository.findAll().size();
        // Create the BomTemplateInfo
        restBomTemplateInfoMockMvc.perform(post("/api/bom-template-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bomTemplateInfo)))
            .andExpect(status().isCreated());

        // Validate the BomTemplateInfo in the database
        List<BomTemplateInfo> bomTemplateInfoList = bomTemplateInfoRepository.findAll();
        assertThat(bomTemplateInfoList).hasSize(databaseSizeBeforeCreate + 1);
        BomTemplateInfo testBomTemplateInfo = bomTemplateInfoList.get(bomTemplateInfoList.size() - 1);
        assertThat(testBomTemplateInfo.getUom()).isEqualTo(DEFAULT_UOM);
        assertThat(testBomTemplateInfo.getUseQty()).isEqualTo(DEFAULT_USE_QTY);
        assertThat(testBomTemplateInfo.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testBomTemplateInfo.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testBomTemplateInfo.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testBomTemplateInfo.getTaxTotal()).isEqualTo(DEFAULT_TAX_TOTAL);
        assertThat(testBomTemplateInfo.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testBomTemplateInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBomTemplateInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBomTemplateInfo.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testBomTemplateInfo.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBomTemplateInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bomTemplateInfoRepository.findAll().size();

        // Create the BomTemplateInfo with an existing ID
        bomTemplateInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBomTemplateInfoMockMvc.perform(post("/api/bom-template-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bomTemplateInfo)))
            .andExpect(status().isBadRequest());

        // Validate the BomTemplateInfo in the database
        List<BomTemplateInfo> bomTemplateInfoList = bomTemplateInfoRepository.findAll();
        assertThat(bomTemplateInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBomTemplateInfos() throws Exception {
        // Initialize the database
        bomTemplateInfoRepository.saveAndFlush(bomTemplateInfo);

        // Get all the bomTemplateInfoList
        restBomTemplateInfoMockMvc.perform(get("/api/bom-template-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bomTemplateInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].uom").value(hasItem(DEFAULT_UOM)))
            .andExpect(jsonPath("$.[*].useQty").value(hasItem(DEFAULT_USE_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX.doubleValue())))
            .andExpect(jsonPath("$.[*].taxTotal").value(hasItem(DEFAULT_TAX_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.doubleValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getBomTemplateInfo() throws Exception {
        // Initialize the database
        bomTemplateInfoRepository.saveAndFlush(bomTemplateInfo);

        // Get the bomTemplateInfo
        restBomTemplateInfoMockMvc.perform(get("/api/bom-template-infos/{id}", bomTemplateInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bomTemplateInfo.getId().intValue()))
            .andExpect(jsonPath("$.uom").value(DEFAULT_UOM))
            .andExpect(jsonPath("$.useQty").value(DEFAULT_USE_QTY.doubleValue()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX.doubleValue()))
            .andExpect(jsonPath("$.taxTotal").value(DEFAULT_TAX_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.doubleValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBomTemplateInfo() throws Exception {
        // Get the bomTemplateInfo
        restBomTemplateInfoMockMvc.perform(get("/api/bom-template-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBomTemplateInfo() throws Exception {
        // Initialize the database
        bomTemplateInfoRepository.saveAndFlush(bomTemplateInfo);

        int databaseSizeBeforeUpdate = bomTemplateInfoRepository.findAll().size();

        // Update the bomTemplateInfo
        BomTemplateInfo updatedBomTemplateInfo = bomTemplateInfoRepository.findById(bomTemplateInfo.getId()).get();
        // Disconnect from session so that the updates on updatedBomTemplateInfo are not directly saved in db
        em.detach(updatedBomTemplateInfo);
        updatedBomTemplateInfo
            .uom(UPDATED_UOM)
            .useQty(UPDATED_USE_QTY)
            .unitPrice(UPDATED_UNIT_PRICE)
            .total(UPDATED_TOTAL)
            .tax(UPDATED_TAX)
            .taxTotal(UPDATED_TAX_TOTAL)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restBomTemplateInfoMockMvc.perform(put("/api/bom-template-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBomTemplateInfo)))
            .andExpect(status().isOk());

        // Validate the BomTemplateInfo in the database
        List<BomTemplateInfo> bomTemplateInfoList = bomTemplateInfoRepository.findAll();
        assertThat(bomTemplateInfoList).hasSize(databaseSizeBeforeUpdate);
        BomTemplateInfo testBomTemplateInfo = bomTemplateInfoList.get(bomTemplateInfoList.size() - 1);
        assertThat(testBomTemplateInfo.getUom()).isEqualTo(UPDATED_UOM);
        assertThat(testBomTemplateInfo.getUseQty()).isEqualTo(UPDATED_USE_QTY);
        assertThat(testBomTemplateInfo.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testBomTemplateInfo.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testBomTemplateInfo.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testBomTemplateInfo.getTaxTotal()).isEqualTo(UPDATED_TAX_TOTAL);
        assertThat(testBomTemplateInfo.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBomTemplateInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBomTemplateInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBomTemplateInfo.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testBomTemplateInfo.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBomTemplateInfo() throws Exception {
        int databaseSizeBeforeUpdate = bomTemplateInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBomTemplateInfoMockMvc.perform(put("/api/bom-template-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bomTemplateInfo)))
            .andExpect(status().isBadRequest());

        // Validate the BomTemplateInfo in the database
        List<BomTemplateInfo> bomTemplateInfoList = bomTemplateInfoRepository.findAll();
        assertThat(bomTemplateInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBomTemplateInfo() throws Exception {
        // Initialize the database
        bomTemplateInfoRepository.saveAndFlush(bomTemplateInfo);

        int databaseSizeBeforeDelete = bomTemplateInfoRepository.findAll().size();

        // Delete the bomTemplateInfo
        restBomTemplateInfoMockMvc.perform(delete("/api/bom-template-infos/{id}", bomTemplateInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BomTemplateInfo> bomTemplateInfoList = bomTemplateInfoRepository.findAll();
        assertThat(bomTemplateInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
