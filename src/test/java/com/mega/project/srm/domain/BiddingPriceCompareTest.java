package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BiddingPriceCompareTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BiddingPriceCompare.class);
        BiddingPriceCompare biddingPriceCompare1 = new BiddingPriceCompare();
        biddingPriceCompare1.setId(1L);
        BiddingPriceCompare biddingPriceCompare2 = new BiddingPriceCompare();
        biddingPriceCompare2.setId(biddingPriceCompare1.getId());
        assertThat(biddingPriceCompare1).isEqualTo(biddingPriceCompare2);
        biddingPriceCompare2.setId(2L);
        assertThat(biddingPriceCompare1).isNotEqualTo(biddingPriceCompare2);
        biddingPriceCompare1.setId(null);
        assertThat(biddingPriceCompare1).isNotEqualTo(biddingPriceCompare2);
    }
}
