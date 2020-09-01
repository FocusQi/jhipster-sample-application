package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PoHeaderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PoHeader.class);
        PoHeader poHeader1 = new PoHeader();
        poHeader1.setId(1L);
        PoHeader poHeader2 = new PoHeader();
        poHeader2.setId(poHeader1.getId());
        assertThat(poHeader1).isEqualTo(poHeader2);
        poHeader2.setId(2L);
        assertThat(poHeader1).isNotEqualTo(poHeader2);
        poHeader1.setId(null);
        assertThat(poHeader1).isNotEqualTo(poHeader2);
    }
}
