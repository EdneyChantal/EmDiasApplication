package com.praticasolucoes.emdiasbi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

public class ParametroCargaBi implements Serializable {
    private LocalDate mes;
    private Long IdProjeto;
    private LocalDate mesAnterior;
    private Long idWorkSpace;

    public LocalDate getMes() {
        return mes;
    }
    public LocalDate mesUltimoDia(LocalDate dia) {
      return dia.with(TemporalAdjusters.lastDayOfMonth());
    }

    public void setMes(LocalDate mes) {
        this.mes = mes.withDayOfMonth(1);
        this.mesAnterior = this.mes.minusMonths(-1);

    }

    public Long getIdWorkSpace() {
        return idWorkSpace;
    }

    public void setIdWorkSpace(Long idWorkSpace) {
        this.idWorkSpace = idWorkSpace;
    }

    public Long getIdProjeto() {
        return IdProjeto;
    }

    public void setIdProjeto(Long idProjeto) {
        IdProjeto = idProjeto;
    }

    public LocalDate getMesAnterior() {
        return mesAnterior;
    }

    public void setMesAnterior(LocalDate mesAnterior) {
        this.mesAnterior = mesAnterior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParametroCargaBi that = (ParametroCargaBi) o;
        return Objects.equals(mes, that.mes) && Objects.equals(IdProjeto, that.IdProjeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mes, IdProjeto);
    }
}
