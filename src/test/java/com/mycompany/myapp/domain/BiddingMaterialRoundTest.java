package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

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
