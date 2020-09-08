package com.mega.project.srm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mega.project.srm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class KpiCalItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KpiCalItem.class);
        KpiCalItem kpiCalItem1 = new KpiCalItem();
        kpiCalItem1.setId(1L);
        KpiCalItem kpiCalItem2 = new KpiCalItem();
        kpiCalItem2.setId(kpiCalItem1.getId());
        assertThat(kpiCalItem1).isEqualTo(kpiCalItem2);
        kpiCalItem2.setId(2L);
        assertThat(kpiCalItem1).isNotEqualTo(kpiCalItem2);
        kpiCalItem1.setId(null);
        assertThat(kpiCalItem1).isNotEqualTo(kpiCalItem2);
    }
}
