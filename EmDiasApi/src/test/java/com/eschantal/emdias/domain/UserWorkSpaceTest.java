package com.eschantal.emdias.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eschantal.emdias.web.rest.TestUtil;

public class UserWorkSpaceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserWorkSpace.class);
        UserWorkSpace userWorkSpace1 = new UserWorkSpace();
        userWorkSpace1.setId(1L);
        UserWorkSpace userWorkSpace2 = new UserWorkSpace();
        userWorkSpace2.setId(userWorkSpace1.getId());
        assertThat(userWorkSpace1).isEqualTo(userWorkSpace2);
        userWorkSpace2.setId(2L);
        assertThat(userWorkSpace1).isNotEqualTo(userWorkSpace2);
        userWorkSpace1.setId(null);
        assertThat(userWorkSpace1).isNotEqualTo(userWorkSpace2);
    }
}
