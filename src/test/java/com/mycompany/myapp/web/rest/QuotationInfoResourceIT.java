package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.QuotationInfo;
import com.mycompany.myapp.repository.QuotationInfoRepository;

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
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.QuotationStatus;
/**
 * Integration tests for the {@link QuotationInfoResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class QuotationInfoResourceIT {

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
    private QuotationInfoRepository quotationInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuotationInfoMockMvc;

    private QuotationInfo quotationInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuotationInfo createEntity(EntityManager em) {
        QuotationInfo quotationInfo = new QuotationInfo()
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
        return quotationInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuotationInfo createUpdatedEntity(EntityManager em) {
        QuotationInfo quotationInfo = new QuotationInfo()
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
        return quotationInfo;
    }

    @BeforeEach
    public void initTest() {
        quotationInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuotationInfo() throws Exception {
        int databaseSizeBeforeCreate = quotationInfoRepository.findAll().size();
        // Create the QuotationInfo
        restQuotationInfoMockMvc.perform(post("/api/quotation-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(quotationInfo)))
            .andExpect(status().isCreated());

        // Validate the QuotationInfo in the database
        List<QuotationInfo> quotationInfoList = quotationInfoRepository.findAll();
        assertThat(quotationInfoList).hasSize(databaseSizeBeforeCreate + 1);
        QuotationInfo testQuotationInfo = quotationInfoList.get(quotationInfoList.size() - 1);
        assertThat(testQuotationInfo.getLatestQuotation()).isEqualTo(DEFAULT_LATEST_QUOTATION);
        assertThat(testQuotationInfo.getMinQty()).isEqualTo(DEFAULT_MIN_QTY);
        assertThat(testQuotationInfo.getValidDate()).isEqualTo(DEFAULT_VALID_DATE);
        assertThat(testQuotationInfo.getDeliveryDays()).isEqualTo(DEFAULT_DELIVERY_DAYS);
        assertThat(testQuotationInfo.getHistoryPrice()).isEqualTo(DEFAULT_HISTORY_PRICE);
        assertThat(testQuotationInfo.getCurrentTaxPrice()).isEqualTo(DEFAULT_CURRENT_TAX_PRICE);
        assertThat(testQuotationInfo.getCurrentPriceValidDate()).isEqualTo(DEFAULT_CURRENT_PRICE_VALID_DATE);
        assertThat(testQuotationInfo.getNetPrice()).isEqualTo(DEFAULT_NET_PRICE);
        assertThat(testQuotationInfo.getNetUrl()).isEqualTo(DEFAULT_NET_URL);
        assertThat(testQuotationInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testQuotationInfo.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testQuotationInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testQuotationInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testQuotationInfo.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testQuotationInfo.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createQuotationInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quotationInfoRepository.findAll().size();

        // Create the QuotationInfo with an existing ID
        quotationInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuotationInfoMockMvc.perform(post("/api/quotation-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(quotationInfo)))
            .andExpect(status().isBadRequest());

        // Validate the QuotationInfo in the database
        List<QuotationInfo> quotationInfoList = quotationInfoRepository.findAll();
        assertThat(quotationInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotationInfoRepository.findAll().size();
        // set the field null
        quotationInfo.setStatus(null);

        // Create the QuotationInfo, which fails.


        restQuotationInfoMockMvc.perform(post("/api/quotation-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(quotationInfo)))
            .andExpect(status().isBadRequest());

        List<QuotationInfo> quotationInfoList = quotationInfoRepository.findAll();
        assertThat(quotationInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQuotationInfos() throws Exception {
        // Initialize the database
        quotationInfoRepository.saveAndFlush(quotationInfo);

        // Get all the quotationInfoList
        restQuotationInfoMockMvc.perform(get("/api/quotation-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quotationInfo.getId().intValue())))
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
    public void getQuotationInfo() throws Exception {
        // Initialize the database
        quotationInfoRepository.saveAndFlush(quotationInfo);

        // Get the quotationInfo
        restQuotationInfoMockMvc.perform(get("/api/quotation-infos/{id}", quotationInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(quotationInfo.getId().intValue()))
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
    public void getNonExistingQuotationInfo() throws Exception {
        // Get the quotationInfo
        restQuotationInfoMockMvc.perform(get("/api/quotation-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuotationInfo() throws Exception {
        // Initialize the database
        quotationInfoRepository.saveAndFlush(quotationInfo);

        int databaseSizeBeforeUpdate = quotationInfoRepository.findAll().size();

        // Update the quotationInfo
        QuotationInfo updatedQuotationInfo = quotationInfoRepository.findById(quotationInfo.getId()).get();
        // Disconnect from session so that the updates on updatedQuotationInfo are not directly saved in db
        em.detach(updatedQuotationInfo);
        updatedQuotationInfo
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

        restQuotationInfoMockMvc.perform(put("/api/quotation-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedQuotationInfo)))
            .andExpect(status().isOk());

        // Validate the QuotationInfo in the database
        List<QuotationInfo> quotationInfoList = quotationInfoRepository.findAll();
        assertThat(quotationInfoList).hasSize(databaseSizeBeforeUpdate);
        QuotationInfo testQuotationInfo = quotationInfoList.get(quotationInfoList.size() - 1);
        assertThat(testQuotationInfo.getLatestQuotation()).isEqualTo(UPDATED_LATEST_QUOTATION);
        assertThat(testQuotationInfo.getMinQty()).isEqualTo(UPDATED_MIN_QTY);
        assertThat(testQuotationInfo.getValidDate()).isEqualTo(UPDATED_VALID_DATE);
        assertThat(testQuotationInfo.getDeliveryDays()).isEqualTo(UPDATED_DELIVERY_DAYS);
        assertThat(testQuotationInfo.getHistoryPrice()).isEqualTo(UPDATED_HISTORY_PRICE);
        assertThat(testQuotationInfo.getCurrentTaxPrice()).isEqualTo(UPDATED_CURRENT_TAX_PRICE);
        assertThat(testQuotationInfo.getCurrentPriceValidDate()).isEqualTo(UPDATED_CURRENT_PRICE_VALID_DATE);
        assertThat(testQuotationInfo.getNetPrice()).isEqualTo(UPDATED_NET_PRICE);
        assertThat(testQuotationInfo.getNetUrl()).isEqualTo(UPDATED_NET_URL);
        assertThat(testQuotationInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testQuotationInfo.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testQuotationInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testQuotationInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testQuotationInfo.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testQuotationInfo.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingQuotationInfo() throws Exception {
        int databaseSizeBeforeUpdate = quotationInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuotationInfoMockMvc.perform(put("/api/quotation-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(quotationInfo)))
            .andExpect(status().isBadRequest());

        // Validate the QuotationInfo in the database
        List<QuotationInfo> quotationInfoList = quotationInfoRepository.findAll();
        assertThat(quotationInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuotationInfo() throws Exception {
        // Initialize the database
        quotationInfoRepository.saveAndFlush(quotationInfo);

        int databaseSizeBeforeDelete = quotationInfoRepository.findAll().size();

        // Delete the quotationInfo
        restQuotationInfoMockMvc.perform(delete("/api/quotation-infos/{id}", quotationInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QuotationInfo> quotationInfoList = quotationInfoRepository.findAll();
        assertThat(quotationInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
