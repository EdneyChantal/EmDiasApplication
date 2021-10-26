package com.eschantal.emdias.domain.dto.fluxocaixa;



import com.eschantal.emdias.util.Builder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class LinhaFluxoCaixaBuilder implements Builder<LinhaFluxoCaixa> {

    private LinhaFluxoCaixa linhaFluxoCaixaDTO ;

    public LinhaFluxoCaixaBuilder(String id , String descricao ) {
        this.linhaFluxoCaixaDTO = new LinhaFluxoCaixa(id,descricao);
    }

    public static LinhaFluxoCaixaBuilder umaLinhaFluxoCaixaDTO(String id , String descricao) {
        return  new LinhaFluxoCaixaBuilder(id,descricao);
    }
    public LinhaFluxoCaixaBuilder withDia(ZonedDateTime dia , BigDecimal valor){
        this.linhaFluxoCaixaDTO.add(dia.toLocalDate(),valor);
        return this;
    }
    @Override
    public LinhaFluxoCaixa build() {
        return this.linhaFluxoCaixaDTO;
    }
}
