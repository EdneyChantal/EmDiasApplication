package com.praticasolucoes.emdiasbi.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="at_fluxo_caixa_item")
public class AtFluxoCaixaItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "at_fluxo_caixa_item_id")
    @SequenceGenerator(name="at_fluxo_caixa_item_id",sequenceName = "AT_FLUXO_CAIXA_ITEM_ID_seq",allocationSize = 1)
    @Column(name="ID")
    private Long id;
    @Column(name="DESCRICAO")
    private String descricao;
    @Column(name="CV_ID_NATUREZA")
    private Long cvIdNatureza;
    @Column(name="CONTA")
    private String conta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCvIdNatureza() {
        return cvIdNatureza;
    }

    public void setCvIdNatureza(Long cvIdNatureza) {
        this.cvIdNatureza = cvIdNatureza;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtFluxoCaixaItem that = (AtFluxoCaixaItem) o;
        return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao) && Objects.equals(cvIdNatureza, that.cvIdNatureza) && Objects.equals(conta, that.conta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, cvIdNatureza, conta);
    }
}
