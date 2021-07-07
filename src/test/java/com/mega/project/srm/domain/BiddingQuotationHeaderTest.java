package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BiddingQuotationHeaderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BiddingQuotationHeader.class);
        BiddingQuotationHeader biddingQuotationHeader1 = new BiddingQuotationHeader();
        biddingQuotationHeader1.setId(1L);
        BiddingQuotationHeader biddingQuotationHeader2 = new BiddingQuotationHeader();
        biddingQuotationHeader2.setId(biddingQuotationHeader1.getId());
        assertThat(biddingQuotationHeader1).isEqualTo(biddingQuotationHeader2);
        biddingQuotationHeader2.setId(2L);
        assertThat(biddingQuotationHeader1).isNotEqualTo(biddingQuotationHeader2);
        biddingQuotationHeader1.setId(null);
        assertThat(biddingQuotationHeader1).isNotEqualTo(biddingQuotationHeader2);
    }
}
