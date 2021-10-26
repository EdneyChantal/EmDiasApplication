package com.eschantal.emdias.domain.dto.fluxocaixa;

import com.eschantal.emdias.domain.NaturePlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Relatório do Fluxo de Caixa")
public class RelatorioFluxoCaixaTest {

    protected RelatorioFluxoCaixa relatorioFluxoCaixa;

    @BeforeEach
    public void init() {
        relatorioFluxoCaixa = new RelatorioFluxoCaixa("isso é um teste");

    }

    @Test
    void getSumAllDays() {
    }

    private void verificarRelatorio(int quantidadeLinhas, int quantidadeDiasLinha,
                                    String idLinha, ZonedDateTime dia, Double valorDia) {
        assertEquals(quantidadeLinhas, relatorioFluxoCaixa.getLinhaFluxoCaixaSet().size());
        assertEquals(quantidadeDiasLinha, relatorioFluxoCaixa
            .getLinhaFluxoCaixaSet()
            .get(idLinha)
            .getMapDias().size());
        assertEquals(valorDia,
            new Double(relatorioFluxoCaixa
            .getLinhaFluxoCaixaSet()
            .get(idLinha)
            .getMapDias()
            .get(dia.toLocalDate())
            .getValor()
            .doubleValue()));

    }

    @Test
    void addLinha() {
        assertEquals(0, relatorioFluxoCaixa.getLinhaFluxoCaixaSet().size());
        NaturePlan naturePlan = new NaturePlan();
        naturePlan.setId(20L);
        naturePlan.descNaturePlan("TESTE 1");
        ZonedDateTime agora = ZonedDateTime.now();
        relatorioFluxoCaixa.addLinha(naturePlan, agora, new Double(1200));
        verificarRelatorio(1, 1, "1.20", agora, 1200D);
        relatorioFluxoCaixa.addLinha(naturePlan, agora, new Double(1000));
        verificarRelatorio(1, 1, "1.20", agora, 2200D);
        relatorioFluxoCaixa.addLinha(naturePlan, agora.plusDays(1), new Double(350));
        verificarRelatorio(1, 2, "1.20", agora.plusDays(1), 350D);
        relatorioFluxoCaixa.addLinha(naturePlan, agora, new Double(210));
        verificarRelatorio(1, 2, "1.20", agora, 2410D);
        Map<LocalDate, Double> sumAllDays = relatorioFluxoCaixa.getSumAllDays();
        assertEquals(2,sumAllDays.size());
        assertEquals(Optional.of(2410D).get(),sumAllDays.get(agora.toLocalDate()));
        assertEquals(Optional.of(350D).get(),sumAllDays.get(agora.toLocalDate().plusDays(1)));
    }
}
