package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

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
