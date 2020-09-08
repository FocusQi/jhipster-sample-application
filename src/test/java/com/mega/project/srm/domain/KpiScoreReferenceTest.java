package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiScoreReferenceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiScoreReference.class);
        KpiScoreReference kpiScoreReference1 = new KpiScoreReference();
        kpiScoreReference1.setId(1L);
        KpiScoreReference kpiScoreReference2 = new KpiScoreReference();
        kpiScoreReference2.setId(kpiScoreReference1.getId());
        assertThat(kpiScoreReference1).isEqualTo(kpiScoreReference2);
        kpiScoreReference2.setId(2L);
        assertThat(kpiScoreReference1).isNotEqualTo(kpiScoreReference2);
        kpiScoreReference1.setId(null);
        assertThat(kpiScoreReference1).isNotEqualTo(kpiScoreReference2);
    }
}
