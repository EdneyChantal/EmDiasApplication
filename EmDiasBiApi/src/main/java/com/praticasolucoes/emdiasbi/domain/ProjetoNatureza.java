package com.praticasolucoes.emdiasbi.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A ProjetoNatureza.
 */
public class ProjetoNatureza implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    private Double valorPrevisto;


    private Double valorRealizado;


    private ZonedDateTime dia;

    private NaturePlan naturePlan;

    private Projeto projeto;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorPrevisto() {
        return valorPrevisto;
    }

    public ProjetoNatureza valorPrevisto(Double valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
        return this;
    }

    public ZonedDateTime getDia() {
        return dia;
    }

    public void setDia(ZonedDateTime dia) {
        this.dia = dia;
    }

    public void setValorPrevisto(Double valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public Double getValorRealizado() {
        return valorRealizado;
    }

    public ProjetoNatureza valorRealizado(Double valorRealizado) {
        this.valorRealizado = valorRealizado;
        return this;
    }

    public void setValorRealizado(Double valorRealizado) {
        this.valorRealizado = valorRealizado;
    }

    public NaturePlan getNaturePlan() {
        return naturePlan;
    }

    public ProjetoNatureza naturePlan(NaturePlan naturePlan) {
        this.naturePlan = naturePlan;
        return this;
    }

    public void setNaturePlan(NaturePlan naturePlan) {
        this.naturePlan = naturePlan;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public ProjetoNatureza projeto(Projeto projeto) {
        this.projeto = projeto;
        return this;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjetoNatureza)) {
            return false;
        }
        return id != null && id.equals(((ProjetoNatureza) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjetoNatureza{" +
            "id=" + getId() +
            ", valorPrevisto=" + getValorPrevisto() +
            ", valorRealizado=" + getValorRealizado() +
            "}";
    }
}
