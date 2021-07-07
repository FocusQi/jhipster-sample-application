package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class OpenerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Opener.class);
        Opener opener1 = new Opener();
        opener1.setId(1L);
        Opener opener2 = new Opener();
        opener2.setId(opener1.getId());
        assertThat(opener1).isEqualTo(opener2);
        opener2.setId(2L);
        assertThat(opener1).isNotEqualTo(opener2);
        opener1.setId(null);
        assertThat(opener1).isNotEqualTo(opener2);
    }
}
