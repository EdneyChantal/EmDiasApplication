package com.praticasolucoes.emdiasapiv2.domain.fluxocaixa;





import com.praticasolucoes.emdiasapiv2.util.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class CelulaFluxoCaixaBuilder implements Builder<CelulaFluxoCaixa> {
    private CelulaFluxoCaixa celulaFluxoCaixaDTO;

    public CelulaFluxoCaixaBuilder(CelulaFluxoCaixa celulaFluxoCaixaDTO) {
        this.celulaFluxoCaixaDTO = celulaFluxoCaixaDTO;
    }

    public static CelulaFluxoCaixaBuilder umaCelulaFluxoCaixaDTO(ZonedDateTime dia ) {
        return new CelulaFluxoCaixaBuilder(new CelulaFluxoCaixa(dia));
    }
    public static CelulaFluxoCaixaBuilder umaCelulaFluxoCaixaDTO(LocalDate dia ) {
        return new CelulaFluxoCaixaBuilder(new CelulaFluxoCaixa(dia));
    }


    public CelulaFluxoCaixaBuilder withValor(BigDecimal valor) {
        this.celulaFluxoCaixaDTO.setValor(valor);
        return this;
    }

    @Override
    public CelulaFluxoCaixa build() {
        return this.celulaFluxoCaixaDTO;
    }
}
