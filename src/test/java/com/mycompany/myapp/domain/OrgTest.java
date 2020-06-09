package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class OrgTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Org.class);
        Org org1 = new Org();
        org1.setId(1L);
        Org org2 = new Org();
        org2.setId(org1.getId());
        assertThat(org1).isEqualTo(org2);
        org2.setId(2L);
        assertThat(org1).isNotEqualTo(org2);
        org1.setId(null);
        assertThat(org1).isNotEqualTo(org2);
    }
}
