package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiAssTemplateGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiAssTemplateGroup.class);
        KpiAssTemplateGroup kpiAssTemplateGroup1 = new KpiAssTemplateGroup();
        kpiAssTemplateGroup1.setId(1L);
        KpiAssTemplateGroup kpiAssTemplateGroup2 = new KpiAssTemplateGroup();
        kpiAssTemplateGroup2.setId(kpiAssTemplateGroup1.getId());
        assertThat(kpiAssTemplateGroup1).isEqualTo(kpiAssTemplateGroup2);
        kpiAssTemplateGroup2.setId(2L);
        assertThat(kpiAssTemplateGroup1).isNotEqualTo(kpiAssTemplateGroup2);
        kpiAssTemplateGroup1.setId(null);
        assertThat(kpiAssTemplateGroup1).isNotEqualTo(kpiAssTemplateGroup2);
    }
}
