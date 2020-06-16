package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SrmMsgListTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SrmMsgList.class);
        SrmMsgList srmMsgList1 = new SrmMsgList();
        srmMsgList1.setId(1L);
        SrmMsgList srmMsgList2 = new SrmMsgList();
        srmMsgList2.setId(srmMsgList1.getId());
        assertThat(srmMsgList1).isEqualTo(srmMsgList2);
        srmMsgList2.setId(2L);
        assertThat(srmMsgList1).isNotEqualTo(srmMsgList2);
        srmMsgList1.setId(null);
        assertThat(srmMsgList1).isNotEqualTo(srmMsgList2);
    }
}
