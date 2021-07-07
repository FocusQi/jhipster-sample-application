package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiTemplateGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiTemplateGroup.class);
        KpiTemplateGroup kpiTemplateGroup1 = new KpiTemplateGroup();
        kpiTemplateGroup1.setId(1L);
        KpiTemplateGroup kpiTemplateGroup2 = new KpiTemplateGroup();
        kpiTemplateGroup2.setId(kpiTemplateGroup1.getId());
        assertThat(kpiTemplateGroup1).isEqualTo(kpiTemplateGroup2);
        kpiTemplateGroup2.setId(2L);
        assertThat(kpiTemplateGroup1).isNotEqualTo(kpiTemplateGroup2);
        kpiTemplateGroup1.setId(null);
        assertThat(kpiTemplateGroup1).isNotEqualTo(kpiTemplateGroup2);
    }
}
