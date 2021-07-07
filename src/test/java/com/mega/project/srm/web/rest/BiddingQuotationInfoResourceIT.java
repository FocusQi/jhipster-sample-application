package com.mega.project.srm.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mega.project.srm.MegaframeworkApp;
import com.mega.project.srm.domain.BiddingQuotationInfo;
import com.mega.project.srm.domain.enumeration.QuotationStatus;
import com.mega.project.srm.repository.BiddingQuotationInfoRepository;
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
 * Integration tests for the {@link BiddingQuotationInfoResource} REST controller.
 */
@SpringBootTest(classes = MegaframeworkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BiddingQuotationInfoResourceIT {
    private static final Double DEFAULT_LATEST_QUOTATION = 1D;
    private static final Double UPDATED_LATEST_QUOTATION = 2D;

    private static final Double DEFAULT_MIN_QTY = 1D;
    private static final Double UPDATED_MIN_QTY = 2D;

    private static final LocalDate DEFAULT_VALID_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VALID_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_DELIVERY_DAYS = 1;
    private static final Integer UPDATED_DELIVERY_DAYS = 2;

    private static final Integer DEFAULT_HISTORY_PRICE = 1;
    private static final Integer UPDATED_HISTORY_PRICE = 2;

    private static final Integer DEFAULT_CURRENT_TAX_PRICE = 1;
    private static final Integer UPDATED_CURRENT_TAX_PRICE = 2;

    private static final LocalDate DEFAULT_CURRENT_PRICE_VALID_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CURRENT_PRICE_VALID_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_NET_PRICE = 1D;
    private static final Double UPDATED_NET_PRICE = 2D;

    private static final String DEFAULT_NET_URL = "AAAAAAAAAA";
    private static final String UPDATED_NET_URL = "BBBBBBBBBB";

    private static final QuotationStatus DEFAULT_STATUS = QuotationStatus.BEGIN;
    private static final QuotationStatus UPDATED_STATUS = QuotationStatus.COMPLETE;

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
    private BiddingQuotationInfoRepository biddingQuotationInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBiddingQuotationInfoMockMvc;

    private BiddingQuotationInfo biddingQuotationInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingQuotationInfo createEntity(EntityManager em) {
        BiddingQuotationInfo biddingQuotationInfo = new BiddingQuotationInfo()
            .latestQuotation(DEFAULT_LATEST_QUOTATION)
            .minQty(DEFAULT_MIN_QTY)
            .validDate(DEFAULT_VALID_DATE)
            .deliveryDays(DEFAULT_DELIVERY_DAYS)
            .historyPrice(DEFAULT_HISTORY_PRICE)
            .currentTaxPrice(DEFAULT_CURRENT_TAX_PRICE)
            .currentPriceValidDate(DEFAULT_CURRENT_PRICE_VALID_DATE)
            .netPrice(DEFAULT_NET_PRICE)
            .netUrl(DEFAULT_NET_URL)
            .status(DEFAULT_STATUS)
            .remark(DEFAULT_REMARK)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return biddingQuotationInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BiddingQuotationInfo createUpdatedEntity(EntityManager em) {
        BiddingQuotationInfo biddingQuotationInfo = new BiddingQuotationInfo()
            .latestQuotation(UPDATED_LATEST_QUOTATION)
            .minQty(UPDATED_MIN_QTY)
            .validDate(UPDATED_VALID_DATE)
            .deliveryDays(UPDATED_DELIVERY_DAYS)
            .historyPrice(UPDATED_HISTORY_PRICE)
            .currentTaxPrice(UPDATED_CURRENT_TAX_PRICE)
            .currentPriceValidDate(UPDATED_CURRENT_PRICE_VALID_DATE)
            .netPrice(UPDATED_NET_PRICE)
            .netUrl(UPDATED_NET_URL)
            .status(UPDATED_STATUS)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return biddingQuotationInfo;
    }

    @BeforeEach
    public void initTest() {
        biddingQuotationInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createBiddingQuotationInfo() throws Exception {
        int databaseSizeBeforeCreate = biddingQuotationInfoRepository.findAll().size();
        // Create the BiddingQuotationInfo
        restBiddingQuotationInfoMockMvc
            .perform(
                post("/api/bidding-quotation-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationInfo))
            )
            .andExpect(status().isCreated());

        // Validate the BiddingQuotationInfo in the database
        List<BiddingQuotationInfo> biddingQuotationInfoList = biddingQuotationInfoRepository.findAll();
        assertThat(biddingQuotationInfoList).hasSize(databaseSizeBeforeCreate + 1);
        BiddingQuotationInfo testBiddingQuotationInfo = biddingQuotationInfoList.get(biddingQuotationInfoList.size() - 1);
        assertThat(testBiddingQuotationInfo.getLatestQuotation()).isEqualTo(DEFAULT_LATEST_QUOTATION);
        assertThat(testBiddingQuotationInfo.getMinQty()).isEqualTo(DEFAULT_MIN_QTY);
        assertThat(testBiddingQuotationInfo.getValidDate()).isEqualTo(DEFAULT_VALID_DATE);
        assertThat(testBiddingQuotationInfo.getDeliveryDays()).isEqualTo(DEFAULT_DELIVERY_DAYS);
        assertThat(testBiddingQuotationInfo.getHistoryPrice()).isEqualTo(DEFAULT_HISTORY_PRICE);
        assertThat(testBiddingQuotationInfo.getCurrentTaxPrice()).isEqualTo(DEFAULT_CURRENT_TAX_PRICE);
        assertThat(testBiddingQuotationInfo.getCurrentPriceValidDate()).isEqualTo(DEFAULT_CURRENT_PRICE_VALID_DATE);
        assertThat(testBiddingQuotationInfo.getNetPrice()).isEqualTo(DEFAULT_NET_PRICE);
        assertThat(testBiddingQuotationInfo.getNetUrl()).isEqualTo(DEFAULT_NET_URL);
        assertThat(testBiddingQuotationInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBiddingQuotationInfo.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testBiddingQuotationInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBiddingQuotationInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBiddingQuotationInfo.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testBiddingQuotationInfo.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createBiddingQuotationInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = biddingQuotationInfoRepository.findAll().size();

        // Create the BiddingQuotationInfo with an existing ID
        biddingQuotationInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBiddingQuotationInfoMockMvc
            .perform(
                post("/api/bidding-quotation-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingQuotationInfo in the database
        List<BiddingQuotationInfo> biddingQuotationInfoList = biddingQuotationInfoRepository.findAll();
        assertThat(biddingQuotationInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = biddingQuotationInfoRepository.findAll().size();
        // set the field null
        biddingQuotationInfo.setStatus(null);

        // Create the BiddingQuotationInfo, which fails.

        restBiddingQuotationInfoMockMvc
            .perform(
                post("/api/bidding-quotation-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationInfo))
            )
            .andExpect(status().isBadRequest());

        List<BiddingQuotationInfo> biddingQuotationInfoList = biddingQuotationInfoRepository.findAll();
        assertThat(biddingQuotationInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBiddingQuotationInfos() throws Exception {
        // Initialize the database
        biddingQuotationInfoRepository.saveAndFlush(biddingQuotationInfo);

        // Get all the biddingQuotationInfoList
        restBiddingQuotationInfoMockMvc
            .perform(get("/api/bidding-quotation-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(biddingQuotationInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].latestQuotation").value(hasItem(DEFAULT_LATEST_QUOTATION.doubleValue())))
            .andExpect(jsonPath("$.[*].minQty").value(hasItem(DEFAULT_MIN_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].validDate").value(hasItem(DEFAULT_VALID_DATE.toString())))
            .andExpect(jsonPath("$.[*].deliveryDays").value(hasItem(DEFAULT_DELIVERY_DAYS)))
            .andExpect(jsonPath("$.[*].historyPrice").value(hasItem(DEFAULT_HISTORY_PRICE)))
            .andExpect(jsonPath("$.[*].currentTaxPrice").value(hasItem(DEFAULT_CURRENT_TAX_PRICE)))
            .andExpect(jsonPath("$.[*].currentPriceValidDate").value(hasItem(DEFAULT_CURRENT_PRICE_VALID_DATE.toString())))
            .andExpect(jsonPath("$.[*].netPrice").value(hasItem(DEFAULT_NET_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].netUrl").value(hasItem(DEFAULT_NET_URL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getBiddingQuotationInfo() throws Exception {
        // Initialize the database
        biddingQuotationInfoRepository.saveAndFlush(biddingQuotationInfo);

        // Get the biddingQuotationInfo
        restBiddingQuotationInfoMockMvc
            .perform(get("/api/bidding-quotation-infos/{id}", biddingQuotationInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(biddingQuotationInfo.getId().intValue()))
            .andExpect(jsonPath("$.latestQuotation").value(DEFAULT_LATEST_QUOTATION.doubleValue()))
            .andExpect(jsonPath("$.minQty").value(DEFAULT_MIN_QTY.doubleValue()))
            .andExpect(jsonPath("$.validDate").value(DEFAULT_VALID_DATE.toString()))
            .andExpect(jsonPath("$.deliveryDays").value(DEFAULT_DELIVERY_DAYS))
            .andExpect(jsonPath("$.historyPrice").value(DEFAULT_HISTORY_PRICE))
            .andExpect(jsonPath("$.currentTaxPrice").value(DEFAULT_CURRENT_TAX_PRICE))
            .andExpect(jsonPath("$.currentPriceValidDate").value(DEFAULT_CURRENT_PRICE_VALID_DATE.toString()))
            .andExpect(jsonPath("$.netPrice").value(DEFAULT_NET_PRICE.doubleValue()))
            .andExpect(jsonPath("$.netUrl").value(DEFAULT_NET_URL))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBiddingQuotationInfo() throws Exception {
        // Get the biddingQuotationInfo
        restBiddingQuotationInfoMockMvc.perform(get("/api/bidding-quotation-infos/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBiddingQuotationInfo() throws Exception {
        // Initialize the database
        biddingQuotationInfoRepository.saveAndFlush(biddingQuotationInfo);

        int databaseSizeBeforeUpdate = biddingQuotationInfoRepository.findAll().size();

        // Update the biddingQuotationInfo
        BiddingQuotationInfo updatedBiddingQuotationInfo = biddingQuotationInfoRepository.findById(biddingQuotationInfo.getId()).get();
        // Disconnect from session so that the updates on updatedBiddingQuotationInfo are not directly saved in db
        em.detach(updatedBiddingQuotationInfo);
        updatedBiddingQuotationInfo
            .latestQuotation(UPDATED_LATEST_QUOTATION)
            .minQty(UPDATED_MIN_QTY)
            .validDate(UPDATED_VALID_DATE)
            .deliveryDays(UPDATED_DELIVERY_DAYS)
            .historyPrice(UPDATED_HISTORY_PRICE)
            .currentTaxPrice(UPDATED_CURRENT_TAX_PRICE)
            .currentPriceValidDate(UPDATED_CURRENT_PRICE_VALID_DATE)
            .netPrice(UPDATED_NET_PRICE)
            .netUrl(UPDATED_NET_URL)
            .status(UPDATED_STATUS)
            .remark(UPDATED_REMARK)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restBiddingQuotationInfoMockMvc
            .perform(
                put("/api/bidding-quotation-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBiddingQuotationInfo))
            )
            .andExpect(status().isOk());

        // Validate the BiddingQuotationInfo in the database
        List<BiddingQuotationInfo> biddingQuotationInfoList = biddingQuotationInfoRepository.findAll();
        assertThat(biddingQuotationInfoList).hasSize(databaseSizeBeforeUpdate);
        BiddingQuotationInfo testBiddingQuotationInfo = biddingQuotationInfoList.get(biddingQuotationInfoList.size() - 1);
        assertThat(testBiddingQuotationInfo.getLatestQuotation()).isEqualTo(UPDATED_LATEST_QUOTATION);
        assertThat(testBiddingQuotationInfo.getMinQty()).isEqualTo(UPDATED_MIN_QTY);
        assertThat(testBiddingQuotationInfo.getValidDate()).isEqualTo(UPDATED_VALID_DATE);
        assertThat(testBiddingQuotationInfo.getDeliveryDays()).isEqualTo(UPDATED_DELIVERY_DAYS);
        assertThat(testBiddingQuotationInfo.getHistoryPrice()).isEqualTo(UPDATED_HISTORY_PRICE);
        assertThat(testBiddingQuotationInfo.getCurrentTaxPrice()).isEqualTo(UPDATED_CURRENT_TAX_PRICE);
        assertThat(testBiddingQuotationInfo.getCurrentPriceValidDate()).isEqualTo(UPDATED_CURRENT_PRICE_VALID_DATE);
        assertThat(testBiddingQuotationInfo.getNetPrice()).isEqualTo(UPDATED_NET_PRICE);
        assertThat(testBiddingQuotationInfo.getNetUrl()).isEqualTo(UPDATED_NET_URL);
        assertThat(testBiddingQuotationInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBiddingQuotationInfo.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBiddingQuotationInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBiddingQuotationInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBiddingQuotationInfo.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testBiddingQuotationInfo.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBiddingQuotationInfo() throws Exception {
        int databaseSizeBeforeUpdate = biddingQuotationInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBiddingQuotationInfoMockMvc
            .perform(
                put("/api/bidding-quotation-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(biddingQuotationInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the BiddingQuotationInfo in the database
        List<BiddingQuotationInfo> biddingQuotationInfoList = biddingQuotationInfoRepository.findAll();
        assertThat(biddingQuotationInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBiddingQuotationInfo() throws Exception {
        // Initialize the database
        biddingQuotationInfoRepository.saveAndFlush(biddingQuotationInfo);

        int databaseSizeBeforeDelete = biddingQuotationInfoRepository.findAll().size();

        // Delete the biddingQuotationInfo
        restBiddingQuotationInfoMockMvc
            .perform(delete("/api/bidding-quotation-infos/{id}", biddingQuotationInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BiddingQuotationInfo> biddingQuotationInfoList = biddingQuotationInfoRepository.findAll();
        assertThat(biddingQuotationInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
