package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiAssTemplateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiAssTemplate.class);
        KpiAssTemplate kpiAssTemplate1 = new KpiAssTemplate();
        kpiAssTemplate1.setId(1L);
        KpiAssTemplate kpiAssTemplate2 = new KpiAssTemplate();
        kpiAssTemplate2.setId(kpiAssTemplate1.getId());
        assertThat(kpiAssTemplate1).isEqualTo(kpiAssTemplate2);
        kpiAssTemplate2.setId(2L);
        assertThat(kpiAssTemplate1).isNotEqualTo(kpiAssTemplate2);
        kpiAssTemplate1.setId(null);
        assertThat(kpiAssTemplate1).isNotEqualTo(kpiAssTemplate2);
    }
}
