package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.ReturnHeader;
import com.mycompany.myapp.repository.ReturnHeaderRepository;

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
 * Integration tests for the {@link ReturnHeaderResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ReturnHeaderResourceIT {

    private static final Integer DEFAULT_FROM_NUM = 1;
    private static final Integer UPDATED_FROM_NUM = 2;

    private static final String DEFAULT_V_CODE = "AAAAAAAAAA";
    private static final String UPDATED_V_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_M_CODE = "AAAAAAAAAA";
    private static final String UPDATED_M_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ReturnHeaderRepository returnHeaderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReturnHeaderMockMvc;

    private ReturnHeader returnHeader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReturnHeader createEntity(EntityManager em) {
        ReturnHeader returnHeader = new ReturnHeader()
            .fromNum(DEFAULT_FROM_NUM)
            .vCode(DEFAULT_V_CODE)
            .mCode(DEFAULT_M_CODE)
            .quantity(DEFAULT_QUANTITY)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return returnHeader;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReturnHeader createUpdatedEntity(EntityManager em) {
        ReturnHeader returnHeader = new ReturnHeader()
            .fromNum(UPDATED_FROM_NUM)
            .vCode(UPDATED_V_CODE)
            .mCode(UPDATED_M_CODE)
            .quantity(UPDATED_QUANTITY)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return returnHeader;
    }

    @BeforeEach
    public void initTest() {
        returnHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createReturnHeader() throws Exception {
        int databaseSizeBeforeCreate = returnHeaderRepository.findAll().size();
        // Create the ReturnHeader
        restReturnHeaderMockMvc.perform(post("/api/return-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(returnHeader)))
            .andExpect(status().isCreated());

        // Validate the ReturnHeader in the database
        List<ReturnHeader> returnHeaderList = returnHeaderRepository.findAll();
        assertThat(returnHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        ReturnHeader testReturnHeader = returnHeaderList.get(returnHeaderList.size() - 1);
        assertThat(testReturnHeader.getFromNum()).isEqualTo(DEFAULT_FROM_NUM);
        assertThat(testReturnHeader.getvCode()).isEqualTo(DEFAULT_V_CODE);
        assertThat(testReturnHeader.getmCode()).isEqualTo(DEFAULT_M_CODE);
        assertThat(testReturnHeader.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testReturnHeader.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testReturnHeader.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testReturnHeader.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testReturnHeader.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createReturnHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = returnHeaderRepository.findAll().size();

        // Create the ReturnHeader with an existing ID
        returnHeader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReturnHeaderMockMvc.perform(post("/api/return-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(returnHeader)))
            .andExpect(status().isBadRequest());

        // Validate the ReturnHeader in the database
        List<ReturnHeader> returnHeaderList = returnHeaderRepository.findAll();
        assertThat(returnHeaderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReturnHeaders() throws Exception {
        // Initialize the database
        returnHeaderRepository.saveAndFlush(returnHeader);

        // Get all the returnHeaderList
        restReturnHeaderMockMvc.perform(get("/api/return-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(returnHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromNum").value(hasItem(DEFAULT_FROM_NUM)))
            .andExpect(jsonPath("$.[*].vCode").value(hasItem(DEFAULT_V_CODE)))
            .andExpect(jsonPath("$.[*].mCode").value(hasItem(DEFAULT_M_CODE)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getReturnHeader() throws Exception {
        // Initialize the database
        returnHeaderRepository.saveAndFlush(returnHeader);

        // Get the returnHeader
        restReturnHeaderMockMvc.perform(get("/api/return-headers/{id}", returnHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(returnHeader.getId().intValue()))
            .andExpect(jsonPath("$.fromNum").value(DEFAULT_FROM_NUM))
            .andExpect(jsonPath("$.vCode").value(DEFAULT_V_CODE))
            .andExpect(jsonPath("$.mCode").value(DEFAULT_M_CODE))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingReturnHeader() throws Exception {
        // Get the returnHeader
        restReturnHeaderMockMvc.perform(get("/api/return-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReturnHeader() throws Exception {
        // Initialize the database
        returnHeaderRepository.saveAndFlush(returnHeader);

        int databaseSizeBeforeUpdate = returnHeaderRepository.findAll().size();

        // Update the returnHeader
        ReturnHeader updatedReturnHeader = returnHeaderRepository.findById(returnHeader.getId()).get();
        // Disconnect from session so that the updates on updatedReturnHeader are not directly saved in db
        em.detach(updatedReturnHeader);
        updatedReturnHeader
            .fromNum(UPDATED_FROM_NUM)
            .vCode(UPDATED_V_CODE)
            .mCode(UPDATED_M_CODE)
            .quantity(UPDATED_QUANTITY)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restReturnHeaderMockMvc.perform(put("/api/return-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedReturnHeader)))
            .andExpect(status().isOk());

        // Validate the ReturnHeader in the database
        List<ReturnHeader> returnHeaderList = returnHeaderRepository.findAll();
        assertThat(returnHeaderList).hasSize(databaseSizeBeforeUpdate);
        ReturnHeader testReturnHeader = returnHeaderList.get(returnHeaderList.size() - 1);
        assertThat(testReturnHeader.getFromNum()).isEqualTo(UPDATED_FROM_NUM);
        assertThat(testReturnHeader.getvCode()).isEqualTo(UPDATED_V_CODE);
        assertThat(testReturnHeader.getmCode()).isEqualTo(UPDATED_M_CODE);
        assertThat(testReturnHeader.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testReturnHeader.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testReturnHeader.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testReturnHeader.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testReturnHeader.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingReturnHeader() throws Exception {
        int databaseSizeBeforeUpdate = returnHeaderRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReturnHeaderMockMvc.perform(put("/api/return-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(returnHeader)))
            .andExpect(status().isBadRequest());

        // Validate the ReturnHeader in the database
        List<ReturnHeader> returnHeaderList = returnHeaderRepository.findAll();
        assertThat(returnHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReturnHeader() throws Exception {
        // Initialize the database
        returnHeaderRepository.saveAndFlush(returnHeader);

        int databaseSizeBeforeDelete = returnHeaderRepository.findAll().size();

        // Delete the returnHeader
        restReturnHeaderMockMvc.perform(delete("/api/return-headers/{id}", returnHeader.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReturnHeader> returnHeaderList = returnHeaderRepository.findAll();
        assertThat(returnHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
