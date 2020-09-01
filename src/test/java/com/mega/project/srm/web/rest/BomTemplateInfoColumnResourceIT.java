package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.BomTemplateInfoColumn;
import com.mega.project.srm.repository.BomTemplateInfoColumnRepository;
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
 * Integration tests for the {@link BomTemplateInfoColumnResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BomTemplateInfoColumnResourceIT {
    private static final Integer DEFAULT_SORT_INDEX = 1;
    private static final Integer UPDATED_SORT_INDEX = 2;

    private static final String DEFAULT_COLUMN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_VALUE = "BBBBBBBBBB";

    @Autowired
    private BomTemplateInfoColumnRepository bomTemplateInfoColumnRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBomTemplateInfoColumnMockMvc;

    private BomTemplateInfoColumn bomTemplateInfoColumn;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BomTemplateInfoColumn createEntity(EntityManager em) {
        BomTemplateInfoColumn bomTemplateInfoColumn = new BomTemplateInfoColumn()
            .sortIndex(DEFAULT_SORT_INDEX)
            .columnName(DEFAULT_COLUMN_NAME)
            .columnValue(DEFAULT_COLUMN_VALUE);
        return bomTemplateInfoColumn;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BomTemplateInfoColumn createUpdatedEntity(EntityManager em) {
        BomTemplateInfoColumn bomTemplateInfoColumn = new BomTemplateInfoColumn()
            .sortIndex(UPDATED_SORT_INDEX)
            .columnName(UPDATED_COLUMN_NAME)
            .columnValue(UPDATED_COLUMN_VALUE);
        return bomTemplateInfoColumn;
    }

    @BeforeEach
    public void initTest() {
        bomTemplateInfoColumn = createEntity(em);
    }

    @Test
    @Transactional
    public void createBomTemplateInfoColumn() throws Exception {
        int databaseSizeBeforeCreate = bomTemplateInfoColumnRepository.findAll().size();
        // Create the BomTemplateInfoColumn
        restBomTemplateInfoColumnMockMvc
            .perform(
                post("/api/bom-template-info-columns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bomTemplateInfoColumn))
            )
            .andExpect(status().isCreated());

        // Validate the BomTemplateInfoColumn in the database
        List<BomTemplateInfoColumn> bomTemplateInfoColumnList = bomTemplateInfoColumnRepository.findAll();
        assertThat(bomTemplateInfoColumnList).hasSize(databaseSizeBeforeCreate + 1);
        BomTemplateInfoColumn testBomTemplateInfoColumn = bomTemplateInfoColumnList.get(bomTemplateInfoColumnList.size() - 1);
        assertThat(testBomTemplateInfoColumn.getSortIndex()).isEqualTo(DEFAULT_SORT_INDEX);
        assertThat(testBomTemplateInfoColumn.getColumnName()).isEqualTo(DEFAULT_COLUMN_NAME);
        assertThat(testBomTemplateInfoColumn.getColumnValue()).isEqualTo(DEFAULT_COLUMN_VALUE);
    }

    @Test
    @Transactional
    public void createBomTemplateInfoColumnWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bomTemplateInfoColumnRepository.findAll().size();

        // Create the BomTemplateInfoColumn with an existing ID
        bomTemplateInfoColumn.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBomTemplateInfoColumnMockMvc
            .perform(
                post("/api/bom-template-info-columns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bomTemplateInfoColumn))
            )
            .andExpect(status().isBadRequest());

        // Validate the BomTemplateInfoColumn in the database
        List<BomTemplateInfoColumn> bomTemplateInfoColumnList = bomTemplateInfoColumnRepository.findAll();
        assertThat(bomTemplateInfoColumnList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBomTemplateInfoColumns() throws Exception {
        // Initialize the database
        bomTemplateInfoColumnRepository.saveAndFlush(bomTemplateInfoColumn);

        // Get all the bomTemplateInfoColumnList
        restBomTemplateInfoColumnMockMvc
            .perform(get("/api/bom-template-info-columns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bomTemplateInfoColumn.getId().intValue())))
            .andExpect(jsonPath("$.[*].sortIndex").value(hasItem(DEFAULT_SORT_INDEX)))
            .andExpect(jsonPath("$.[*].columnName").value(hasItem(DEFAULT_COLUMN_NAME)))
            .andExpect(jsonPath("$.[*].columnValue").value(hasItem(DEFAULT_COLUMN_VALUE)));
    }

    @Test
    @Transactional
    public void getBomTemplateInfoColumn() throws Exception {
        // Initialize the database
        bomTemplateInfoColumnRepository.saveAndFlush(bomTemplateInfoColumn);

        // Get the bomTemplateInfoColumn
        restBomTemplateInfoColumnMockMvc
            .perform(get("/api/bom-template-info-columns/{id}", bomTemplateInfoColumn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bomTemplateInfoColumn.getId().intValue()))
            .andExpect(jsonPath("$.sortIndex").value(DEFAULT_SORT_INDEX))
            .andExpect(jsonPath("$.columnName").value(DEFAULT_COLUMN_NAME))
            .andExpect(jsonPath("$.columnValue").value(DEFAULT_COLUMN_VALUE));
    }

    @Test
    @Transactional
    public void getNonExistingBomTemplateInfoColumn() throws Exception {
        // Get the bomTemplateInfoColumn
        restBomTemplateInfoColumnMockMvc
            .perform(get("/api/bom-template-info-columns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBomTemplateInfoColumn() throws Exception {
        // Initialize the database
        bomTemplateInfoColumnRepository.saveAndFlush(bomTemplateInfoColumn);

        int databaseSizeBeforeUpdate = bomTemplateInfoColumnRepository.findAll().size();

        // Update the bomTemplateInfoColumn
        BomTemplateInfoColumn updatedBomTemplateInfoColumn = bomTemplateInfoColumnRepository.findById(bomTemplateInfoColumn.getId()).get();
        // Disconnect from session so that the updates on updatedBomTemplateInfoColumn are not directly saved in db
        em.detach(updatedBomTemplateInfoColumn);
        updatedBomTemplateInfoColumn.sortIndex(UPDATED_SORT_INDEX).columnName(UPDATED_COLUMN_NAME).columnValue(UPDATED_COLUMN_VALUE);

        restBomTemplateInfoColumnMockMvc
            .perform(
                put("/api/bom-template-info-columns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBomTemplateInfoColumn))
            )
            .andExpect(status().isOk());

        // Validate the BomTemplateInfoColumn in the database
        List<BomTemplateInfoColumn> bomTemplateInfoColumnList = bomTemplateInfoColumnRepository.findAll();
        assertThat(bomTemplateInfoColumnList).hasSize(databaseSizeBeforeUpdate);
        BomTemplateInfoColumn testBomTemplateInfoColumn = bomTemplateInfoColumnList.get(bomTemplateInfoColumnList.size() - 1);
        assertThat(testBomTemplateInfoColumn.getSortIndex()).isEqualTo(UPDATED_SORT_INDEX);
        assertThat(testBomTemplateInfoColumn.getColumnName()).isEqualTo(UPDATED_COLUMN_NAME);
        assertThat(testBomTemplateInfoColumn.getColumnValue()).isEqualTo(UPDATED_COLUMN_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingBomTemplateInfoColumn() throws Exception {
        int databaseSizeBeforeUpdate = bomTemplateInfoColumnRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBomTemplateInfoColumnMockMvc
            .perform(
                put("/api/bom-template-info-columns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bomTemplateInfoColumn))
            )
            .andExpect(status().isBadRequest());

        // Validate the BomTemplateInfoColumn in the database
        List<BomTemplateInfoColumn> bomTemplateInfoColumnList = bomTemplateInfoColumnRepository.findAll();
        assertThat(bomTemplateInfoColumnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBomTemplateInfoColumn() throws Exception {
        // Initialize the database
        bomTemplateInfoColumnRepository.saveAndFlush(bomTemplateInfoColumn);

        int databaseSizeBeforeDelete = bomTemplateInfoColumnRepository.findAll().size();

        // Delete the bomTemplateInfoColumn
        restBomTemplateInfoColumnMockMvc
            .perform(delete("/api/bom-template-info-columns/{id}", bomTemplateInfoColumn.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BomTemplateInfoColumn> bomTemplateInfoColumnList = bomTemplateInfoColumnRepository.findAll();
        assertThat(bomTemplateInfoColumnList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
