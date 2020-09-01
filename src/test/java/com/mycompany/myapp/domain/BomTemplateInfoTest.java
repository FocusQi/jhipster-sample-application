package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class BomTemplateInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BomTemplateInfo.class);
        BomTemplateInfo bomTemplateInfo1 = new BomTemplateInfo();
        bomTemplateInfo1.setId(1L);
        BomTemplateInfo bomTemplateInfo2 = new BomTemplateInfo();
        bomTemplateInfo2.setId(bomTemplateInfo1.getId());
        assertThat(bomTemplateInfo1).isEqualTo(bomTemplateInfo2);
        bomTemplateInfo2.setId(2L);
        assertThat(bomTemplateInfo1).isNotEqualTo(bomTemplateInfo2);
        bomTemplateInfo1.setId(null);
        assertThat(bomTemplateInfo1).isNotEqualTo(bomTemplateInfo2);
    }
}
