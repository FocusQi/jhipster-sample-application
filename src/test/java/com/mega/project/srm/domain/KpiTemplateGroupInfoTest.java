package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiTemplateGroupInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiTemplateGroupInfo.class);
        KpiTemplateGroupInfo kpiTemplateGroupInfo1 = new KpiTemplateGroupInfo();
        kpiTemplateGroupInfo1.setId(1L);
        KpiTemplateGroupInfo kpiTemplateGroupInfo2 = new KpiTemplateGroupInfo();
        kpiTemplateGroupInfo2.setId(kpiTemplateGroupInfo1.getId());
        assertThat(kpiTemplateGroupInfo1).isEqualTo(kpiTemplateGroupInfo2);
        kpiTemplateGroupInfo2.setId(2L);
        assertThat(kpiTemplateGroupInfo1).isNotEqualTo(kpiTemplateGroupInfo2);
        kpiTemplateGroupInfo1.setId(null);
        assertThat(kpiTemplateGroupInfo1).isNotEqualTo(kpiTemplateGroupInfo2);
    }
}
