package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiScoreGradeConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiScoreGradeConfig.class);
        KpiScoreGradeConfig kpiScoreGradeConfig1 = new KpiScoreGradeConfig();
        kpiScoreGradeConfig1.setId(1L);
        KpiScoreGradeConfig kpiScoreGradeConfig2 = new KpiScoreGradeConfig();
        kpiScoreGradeConfig2.setId(kpiScoreGradeConfig1.getId());
        assertThat(kpiScoreGradeConfig1).isEqualTo(kpiScoreGradeConfig2);
        kpiScoreGradeConfig2.setId(2L);
        assertThat(kpiScoreGradeConfig1).isNotEqualTo(kpiScoreGradeConfig2);
        kpiScoreGradeConfig1.setId(null);
        assertThat(kpiScoreGradeConfig1).isNotEqualTo(kpiScoreGradeConfig2);
    }
}
