package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiImproveReportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiImproveReport.class);
        KpiImproveReport kpiImproveReport1 = new KpiImproveReport();
        kpiImproveReport1.setId(1L);
        KpiImproveReport kpiImproveReport2 = new KpiImproveReport();
        kpiImproveReport2.setId(kpiImproveReport1.getId());
        assertThat(kpiImproveReport1).isEqualTo(kpiImproveReport2);
        kpiImproveReport2.setId(2L);
        assertThat(kpiImproveReport1).isNotEqualTo(kpiImproveReport2);
        kpiImproveReport1.setId(null);
        assertThat(kpiImproveReport1).isNotEqualTo(kpiImproveReport2);
    }
}
