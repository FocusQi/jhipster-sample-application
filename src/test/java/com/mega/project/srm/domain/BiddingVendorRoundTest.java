package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BiddingVendorRoundTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BiddingVendorRound.class);
        BiddingVendorRound biddingVendorRound1 = new BiddingVendorRound();
        biddingVendorRound1.setId(1L);
        BiddingVendorRound biddingVendorRound2 = new BiddingVendorRound();
        biddingVendorRound2.setId(biddingVendorRound1.getId());
        assertThat(biddingVendorRound1).isEqualTo(biddingVendorRound2);
        biddingVendorRound2.setId(2L);
        assertThat(biddingVendorRound1).isNotEqualTo(biddingVendorRound2);
        biddingVendorRound1.setId(null);
        assertThat(biddingVendorRound1).isNotEqualTo(biddingVendorRound2);
    }
}
