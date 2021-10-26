package com.eschantal.emdias.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eschantal.emdias.web.rest.TestUtil;

public class MovementBankTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MovementBank.class);
        MovementBank movementBank1 = new MovementBank();
        movementBank1.setId(1L);
        MovementBank movementBank2 = new MovementBank();
        movementBank2.setId(movementBank1.getId());
        assertThat(movementBank1).isEqualTo(movementBank2);
        movementBank2.setId(2L);
        assertThat(movementBank1).isNotEqualTo(movementBank2);
        movementBank1.setId(null);
        assertThat(movementBank1).isNotEqualTo(movementBank2);
    }
}
