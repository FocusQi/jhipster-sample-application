package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiAssGroupInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiAssGroupInfo.class);
        KpiAssGroupInfo kpiAssGroupInfo1 = new KpiAssGroupInfo();
        kpiAssGroupInfo1.setId(1L);
        KpiAssGroupInfo kpiAssGroupInfo2 = new KpiAssGroupInfo();
        kpiAssGroupInfo2.setId(kpiAssGroupInfo1.getId());
        assertThat(kpiAssGroupInfo1).isEqualTo(kpiAssGroupInfo2);
        kpiAssGroupInfo2.setId(2L);
        assertThat(kpiAssGroupInfo1).isNotEqualTo(kpiAssGroupInfo2);
        kpiAssGroupInfo1.setId(null);
        assertThat(kpiAssGroupInfo1).isNotEqualTo(kpiAssGroupInfo2);
    }
}
