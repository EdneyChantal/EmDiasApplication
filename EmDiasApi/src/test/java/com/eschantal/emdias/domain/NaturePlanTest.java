package com.eschantal.emdias.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eschantal.emdias.web.rest.TestUtil;

public class NaturePlanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NaturePlan.class);
        NaturePlan naturePlan1 = new NaturePlan();
        naturePlan1.setId(1L);
        NaturePlan naturePlan2 = new NaturePlan();
        naturePlan2.setId(naturePlan1.getId());
        assertThat(naturePlan1).isEqualTo(naturePlan2);
        naturePlan2.setId(2L);
        assertThat(naturePlan1).isNotEqualTo(naturePlan2);
        naturePlan1.setId(null);
        assertThat(naturePlan1).isNotEqualTo(naturePlan2);
    }
}
