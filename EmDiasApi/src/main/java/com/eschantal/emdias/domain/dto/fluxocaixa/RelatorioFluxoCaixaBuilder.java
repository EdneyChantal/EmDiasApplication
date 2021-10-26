package com.eschantal.emdias.domain.dto.fluxocaixa;

import javafx.util.Builder;

public class RelatorioFluxoCaixaBuilder  implements Builder<RelatorioFluxoCaixa> {
    private RelatorioFluxoCaixa relatorioFluxoCaixa;

    public RelatorioFluxoCaixaBuilder(RelatorioFluxoCaixa relatorioFluxoCaixa) {
        this.relatorioFluxoCaixa = relatorioFluxoCaixa;
    }

    public static RelatorioFluxoCaixaBuilder umRelatorioFluxoCaixa(String titulo) {
        return new RelatorioFluxoCaixaBuilder(new RelatorioFluxoCaixa(titulo));
    }

    @Override
    public RelatorioFluxoCaixa build() {
        return this.relatorioFluxoCaixa;
    }
}
