package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PriceCompareTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceCompare.class);
        PriceCompare priceCompare1 = new PriceCompare();
        priceCompare1.setId(1L);
        PriceCompare priceCompare2 = new PriceCompare();
        priceCompare2.setId(priceCompare1.getId());
        assertThat(priceCompare1).isEqualTo(priceCompare2);
        priceCompare2.setId(2L);
        assertThat(priceCompare1).isNotEqualTo(priceCompare2);
        priceCompare1.setId(null);
        assertThat(priceCompare1).isNotEqualTo(priceCompare2);
    }
}
