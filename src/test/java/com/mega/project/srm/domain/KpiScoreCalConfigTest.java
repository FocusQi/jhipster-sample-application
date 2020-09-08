package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiScoreCalConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiScoreCalConfig.class);
        KpiScoreCalConfig kpiScoreCalConfig1 = new KpiScoreCalConfig();
        kpiScoreCalConfig1.setId(1L);
        KpiScoreCalConfig kpiScoreCalConfig2 = new KpiScoreCalConfig();
        kpiScoreCalConfig2.setId(kpiScoreCalConfig1.getId());
        assertThat(kpiScoreCalConfig1).isEqualTo(kpiScoreCalConfig2);
        kpiScoreCalConfig2.setId(2L);
        assertThat(kpiScoreCalConfig1).isNotEqualTo(kpiScoreCalConfig2);
        kpiScoreCalConfig1.setId(null);
        assertThat(kpiScoreCalConfig1).isNotEqualTo(kpiScoreCalConfig2);
    }
}
