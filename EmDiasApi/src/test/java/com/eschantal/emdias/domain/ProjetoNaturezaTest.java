package com.eschantal.emdias.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eschantal.emdias.web.rest.TestUtil;

public class ProjetoNaturezaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjetoNatureza.class);
        ProjetoNatureza projetoNatureza1 = new ProjetoNatureza();
        projetoNatureza1.setId(1L);
        ProjetoNatureza projetoNatureza2 = new ProjetoNatureza();
        projetoNatureza2.setId(projetoNatureza1.getId());
        assertThat(projetoNatureza1).isEqualTo(projetoNatureza2);
        projetoNatureza2.setId(2L);
        assertThat(projetoNatureza1).isNotEqualTo(projetoNatureza2);
        projetoNatureza1.setId(null);
        assertThat(projetoNatureza1).isNotEqualTo(projetoNatureza2);
    }
}
