package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiImproveReportInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiImproveReportInfo.class);
        KpiImproveReportInfo kpiImproveReportInfo1 = new KpiImproveReportInfo();
        kpiImproveReportInfo1.setId(1L);
        KpiImproveReportInfo kpiImproveReportInfo2 = new KpiImproveReportInfo();
        kpiImproveReportInfo2.setId(kpiImproveReportInfo1.getId());
        assertThat(kpiImproveReportInfo1).isEqualTo(kpiImproveReportInfo2);
        kpiImproveReportInfo2.setId(2L);
        assertThat(kpiImproveReportInfo1).isNotEqualTo(kpiImproveReportInfo2);
        kpiImproveReportInfo1.setId(null);
        assertThat(kpiImproveReportInfo1).isNotEqualTo(kpiImproveReportInfo2);
    }
}
