package com.praticasolucoes.emdiasapiv2.domain.fluxocaixa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

public class CelulaFluxoCaixa implements Serializable,Comparable<CelulaFluxoCaixa> {
    private LocalDate dia = LocalDate.now();
    private BigDecimal valor;


    public CelulaFluxoCaixa(LocalDate dia) {
        this.dia = dia;
        valor = new BigDecimal(0);


    }
    public CelulaFluxoCaixa(ZonedDateTime dia) {
        this.dia = dia.toLocalDate();
        valor = new BigDecimal(0);
    }

    public CelulaFluxoCaixa(LocalDate dia, BigDecimal valor) {
        this.dia = dia;
        this.valor = valor;
    }

    public CelulaFluxoCaixa add(BigDecimal valor) {
        if (valor !=null) this.valor = this.getValor().add(valor);
            return this;
    }
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }


    public LocalDate getDia() {
        return dia;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CelulaFluxoCaixa that = (CelulaFluxoCaixa) o;
        return dia.equals(that.dia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia);
    }

    @Override
    public int compareTo( CelulaFluxoCaixa o) {
        return this.dia.compareTo(o.getDia());
    }
}
