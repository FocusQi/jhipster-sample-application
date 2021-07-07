package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiAssTemplateInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiAssTemplateInfo.class);
        KpiAssTemplateInfo kpiAssTemplateInfo1 = new KpiAssTemplateInfo();
        kpiAssTemplateInfo1.setId(1L);
        KpiAssTemplateInfo kpiAssTemplateInfo2 = new KpiAssTemplateInfo();
        kpiAssTemplateInfo2.setId(kpiAssTemplateInfo1.getId());
        assertThat(kpiAssTemplateInfo1).isEqualTo(kpiAssTemplateInfo2);
        kpiAssTemplateInfo2.setId(2L);
        assertThat(kpiAssTemplateInfo1).isNotEqualTo(kpiAssTemplateInfo2);
        kpiAssTemplateInfo1.setId(null);
        assertThat(kpiAssTemplateInfo1).isNotEqualTo(kpiAssTemplateInfo2);
    }
}
