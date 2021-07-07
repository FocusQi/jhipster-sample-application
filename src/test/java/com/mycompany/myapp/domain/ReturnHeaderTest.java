package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ReturnHeaderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReturnHeader.class);
        ReturnHeader returnHeader1 = new ReturnHeader();
        returnHeader1.setId(1L);
        ReturnHeader returnHeader2 = new ReturnHeader();
        returnHeader2.setId(returnHeader1.getId());
        assertThat(returnHeader1).isEqualTo(returnHeader2);
        returnHeader2.setId(2L);
        assertThat(returnHeader1).isNotEqualTo(returnHeader2);
        returnHeader1.setId(null);
        assertThat(returnHeader1).isNotEqualTo(returnHeader2);
    }
}
