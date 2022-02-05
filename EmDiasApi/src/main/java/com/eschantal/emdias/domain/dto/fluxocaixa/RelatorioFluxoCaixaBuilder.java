package com.eschantal.emdias.domain.dto.fluxocaixa;


import com.eschantal.emdias.util.Builder;

import java.time.LocalDate;

public class RelatorioFluxoCaixaBuilder  implements Builder<RelatorioFluxoCaixa> {
    private RelatorioFluxoCaixa relatorioFluxoCaixa;

    public RelatorioFluxoCaixaBuilder(RelatorioFluxoCaixa relatorioFluxoCaixa) {
        this.relatorioFluxoCaixa = relatorioFluxoCaixa;
    }

    public static RelatorioFluxoCaixaBuilder umRelatorioFluxoCaixa(String titulo, LocalDate dataInicial , LocalDate dataFinal) {
        return new RelatorioFluxoCaixaBuilder(new RelatorioFluxoCaixa(titulo, dataInicial, dataFinal));
    }

    @Override
    public RelatorioFluxoCaixa build() {
        return this.relatorioFluxoCaixa;
    }
}
