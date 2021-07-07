package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BiddingMaterialRoundTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BiddingMaterialRound.class);
        BiddingMaterialRound biddingMaterialRound1 = new BiddingMaterialRound();
        biddingMaterialRound1.setId(1L);
        BiddingMaterialRound biddingMaterialRound2 = new BiddingMaterialRound();
        biddingMaterialRound2.setId(biddingMaterialRound1.getId());
        assertThat(biddingMaterialRound1).isEqualTo(biddingMaterialRound2);
        biddingMaterialRound2.setId(2L);
        assertThat(biddingMaterialRound1).isNotEqualTo(biddingMaterialRound2);
        biddingMaterialRound1.setId(null);
        assertThat(biddingMaterialRound1).isNotEqualTo(biddingMaterialRound2);
    }
}
