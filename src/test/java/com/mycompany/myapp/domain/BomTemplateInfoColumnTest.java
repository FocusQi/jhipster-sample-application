package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class BomTemplateInfoColumnTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BomTemplateInfoColumn.class);
        BomTemplateInfoColumn bomTemplateInfoColumn1 = new BomTemplateInfoColumn();
        bomTemplateInfoColumn1.setId(1L);
        BomTemplateInfoColumn bomTemplateInfoColumn2 = new BomTemplateInfoColumn();
        bomTemplateInfoColumn2.setId(bomTemplateInfoColumn1.getId());
        assertThat(bomTemplateInfoColumn1).isEqualTo(bomTemplateInfoColumn2);
        bomTemplateInfoColumn2.setId(2L);
        assertThat(bomTemplateInfoColumn1).isNotEqualTo(bomTemplateInfoColumn2);
        bomTemplateInfoColumn1.setId(null);
        assertThat(bomTemplateInfoColumn1).isNotEqualTo(bomTemplateInfoColumn2);
    }
}
