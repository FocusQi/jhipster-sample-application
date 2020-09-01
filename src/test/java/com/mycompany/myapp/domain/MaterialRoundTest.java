package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class MaterialRoundTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MaterialRound.class);
        MaterialRound materialRound1 = new MaterialRound();
        materialRound1.setId(1L);
        MaterialRound materialRound2 = new MaterialRound();
        materialRound2.setId(materialRound1.getId());
        assertThat(materialRound1).isEqualTo(materialRound2);
        materialRound2.setId(2L);
        assertThat(materialRound1).isNotEqualTo(materialRound2);
        materialRound1.setId(null);
        assertThat(materialRound1).isNotEqualTo(materialRound2);
    }
}
