package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiScoreCalConfigItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiScoreCalConfigItem.class);
        KpiScoreCalConfigItem kpiScoreCalConfigItem1 = new KpiScoreCalConfigItem();
        kpiScoreCalConfigItem1.setId(1L);
        KpiScoreCalConfigItem kpiScoreCalConfigItem2 = new KpiScoreCalConfigItem();
        kpiScoreCalConfigItem2.setId(kpiScoreCalConfigItem1.getId());
        assertThat(kpiScoreCalConfigItem1).isEqualTo(kpiScoreCalConfigItem2);
        kpiScoreCalConfigItem2.setId(2L);
        assertThat(kpiScoreCalConfigItem1).isNotEqualTo(kpiScoreCalConfigItem2);
        kpiScoreCalConfigItem1.setId(null);
        assertThat(kpiScoreCalConfigItem1).isNotEqualTo(kpiScoreCalConfigItem2);
    }
}
