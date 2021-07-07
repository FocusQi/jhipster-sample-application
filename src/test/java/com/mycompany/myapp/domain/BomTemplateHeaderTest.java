package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class BomTemplateHeaderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BomTemplateHeader.class);
        BomTemplateHeader bomTemplateHeader1 = new BomTemplateHeader();
        bomTemplateHeader1.setId(1L);
        BomTemplateHeader bomTemplateHeader2 = new BomTemplateHeader();
        bomTemplateHeader2.setId(bomTemplateHeader1.getId());
        assertThat(bomTemplateHeader1).isEqualTo(bomTemplateHeader2);
        bomTemplateHeader2.setId(2L);
        assertThat(bomTemplateHeader1).isNotEqualTo(bomTemplateHeader2);
        bomTemplateHeader1.setId(null);
        assertThat(bomTemplateHeader1).isNotEqualTo(bomTemplateHeader2);
    }
}
