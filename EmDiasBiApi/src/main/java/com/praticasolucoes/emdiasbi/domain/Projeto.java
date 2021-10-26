package com.praticasolucoes.emdiasbi.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
public class Projeto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    private String nomeProjeto;


    private ZonedDateTime dataInicial;


    private ZonedDateTime dataFinal;

    private WorkSpace workSpace;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public Projeto nomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
        return this;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public ZonedDateTime getDataInicial() {
        return dataInicial;
    }

    public Projeto dataInicial(ZonedDateTime dataInicial) {
        this.dataInicial = dataInicial;
        return this;
    }

    public void setDataInicial(ZonedDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public ZonedDateTime getDataFinal() {
        return dataFinal;
    }

    public Projeto dataFinal(ZonedDateTime dataFinal) {
        this.dataFinal = dataFinal;
        return this;
    }

    public void setDataFinal(ZonedDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }

    public WorkSpace getWorkSpace() {
        return workSpace;
    }

    public Projeto workSpace(WorkSpace workSpace) {
        this.workSpace = workSpace;
        return this;
    }

    public void setWorkSpace(WorkSpace workSpace) {
        this.workSpace = workSpace;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Projeto)) {
            return false;
        }
        return id != null && id.equals(((Projeto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Projeto{" +
            "id=" + getId() +
            ", nomeProjeto='" + getNomeProjeto() + "'" +
            ", dataInicial='" + getDataInicial() + "'" +
            ", dataFinal='" + getDataFinal() + "'" +
            "}";
    }
}
