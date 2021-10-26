package com.eschantal.emdias;

import com.eschantal.emdias.domain.dto.fluxocaixa.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({CelulaFluxoCaixaTest.class,
                LinhaFluxoCaixaTest.class,
                RelatorioFluxoCaixaTest.class})

public class UnitTests {
}
