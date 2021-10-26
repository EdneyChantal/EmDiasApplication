package com.eschantal.emdias.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eschantal.emdias.web.rest.TestUtil;

public class JhiUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JhiUser.class);
        JhiUser jhiUser1 = new JhiUser();
        jhiUser1.setId(1L);
        JhiUser jhiUser2 = new JhiUser();
        jhiUser2.setId(jhiUser1.getId());
        assertThat(jhiUser1).isEqualTo(jhiUser2);
        jhiUser2.setId(2L);
        assertThat(jhiUser1).isNotEqualTo(jhiUser2);
        jhiUser1.setId(null);
        assertThat(jhiUser1).isNotEqualTo(jhiUser2);
    }
}
