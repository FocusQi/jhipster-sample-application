package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

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
