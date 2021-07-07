package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BiddingQuotationInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BiddingQuotationInfo.class);
        BiddingQuotationInfo biddingQuotationInfo1 = new BiddingQuotationInfo();
        biddingQuotationInfo1.setId(1L);
        BiddingQuotationInfo biddingQuotationInfo2 = new BiddingQuotationInfo();
        biddingQuotationInfo2.setId(biddingQuotationInfo1.getId());
        assertThat(biddingQuotationInfo1).isEqualTo(biddingQuotationInfo2);
        biddingQuotationInfo2.setId(2L);
        assertThat(biddingQuotationInfo1).isNotEqualTo(biddingQuotationInfo2);
        biddingQuotationInfo1.setId(null);
        assertThat(biddingQuotationInfo1).isNotEqualTo(biddingQuotationInfo2);
    }
}
