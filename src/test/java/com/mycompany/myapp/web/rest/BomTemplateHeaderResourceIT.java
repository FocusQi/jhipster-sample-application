package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.BomTemplateHeader;
import com.mycompany.myapp.repository.BomTemplateHeaderRepository;

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
 * Integration tests for the {@link BomTemplateHeaderResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BomTemplateHeaderResourceIT {

    private static final String DEFAULT_TEMPLATE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_TYPE = "BBBBBBBBBB";

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
    private BomTemplateHeaderRepository bomTemplateHeaderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBomTemplateHeaderMockMvc;

    private BomTemplateHeader bomTemplateHeader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BomTemplateHeader createEntity(EntityManager em) {
        BomTemplateHeader bomTemplateHeader = new BomTemplateHeader()
            .templateCode(DEFAULT_TEMPLATE_CODE)
            .templateName(DEFAULT_TEMPLATE_NAME)
            .templateType(DEFAULT_TEMPLATE_TYPE)
            .remark(DEFAULT_REMARK)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return bomTemplateHeader;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BomTemplateHeader createUpdatedEntity(EntityManager em) {
        BomTemplateHeader bomTemplateHeader = new BomTemplateHeader()
            .templateCode(UPDATED_TEMPLATE_CODE)
            .templateName(UPDATED_TEMPLATE_NAME)
            .templateType(UPDATED_TEMPLATE_TYPE)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return bomTemplateHeader;
    }

    @BeforeEach
    public void initTest() {
        bomTemplateHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createBomTemplateHeader() throws Exception {
        int databaseSizeBeforeCreate = bomTemplateHeaderRepository.findAll().size();
        // Create the BomTemplateHeader
        restBomTemplateHeaderMockMvc.perform(post("/api/bom-template-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bomTemplateHeader)))
            .andExpect(status().isCreated());

        // Validate the BomTemplateHeader in the database
        List<BomTemplateHeader> bomTemplateHeaderList = bomTemplateHeaderRepository.findAll();
        assertThat(bomTemplateHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        BomTemplateHeader testBomTemplateHeader = bomTemplateHeaderList.get(bomTemplateHeaderList.size() - 1);
        assertThat(testBomTemplateHeader.getTemplateCode()).isEqualTo(DEFAULT_TEMPLATE_CODE);
        assertThat(testBomTemplateHeader.getTemplateName()).isEqualTo(DEFAULT_TEMPLATE_NAME);
        assertThat(testBomTemplateHeader.getTemplateType()).isEqualTo(DEFAULT_TEMPLATE_TYPE);
        assertThat(testBomTemplateHeader.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testBomTemplateHeader.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBomTemplateHeader.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBomTemplateHeader.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testBomTemplateHeader.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBomTemplateHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bomTemplateHeaderRepository.findAll().size();

        // Create the BomTemplateHeader with an existing ID
        bomTemplateHeader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBomTemplateHeaderMockMvc.perform(post("/api/bom-template-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bomTemplateHeader)))
            .andExpect(status().isBadRequest());

        // Validate the BomTemplateHeader in the database
        List<BomTemplateHeader> bomTemplateHeaderList = bomTemplateHeaderRepository.findAll();
        assertThat(bomTemplateHeaderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTemplateCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bomTemplateHeaderRepository.findAll().size();
        // set the field null
        bomTemplateHeader.setTemplateCode(null);

        // Create the BomTemplateHeader, which fails.


        restBomTemplateHeaderMockMvc.perform(post("/api/bom-template-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bomTemplateHeader)))
            .andExpect(status().isBadRequest());

        List<BomTemplateHeader> bomTemplateHeaderList = bomTemplateHeaderRepository.findAll();
        assertThat(bomTemplateHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTemplateNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = bomTemplateHeaderRepository.findAll().size();
        // set the field null
        bomTemplateHeader.setTemplateName(null);

        // Create the BomTemplateHeader, which fails.


        restBomTemplateHeaderMockMvc.perform(post("/api/bom-template-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bomTemplateHeader)))
            .andExpect(status().isBadRequest());

        List<BomTemplateHeader> bomTemplateHeaderList = bomTemplateHeaderRepository.findAll();
        assertThat(bomTemplateHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTemplateTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bomTemplateHeaderRepository.findAll().size();
        // set the field null
        bomTemplateHeader.setTemplateType(null);

        // Create the BomTemplateHeader, which fails.


        restBomTemplateHeaderMockMvc.perform(post("/api/bom-template-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bomTemplateHeader)))
            .andExpect(status().isBadRequest());

        List<BomTemplateHeader> bomTemplateHeaderList = bomTemplateHeaderRepository.findAll();
        assertThat(bomTemplateHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBomTemplateHeaders() throws Exception {
        // Initialize the database
        bomTemplateHeaderRepository.saveAndFlush(bomTemplateHeader);

        // Get all the bomTemplateHeaderList
        restBomTemplateHeaderMockMvc.perform(get("/api/bom-template-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bomTemplateHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].templateCode").value(hasItem(DEFAULT_TEMPLATE_CODE)))
            .andExpect(jsonPath("$.[*].templateName").value(hasItem(DEFAULT_TEMPLATE_NAME)))
            .andExpect(jsonPath("$.[*].templateType").value(hasItem(DEFAULT_TEMPLATE_TYPE)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getBomTemplateHeader() throws Exception {
        // Initialize the database
        bomTemplateHeaderRepository.saveAndFlush(bomTemplateHeader);

        // Get the bomTemplateHeader
        restBomTemplateHeaderMockMvc.perform(get("/api/bom-template-headers/{id}", bomTemplateHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bomTemplateHeader.getId().intValue()))
            .andExpect(jsonPath("$.templateCode").value(DEFAULT_TEMPLATE_CODE))
            .andExpect(jsonPath("$.templateName").value(DEFAULT_TEMPLATE_NAME))
            .andExpect(jsonPath("$.templateType").value(DEFAULT_TEMPLATE_TYPE))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBomTemplateHeader() throws Exception {
        // Get the bomTemplateHeader
        restBomTemplateHeaderMockMvc.perform(get("/api/bom-template-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBomTemplateHeader() throws Exception {
        // Initialize the database
        bomTemplateHeaderRepository.saveAndFlush(bomTemplateHeader);

        int databaseSizeBeforeUpdate = bomTemplateHeaderRepository.findAll().size();

        // Update the bomTemplateHeader
        BomTemplateHeader updatedBomTemplateHeader = bomTemplateHeaderRepository.findById(bomTemplateHeader.getId()).get();
        // Disconnect from session so that the updates on updatedBomTemplateHeader are not directly saved in db
        em.detach(updatedBomTemplateHeader);
        updatedBomTemplateHeader
            .templateCode(UPDATED_TEMPLATE_CODE)
            .templateName(UPDATED_TEMPLATE_NAME)
            .templateType(UPDATED_TEMPLATE_TYPE)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restBomTemplateHeaderMockMvc.perform(put("/api/bom-template-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBomTemplateHeader)))
            .andExpect(status().isOk());

        // Validate the BomTemplateHeader in the database
        List<BomTemplateHeader> bomTemplateHeaderList = bomTemplateHeaderRepository.findAll();
        assertThat(bomTemplateHeaderList).hasSize(databaseSizeBeforeUpdate);
        BomTemplateHeader testBomTemplateHeader = bomTemplateHeaderList.get(bomTemplateHeaderList.size() - 1);
        assertThat(testBomTemplateHeader.getTemplateCode()).isEqualTo(UPDATED_TEMPLATE_CODE);
        assertThat(testBomTemplateHeader.getTemplateName()).isEqualTo(UPDATED_TEMPLATE_NAME);
        assertThat(testBomTemplateHeader.getTemplateType()).isEqualTo(UPDATED_TEMPLATE_TYPE);
        assertThat(testBomTemplateHeader.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBomTemplateHeader.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBomTemplateHeader.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBomTemplateHeader.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testBomTemplateHeader.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBomTemplateHeader() throws Exception {
        int databaseSizeBeforeUpdate = bomTemplateHeaderRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBomTemplateHeaderMockMvc.perform(put("/api/bom-template-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bomTemplateHeader)))
            .andExpect(status().isBadRequest());

        // Validate the BomTemplateHeader in the database
        List<BomTemplateHeader> bomTemplateHeaderList = bomTemplateHeaderRepository.findAll();
        assertThat(bomTemplateHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBomTemplateHeader() throws Exception {
        // Initialize the database
        bomTemplateHeaderRepository.saveAndFlush(bomTemplateHeader);

        int databaseSizeBeforeDelete = bomTemplateHeaderRepository.findAll().size();

        // Delete the bomTemplateHeader
        restBomTemplateHeaderMockMvc.perform(delete("/api/bom-template-headers/{id}", bomTemplateHeader.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BomTemplateHeader> bomTemplateHeaderList = bomTemplateHeaderRepository.findAll();
        assertThat(bomTemplateHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
