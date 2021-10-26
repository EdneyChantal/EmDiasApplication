package com.eschantal.emdias.domain.dto.fluxocaixa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Celula do Fluxo de Caixa")
public class CelulaFluxoCaixaTest {

   /* @RunWith(Suite.class)
    @Suite.SuiteClasses({
        AddSomarPositivo.class
    })

    public static class AllTests {}*/


    protected CelulaFluxoCaixa celulaFluxoCaixa;
    @BeforeEach
    public void init() {
        celulaFluxoCaixa = new CelulaFluxoCaixa(ZonedDateTime.now());
    }


        @Test
        void adddevesomarpositivo() {
            celulaFluxoCaixa.setValor(new BigDecimal(200));
            celulaFluxoCaixa.add(new BigDecimal(300));
            assertEquals(new BigDecimal(500), celulaFluxoCaixa.getValor());
        }

        @Test
        void addcomnulodevesomarpositivo() {
            celulaFluxoCaixa.setValor(new BigDecimal(200));
            celulaFluxoCaixa.add(null);
            assertEquals(new BigDecimal(200), celulaFluxoCaixa.getValor());
        }

        @Test
        void adddevesomarnegativo() {
            celulaFluxoCaixa.setValor(new BigDecimal(200));
            celulaFluxoCaixa.add(new BigDecimal(-100));
            assertEquals(new BigDecimal(100), celulaFluxoCaixa.getValor());
        }

}
