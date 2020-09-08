package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiScoreInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiScoreInfo.class);
        KpiScoreInfo kpiScoreInfo1 = new KpiScoreInfo();
        kpiScoreInfo1.setId(1L);
        KpiScoreInfo kpiScoreInfo2 = new KpiScoreInfo();
        kpiScoreInfo2.setId(kpiScoreInfo1.getId());
        assertThat(kpiScoreInfo1).isEqualTo(kpiScoreInfo2);
        kpiScoreInfo2.setId(2L);
        assertThat(kpiScoreInfo1).isNotEqualTo(kpiScoreInfo2);
        kpiScoreInfo1.setId(null);
        assertThat(kpiScoreInfo1).isNotEqualTo(kpiScoreInfo2);
    }
}
