package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class VendorRoundTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VendorRound.class);
        VendorRound vendorRound1 = new VendorRound();
        vendorRound1.setId(1L);
        VendorRound vendorRound2 = new VendorRound();
        vendorRound2.setId(vendorRound1.getId());
        assertThat(vendorRound1).isEqualTo(vendorRound2);
        vendorRound2.setId(2L);
        assertThat(vendorRound1).isNotEqualTo(vendorRound2);
        vendorRound1.setId(null);
        assertThat(vendorRound1).isNotEqualTo(vendorRound2);
    }
}
