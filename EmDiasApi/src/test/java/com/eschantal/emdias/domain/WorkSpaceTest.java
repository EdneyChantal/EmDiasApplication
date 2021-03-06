package com.eschantal.emdias.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eschantal.emdias.web.rest.TestUtil;

public class WorkSpaceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkSpace.class);
        WorkSpace workSpace1 = new WorkSpace();
        workSpace1.setId(1L);
        WorkSpace workSpace2 = new WorkSpace();
        workSpace2.setId(workSpace1.getId());
        assertThat(workSpace1).isEqualTo(workSpace2);
        workSpace2.setId(2L);
        assertThat(workSpace1).isNotEqualTo(workSpace2);
        workSpace1.setId(null);
        assertThat(workSpace1).isNotEqualTo(workSpace2);
    }
}
