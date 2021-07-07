package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class BiddingRoundInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BiddingRoundInfo.class);
        BiddingRoundInfo biddingRoundInfo1 = new BiddingRoundInfo();
        biddingRoundInfo1.setId(1L);
        BiddingRoundInfo biddingRoundInfo2 = new BiddingRoundInfo();
        biddingRoundInfo2.setId(biddingRoundInfo1.getId());
        assertThat(biddingRoundInfo1).isEqualTo(biddingRoundInfo2);
        biddingRoundInfo2.setId(2L);
        assertThat(biddingRoundInfo1).isNotEqualTo(biddingRoundInfo2);
        biddingRoundInfo1.setId(null);
        assertThat(biddingRoundInfo1).isNotEqualTo(biddingRoundInfo2);
    }
}
