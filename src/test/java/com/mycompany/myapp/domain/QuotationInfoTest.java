package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QuotationInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuotationInfo.class);
        QuotationInfo quotationInfo1 = new QuotationInfo();
        quotationInfo1.setId(1L);
        QuotationInfo quotationInfo2 = new QuotationInfo();
        quotationInfo2.setId(quotationInfo1.getId());
        assertThat(quotationInfo1).isEqualTo(quotationInfo2);
        quotationInfo2.setId(2L);
        assertThat(quotationInfo1).isNotEqualTo(quotationInfo2);
        quotationInfo1.setId(null);
        assertThat(quotationInfo1).isNotEqualTo(quotationInfo2);
    }
}
