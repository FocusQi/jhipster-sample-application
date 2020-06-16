package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.SrmMsgList;
import com.mycompany.myapp.repository.SrmMsgListRepository;

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
 * Integration tests for the {@link SrmMsgListResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SrmMsgListResourceIT {

    private static final Integer DEFAULT_MSG_ID = 1;
    private static final Integer UPDATED_MSG_ID = 2;

    private static final String DEFAULT_MSG_TOPIC = "AAAAAAAAAA";
    private static final String UPDATED_MSG_TOPIC = "BBBBBBBBBB";

    private static final String DEFAULT_MSG_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_MSG_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_MSG_MAIL_TO = "AAAAAAAAAA";
    private static final String UPDATED_MSG_MAIL_TO = "BBBBBBBBBB";

    private static final String DEFAULT_MSG_SMS_TO = "AAAAAAAAAA";
    private static final String UPDATED_MSG_SMS_TO = "BBBBBBBBBB";

    private static final Instant DEFAULT_MSG_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MSG_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MSG_SEND_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MSG_SEND_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MSG_STATUS = "AAA";
    private static final String UPDATED_MSG_STATUS = "BBB";

    private static final String DEFAULT_MSF_MEMO = "AAAAAAAAAA";
    private static final String UPDATED_MSF_MEMO = "BBBBBBBBBB";

    private static final String DEFAULT_MSG_TO_BACK_UP = "AA";
    private static final String UPDATED_MSG_TO_BACK_UP = "BB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private SrmMsgListRepository srmMsgListRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSrmMsgListMockMvc;

    private SrmMsgList srmMsgList;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SrmMsgList createEntity(EntityManager em) {
        SrmMsgList srmMsgList = new SrmMsgList()
            .msgId(DEFAULT_MSG_ID)
            .msgTopic(DEFAULT_MSG_TOPIC)
            .msgContent(DEFAULT_MSG_CONTENT)
            .msgMailTo(DEFAULT_MSG_MAIL_TO)
            .msgSmsTo(DEFAULT_MSG_SMS_TO)
            .msgCreateTime(DEFAULT_MSG_CREATE_TIME)
            .msgSendTime(DEFAULT_MSG_SEND_TIME)
            .msgStatus(DEFAULT_MSG_STATUS)
            .msfMemo(DEFAULT_MSF_MEMO)
            .msgToBackUp(DEFAULT_MSG_TO_BACK_UP)
            .remark(DEFAULT_REMARK)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return srmMsgList;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SrmMsgList createUpdatedEntity(EntityManager em) {
        SrmMsgList srmMsgList = new SrmMsgList()
            .msgId(UPDATED_MSG_ID)
            .msgTopic(UPDATED_MSG_TOPIC)
            .msgContent(UPDATED_MSG_CONTENT)
            .msgMailTo(UPDATED_MSG_MAIL_TO)
            .msgSmsTo(UPDATED_MSG_SMS_TO)
            .msgCreateTime(UPDATED_MSG_CREATE_TIME)
            .msgSendTime(UPDATED_MSG_SEND_TIME)
            .msgStatus(UPDATED_MSG_STATUS)
            .msfMemo(UPDATED_MSF_MEMO)
            .msgToBackUp(UPDATED_MSG_TO_BACK_UP)
            .remark(UPDATED_REMARK)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return srmMsgList;
    }

    @BeforeEach
    public void initTest() {
        srmMsgList = createEntity(em);
    }

    @Test
    @Transactional
    public void createSrmMsgList() throws Exception {
        int databaseSizeBeforeCreate = srmMsgListRepository.findAll().size();
        // Create the SrmMsgList
        restSrmMsgListMockMvc.perform(post("/api/srm-msg-lists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(srmMsgList)))
            .andExpect(status().isCreated());

        // Validate the SrmMsgList in the database
        List<SrmMsgList> srmMsgListList = srmMsgListRepository.findAll();
        assertThat(srmMsgListList).hasSize(databaseSizeBeforeCreate + 1);
        SrmMsgList testSrmMsgList = srmMsgListList.get(srmMsgListList.size() - 1);
        assertThat(testSrmMsgList.getMsgId()).isEqualTo(DEFAULT_MSG_ID);
        assertThat(testSrmMsgList.getMsgTopic()).isEqualTo(DEFAULT_MSG_TOPIC);
        assertThat(testSrmMsgList.getMsgContent()).isEqualTo(DEFAULT_MSG_CONTENT);
        assertThat(testSrmMsgList.getMsgMailTo()).isEqualTo(DEFAULT_MSG_MAIL_TO);
        assertThat(testSrmMsgList.getMsgSmsTo()).isEqualTo(DEFAULT_MSG_SMS_TO);
        assertThat(testSrmMsgList.getMsgCreateTime()).isEqualTo(DEFAULT_MSG_CREATE_TIME);
        assertThat(testSrmMsgList.getMsgSendTime()).isEqualTo(DEFAULT_MSG_SEND_TIME);
        assertThat(testSrmMsgList.getMsgStatus()).isEqualTo(DEFAULT_MSG_STATUS);
        assertThat(testSrmMsgList.getMsfMemo()).isEqualTo(DEFAULT_MSF_MEMO);
        assertThat(testSrmMsgList.getMsgToBackUp()).isEqualTo(DEFAULT_MSG_TO_BACK_UP);
        assertThat(testSrmMsgList.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testSrmMsgList.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testSrmMsgList.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSrmMsgList.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSrmMsgList.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createSrmMsgListWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = srmMsgListRepository.findAll().size();

        // Create the SrmMsgList with an existing ID
        srmMsgList.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSrmMsgListMockMvc.perform(post("/api/srm-msg-lists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(srmMsgList)))
            .andExpect(status().isBadRequest());

        // Validate the SrmMsgList in the database
        List<SrmMsgList> srmMsgListList = srmMsgListRepository.findAll();
        assertThat(srmMsgListList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSrmMsgLists() throws Exception {
        // Initialize the database
        srmMsgListRepository.saveAndFlush(srmMsgList);

        // Get all the srmMsgListList
        restSrmMsgListMockMvc.perform(get("/api/srm-msg-lists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(srmMsgList.getId().intValue())))
            .andExpect(jsonPath("$.[*].msgId").value(hasItem(DEFAULT_MSG_ID)))
            .andExpect(jsonPath("$.[*].msgTopic").value(hasItem(DEFAULT_MSG_TOPIC)))
            .andExpect(jsonPath("$.[*].msgContent").value(hasItem(DEFAULT_MSG_CONTENT)))
            .andExpect(jsonPath("$.[*].msgMailTo").value(hasItem(DEFAULT_MSG_MAIL_TO)))
            .andExpect(jsonPath("$.[*].msgSmsTo").value(hasItem(DEFAULT_MSG_SMS_TO)))
            .andExpect(jsonPath("$.[*].msgCreateTime").value(hasItem(DEFAULT_MSG_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].msgSendTime").value(hasItem(DEFAULT_MSG_SEND_TIME.toString())))
            .andExpect(jsonPath("$.[*].msgStatus").value(hasItem(DEFAULT_MSG_STATUS)))
            .andExpect(jsonPath("$.[*].msfMemo").value(hasItem(DEFAULT_MSF_MEMO)))
            .andExpect(jsonPath("$.[*].msgToBackUp").value(hasItem(DEFAULT_MSG_TO_BACK_UP)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getSrmMsgList() throws Exception {
        // Initialize the database
        srmMsgListRepository.saveAndFlush(srmMsgList);

        // Get the srmMsgList
        restSrmMsgListMockMvc.perform(get("/api/srm-msg-lists/{id}", srmMsgList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(srmMsgList.getId().intValue()))
            .andExpect(jsonPath("$.msgId").value(DEFAULT_MSG_ID))
            .andExpect(jsonPath("$.msgTopic").value(DEFAULT_MSG_TOPIC))
            .andExpect(jsonPath("$.msgContent").value(DEFAULT_MSG_CONTENT))
            .andExpect(jsonPath("$.msgMailTo").value(DEFAULT_MSG_MAIL_TO))
            .andExpect(jsonPath("$.msgSmsTo").value(DEFAULT_MSG_SMS_TO))
            .andExpect(jsonPath("$.msgCreateTime").value(DEFAULT_MSG_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.msgSendTime").value(DEFAULT_MSG_SEND_TIME.toString()))
            .andExpect(jsonPath("$.msgStatus").value(DEFAULT_MSG_STATUS))
            .andExpect(jsonPath("$.msfMemo").value(DEFAULT_MSF_MEMO))
            .andExpect(jsonPath("$.msgToBackUp").value(DEFAULT_MSG_TO_BACK_UP))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSrmMsgList() throws Exception {
        // Get the srmMsgList
        restSrmMsgListMockMvc.perform(get("/api/srm-msg-lists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSrmMsgList() throws Exception {
        // Initialize the database
        srmMsgListRepository.saveAndFlush(srmMsgList);

        int databaseSizeBeforeUpdate = srmMsgListRepository.findAll().size();

        // Update the srmMsgList
        SrmMsgList updatedSrmMsgList = srmMsgListRepository.findById(srmMsgList.getId()).get();
        // Disconnect from session so that the updates on updatedSrmMsgList are not directly saved in db
        em.detach(updatedSrmMsgList);
        updatedSrmMsgList
            .msgId(UPDATED_MSG_ID)
            .msgTopic(UPDATED_MSG_TOPIC)
            .msgContent(UPDATED_MSG_CONTENT)
            .msgMailTo(UPDATED_MSG_MAIL_TO)
            .msgSmsTo(UPDATED_MSG_SMS_TO)
            .msgCreateTime(UPDATED_MSG_CREATE_TIME)
            .msgSendTime(UPDATED_MSG_SEND_TIME)
            .msgStatus(UPDATED_MSG_STATUS)
            .msfMemo(UPDATED_MSF_MEMO)
            .msgToBackUp(UPDATED_MSG_TO_BACK_UP)
            .remark(UPDATED_REMARK)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restSrmMsgListMockMvc.perform(put("/api/srm-msg-lists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSrmMsgList)))
            .andExpect(status().isOk());

        // Validate the SrmMsgList in the database
        List<SrmMsgList> srmMsgListList = srmMsgListRepository.findAll();
        assertThat(srmMsgListList).hasSize(databaseSizeBeforeUpdate);
        SrmMsgList testSrmMsgList = srmMsgListList.get(srmMsgListList.size() - 1);
        assertThat(testSrmMsgList.getMsgId()).isEqualTo(UPDATED_MSG_ID);
        assertThat(testSrmMsgList.getMsgTopic()).isEqualTo(UPDATED_MSG_TOPIC);
        assertThat(testSrmMsgList.getMsgContent()).isEqualTo(UPDATED_MSG_CONTENT);
        assertThat(testSrmMsgList.getMsgMailTo()).isEqualTo(UPDATED_MSG_MAIL_TO);
        assertThat(testSrmMsgList.getMsgSmsTo()).isEqualTo(UPDATED_MSG_SMS_TO);
        assertThat(testSrmMsgList.getMsgCreateTime()).isEqualTo(UPDATED_MSG_CREATE_TIME);
        assertThat(testSrmMsgList.getMsgSendTime()).isEqualTo(UPDATED_MSG_SEND_TIME);
        assertThat(testSrmMsgList.getMsgStatus()).isEqualTo(UPDATED_MSG_STATUS);
        assertThat(testSrmMsgList.getMsfMemo()).isEqualTo(UPDATED_MSF_MEMO);
        assertThat(testSrmMsgList.getMsgToBackUp()).isEqualTo(UPDATED_MSG_TO_BACK_UP);
        assertThat(testSrmMsgList.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSrmMsgList.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testSrmMsgList.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSrmMsgList.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSrmMsgList.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSrmMsgList() throws Exception {
        int databaseSizeBeforeUpdate = srmMsgListRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSrmMsgListMockMvc.perform(put("/api/srm-msg-lists")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(srmMsgList)))
            .andExpect(status().isBadRequest());

        // Validate the SrmMsgList in the database
        List<SrmMsgList> srmMsgListList = srmMsgListRepository.findAll();
        assertThat(srmMsgListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSrmMsgList() throws Exception {
        // Initialize the database
        srmMsgListRepository.saveAndFlush(srmMsgList);

        int databaseSizeBeforeDelete = srmMsgListRepository.findAll().size();

        // Delete the srmMsgList
        restSrmMsgListMockMvc.perform(delete("/api/srm-msg-lists/{id}", srmMsgList.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SrmMsgList> srmMsgListList = srmMsgListRepository.findAll();
        assertThat(srmMsgListList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
