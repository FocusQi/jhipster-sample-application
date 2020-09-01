package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.BiddingQuotationHeader;
import com.mega.project.srm.domain.enumeration.BiddingStatus;
import com.mega.project.srm.domain.enumeration.BiddingType;
import com.mega.project.srm.domain.enumeration.ModeType;
import com.mega.project.srm.repository.BiddingQuotationHeaderRepository;
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
 * Integration tests for the {@link BiddingQuotationHeaderResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BiddingQuotationHeaderResourceIT {
    private static final String DEFAULT_BIDDING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BIDDING_CODE = "BBBBBBBBBB";

    private static final ModeType DEFAULT_MODE_TYPE = ModeType.BIDDING;
    private static final ModeType UPDATED_MODE_TYPE = ModeType.INQUIRY;

    private static final BiddingType DEFAULT_BIDDING_TYPE = BiddingType.PO_ORDER_BIDDING;
    private static final BiddingType UPDATED_BIDDING_TYPE = BiddingType.NON_MATERIAL_BIDDING;

    private static final LocalDate DEFAULT_ISSUANCE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ISSUANCE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_BIDDING_TIMES = 1;
    private static final Integer UPDATED_BIDDING_TIMES = 2;

    private static final Integer DEFAULT_SUM_TIMES = 1;
    private static final Integer UPDATED_SUM_TIMES = 2;

    private static final Integer DEFAULT_MAX_QUOTATION_TIMES = 1;
    private static final Integer UPDATED_MAX_QUOTATION_TIMES = 2;

    private static final BiddingStatus DEFAULT_STATUS = BiddingStatus.BIDDING;
    private static final BiddingStatus UPDATED_STATUS = BiddingStatus.COMPLETE;

    private static final Boolean DEFAULT_COST_LIST_REQUIRED = false;
    private static final Boolean UPDATED_COST_LIST_REQUIRED = true;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BiddingQuotationHeaderRepository biddingQuotationHeaderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBiddingQuotationHeaderMockMvc;

    private BiddingQuotationHeader biddingQuotationHeader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingQuotationHeader createEntity(EntityManager em) {
        BiddingQuotationHeader biddingQuotationHeader = new BiddingQuotationHeader()
            .biddingCode(DEFAULT_BIDDING_CODE)
            .modeType(DEFAULT_MODE_TYPE)
            .biddingType(DEFAULT_BIDDING_TYPE)
            .issuanceDate(DEFAULT_ISSUANCE_DATE)
            .biddingTimes(DEFAULT_BIDDING_TIMES)
            .sumTimes(DEFAULT_SUM_TIMES)
            .maxQuotationTimes(DEFAULT_MAX_QUOTATION_TIMES)
            .status(DEFAULT_STATUS)
            .costListRequired(DEFAULT_COST_LIST_REQUIRED)
            .description(DEFAULT_DESCRIPTION)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return biddingQuotationHeader;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingQuotationHeader createUpdatedEntity(EntityManager em) {
        BiddingQuotationHeader biddingQuotationHeader = new BiddingQuotationHeader()
            .biddingCode(UPDATED_BIDDING_CODE)
            .modeType(UPDATED_MODE_TYPE)
            .biddingType(UPDATED_BIDDING_TYPE)
            .issuanceDate(UPDATED_ISSUANCE_DATE)
            .biddingTimes(UPDATED_BIDDING_TIMES)
            .sumTimes(UPDATED_SUM_TIMES)
            .maxQuotationTimes(UPDATED_MAX_QUOTATION_TIMES)
            .status(UPDATED_STATUS)
            .costListRequired(UPDATED_COST_LIST_REQUIRED)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return biddingQuotationHeader;
    }

    @BeforeEach
    public void initTest() {
        biddingQuotationHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createBiddingQuotationHeader() throws Exception {
        int databaseSizeBeforeCreate = biddingQuotationHeaderRepository.findAll().size();
        // Create the BiddingQuotationHeader
        restBiddingQuotationHeaderMockMvc
            .perform(
                post("/api/bidding-quotation-headers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationHeader))
            )
            .andExpect(status().isCreated());

        // Validate the BiddingQuotationHeader in the database
        List<BiddingQuotationHeader> biddingQuotationHeaderList = biddingQuotationHeaderRepository.findAll();
        assertThat(biddingQuotationHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        BiddingQuotationHeader testBiddingQuotationHeader = biddingQuotationHeaderList.get(biddingQuotationHeaderList.size() - 1);
        assertThat(testBiddingQuotationHeader.getBiddingCode()).isEqualTo(DEFAULT_BIDDING_CODE);
        assertThat(testBiddingQuotationHeader.getModeType()).isEqualTo(DEFAULT_MODE_TYPE);
        assertThat(testBiddingQuotationHeader.getBiddingType()).isEqualTo(DEFAULT_BIDDING_TYPE);
        assertThat(testBiddingQuotationHeader.getIssuanceDate()).isEqualTo(DEFAULT_ISSUANCE_DATE);
        assertThat(testBiddingQuotationHeader.getBiddingTimes()).isEqualTo(DEFAULT_BIDDING_TIMES);
        assertThat(testBiddingQuotationHeader.getSumTimes()).isEqualTo(DEFAULT_SUM_TIMES);
        assertThat(testBiddingQuotationHeader.getMaxQuotationTimes()).isEqualTo(DEFAULT_MAX_QUOTATION_TIMES);
        assertThat(testBiddingQuotationHeader.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBiddingQuotationHeader.isCostListRequired()).isEqualTo(DEFAULT_COST_LIST_REQUIRED);
        assertThat(testBiddingQuotationHeader.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBiddingQuotationHeader.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBiddingQuotationHeader.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBiddingQuotationHeader.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testBiddingQuotationHeader.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBiddingQuotationHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = biddingQuotationHeaderRepository.findAll().size();

        // Create the BiddingQuotationHeader with an existing ID
        biddingQuotationHeader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBiddingQuotationHeaderMockMvc
            .perform(
                post("/api/bidding-quotation-headers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingQuotationHeader in the database
        List<BiddingQuotationHeader> biddingQuotationHeaderList = biddingQuotationHeaderRepository.findAll();
        assertThat(biddingQuotationHeaderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBiddingCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingQuotationHeaderRepository.findAll().size();
        // set the field null
        biddingQuotationHeader.setBiddingCode(null);

        // Create the BiddingQuotationHeader, which fails.

        restBiddingQuotationHeaderMockMvc
            .perform(
                post("/api/bidding-quotation-headers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationHeader))
            )
            .andExpect(status().isBadRequest());

        List<BiddingQuotationHeader> biddingQuotationHeaderList = biddingQuotationHeaderRepository.findAll();
        assertThat(biddingQuotationHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModeTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingQuotationHeaderRepository.findAll().size();
        // set the field null
        biddingQuotationHeader.setModeType(null);

        // Create the BiddingQuotationHeader, which fails.

        restBiddingQuotationHeaderMockMvc
            .perform(
                post("/api/bidding-quotation-headers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationHeader))
            )
            .andExpect(status().isBadRequest());

        List<BiddingQuotationHeader> biddingQuotationHeaderList = biddingQuotationHeaderRepository.findAll();
        assertThat(biddingQuotationHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBiddingTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingQuotationHeaderRepository.findAll().size();
        // set the field null
        biddingQuotationHeader.setBiddingType(null);

        // Create the BiddingQuotationHeader, which fails.

        restBiddingQuotationHeaderMockMvc
            .perform(
                post("/api/bidding-quotation-headers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationHeader))
            )
            .andExpect(status().isBadRequest());

        List<BiddingQuotationHeader> biddingQuotationHeaderList = biddingQuotationHeaderRepository.findAll();
        assertThat(biddingQuotationHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIssuanceDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingQuotationHeaderRepository.findAll().size();
        // set the field null
        biddingQuotationHeader.setIssuanceDate(null);

        // Create the BiddingQuotationHeader, which fails.

        restBiddingQuotationHeaderMockMvc
            .perform(
                post("/api/bidding-quotation-headers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationHeader))
            )
            .andExpect(status().isBadRequest());

        List<BiddingQuotationHeader> biddingQuotationHeaderList = biddingQuotationHeaderRepository.findAll();
        assertThat(biddingQuotationHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingQuotationHeaderRepository.findAll().size();
        // set the field null
        biddingQuotationHeader.setStatus(null);

        // Create the BiddingQuotationHeader, which fails.

        restBiddingQuotationHeaderMockMvc
            .perform(
                post("/api/bidding-quotation-headers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationHeader))
            )
            .andExpect(status().isBadRequest());

        List<BiddingQuotationHeader> biddingQuotationHeaderList = biddingQuotationHeaderRepository.findAll();
        assertThat(biddingQuotationHeaderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBiddingQuotationHeaders() throws Exception {
        // Initialize the database
        biddingQuotationHeaderRepository.saveAndFlush(biddingQuotationHeader);

        // Get all the biddingQuotationHeaderList
        restBiddingQuotationHeaderMockMvc
            .perform(get("/api/bidding-quotation-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(biddingQuotationHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].biddingCode").value(hasItem(DEFAULT_BIDDING_CODE)))
            .andExpect(jsonPath("$.[*].modeType").value(hasItem(DEFAULT_MODE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].biddingType").value(hasItem(DEFAULT_BIDDING_TYPE.toString())))
            .andExpect(jsonPath("$.[*].issuanceDate").value(hasItem(DEFAULT_ISSUANCE_DATE.toString())))
            .andExpect(jsonPath("$.[*].biddingTimes").value(hasItem(DEFAULT_BIDDING_TIMES)))
            .andExpect(jsonPath("$.[*].sumTimes").value(hasItem(DEFAULT_SUM_TIMES)))
            .andExpect(jsonPath("$.[*].maxQuotationTimes").value(hasItem(DEFAULT_MAX_QUOTATION_TIMES)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].costListRequired").value(hasItem(DEFAULT_COST_LIST_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getBiddingQuotationHeader() throws Exception {
        // Initialize the database
        biddingQuotationHeaderRepository.saveAndFlush(biddingQuotationHeader);

        // Get the biddingQuotationHeader
        restBiddingQuotationHeaderMockMvc
            .perform(get("/api/bidding-quotation-headers/{id}", biddingQuotationHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(biddingQuotationHeader.getId().intValue()))
            .andExpect(jsonPath("$.biddingCode").value(DEFAULT_BIDDING_CODE))
            .andExpect(jsonPath("$.modeType").value(DEFAULT_MODE_TYPE.toString()))
            .andExpect(jsonPath("$.biddingType").value(DEFAULT_BIDDING_TYPE.toString()))
            .andExpect(jsonPath("$.issuanceDate").value(DEFAULT_ISSUANCE_DATE.toString()))
            .andExpect(jsonPath("$.biddingTimes").value(DEFAULT_BIDDING_TIMES))
            .andExpect(jsonPath("$.sumTimes").value(DEFAULT_SUM_TIMES))
            .andExpect(jsonPath("$.maxQuotationTimes").value(DEFAULT_MAX_QUOTATION_TIMES))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.costListRequired").value(DEFAULT_COST_LIST_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBiddingQuotationHeader() throws Exception {
        // Get the biddingQuotationHeader
        restBiddingQuotationHeaderMockMvc
            .perform(get("/api/bidding-quotation-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBiddingQuotationHeader() throws Exception {
        // Initialize the database
        biddingQuotationHeaderRepository.saveAndFlush(biddingQuotationHeader);

        int databaseSizeBeforeUpdate = biddingQuotationHeaderRepository.findAll().size();

        // Update the biddingQuotationHeader
        BiddingQuotationHeader updatedBiddingQuotationHeader = biddingQuotationHeaderRepository
            .findById(biddingQuotationHeader.getId())
            .get();
        // Disconnect from session so that the updates on updatedBiddingQuotationHeader are not directly saved in db
        em.detach(updatedBiddingQuotationHeader);
        updatedBiddingQuotationHeader
            .biddingCode(UPDATED_BIDDING_CODE)
            .modeType(UPDATED_MODE_TYPE)
            .biddingType(UPDATED_BIDDING_TYPE)
            .issuanceDate(UPDATED_ISSUANCE_DATE)
            .biddingTimes(UPDATED_BIDDING_TIMES)
            .sumTimes(UPDATED_SUM_TIMES)
            .maxQuotationTimes(UPDATED_MAX_QUOTATION_TIMES)
            .status(UPDATED_STATUS)
            .costListRequired(UPDATED_COST_LIST_REQUIRED)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restBiddingQuotationHeaderMockMvc
            .perform(
                put("/api/bidding-quotation-headers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBiddingQuotationHeader))
            )
            .andExpect(status().isOk());

        // Validate the BiddingQuotationHeader in the database
        List<BiddingQuotationHeader> biddingQuotationHeaderList = biddingQuotationHeaderRepository.findAll();
        assertThat(biddingQuotationHeaderList).hasSize(databaseSizeBeforeUpdate);
        BiddingQuotationHeader testBiddingQuotationHeader = biddingQuotationHeaderList.get(biddingQuotationHeaderList.size() - 1);
        assertThat(testBiddingQuotationHeader.getBiddingCode()).isEqualTo(UPDATED_BIDDING_CODE);
        assertThat(testBiddingQuotationHeader.getModeType()).isEqualTo(UPDATED_MODE_TYPE);
        assertThat(testBiddingQuotationHeader.getBiddingType()).isEqualTo(UPDATED_BIDDING_TYPE);
        assertThat(testBiddingQuotationHeader.getIssuanceDate()).isEqualTo(UPDATED_ISSUANCE_DATE);
        assertThat(testBiddingQuotationHeader.getBiddingTimes()).isEqualTo(UPDATED_BIDDING_TIMES);
        assertThat(testBiddingQuotationHeader.getSumTimes()).isEqualTo(UPDATED_SUM_TIMES);
        assertThat(testBiddingQuotationHeader.getMaxQuotationTimes()).isEqualTo(UPDATED_MAX_QUOTATION_TIMES);
        assertThat(testBiddingQuotationHeader.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBiddingQuotationHeader.isCostListRequired()).isEqualTo(UPDATED_COST_LIST_REQUIRED);
        assertThat(testBiddingQuotationHeader.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBiddingQuotationHeader.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBiddingQuotationHeader.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBiddingQuotationHeader.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testBiddingQuotationHeader.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBiddingQuotationHeader() throws Exception {
        int databaseSizeBeforeUpdate = biddingQuotationHeaderRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBiddingQuotationHeaderMockMvc
            .perform(
                put("/api/bidding-quotation-headers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationHeader))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingQuotationHeader in the database
        List<BiddingQuotationHeader> biddingQuotationHeaderList = biddingQuotationHeaderRepository.findAll();
        assertThat(biddingQuotationHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBiddingQuotationHeader() throws Exception {
        // Initialize the database
        biddingQuotationHeaderRepository.saveAndFlush(biddingQuotationHeader);

        int databaseSizeBeforeDelete = biddingQuotationHeaderRepository.findAll().size();

        // Delete the biddingQuotationHeader
        restBiddingQuotationHeaderMockMvc
            .perform(delete("/api/bidding-quotation-headers/{id}", biddingQuotationHeader.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BiddingQuotationHeader> biddingQuotationHeaderList = biddingQuotationHeaderRepository.findAll();
        assertThat(biddingQuotationHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
