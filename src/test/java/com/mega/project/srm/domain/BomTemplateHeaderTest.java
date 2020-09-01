package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

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
