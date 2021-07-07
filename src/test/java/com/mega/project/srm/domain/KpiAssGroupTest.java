package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiAssGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiAssGroup.class);
        KpiAssGroup kpiAssGroup1 = new KpiAssGroup();
        kpiAssGroup1.setId(1L);
        KpiAssGroup kpiAssGroup2 = new KpiAssGroup();
        kpiAssGroup2.setId(kpiAssGroup1.getId());
        assertThat(kpiAssGroup1).isEqualTo(kpiAssGroup2);
        kpiAssGroup2.setId(2L);
        assertThat(kpiAssGroup1).isNotEqualTo(kpiAssGroup2);
        kpiAssGroup1.setId(null);
        assertThat(kpiAssGroup1).isNotEqualTo(kpiAssGroup2);
    }
}
