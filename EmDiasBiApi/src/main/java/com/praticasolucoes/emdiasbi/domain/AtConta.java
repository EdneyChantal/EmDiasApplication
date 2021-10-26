package com.praticasolucoes.emdiasbi.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="AT_CONTA")

public class AtConta  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "at_conta_id")
    @SequenceGenerator(name="at_conta_id",sequenceName = "AT_CONTA_ID_seq",allocationSize = 1)
    @Column(name="ID")
    private Long id;
    @Column(name = "NOME_CONTA")
    private String nomeConta;
    @Column(name = "CH_ID_CONTA",unique = true)
    private Long chIdconta;
    @Column(name = "TIPO")
    private String tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public Long getChIdconta() {
        return chIdconta;
    }

    public void setChIdconta(Long chIdconta) {
        this.chIdconta = chIdconta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtConta atConta = (AtConta) o;
        return Objects.equals(id, atConta.id) && Objects.equals(chIdconta, atConta.chIdconta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chIdconta);
    }
}
