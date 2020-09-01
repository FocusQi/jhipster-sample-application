package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BiddingOpenerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BiddingOpener.class);
        BiddingOpener biddingOpener1 = new BiddingOpener();
        biddingOpener1.setId(1L);
        BiddingOpener biddingOpener2 = new BiddingOpener();
        biddingOpener2.setId(biddingOpener1.getId());
        assertThat(biddingOpener1).isEqualTo(biddingOpener2);
        biddingOpener2.setId(2L);
        assertThat(biddingOpener1).isNotEqualTo(biddingOpener2);
        biddingOpener1.setId(null);
        assertThat(biddingOpener1).isNotEqualTo(biddingOpener2);
    }
}
