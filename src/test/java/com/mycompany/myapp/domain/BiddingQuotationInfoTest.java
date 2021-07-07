package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

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
