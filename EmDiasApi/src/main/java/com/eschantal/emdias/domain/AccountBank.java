package com.eschantal.emdias.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbaccountbank")
public class AccountBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accountbank")
    private Long id;

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "id_workspace" , referencedColumnName = "id_workspace"))
    private WorkSpace workSpace;

    @Column(name="ds_accountbank")
    private String nomeDaContaBancaria;

    @Column(name="initial_value")
    private Double valorInicial;

    @Column(name="cod_conta")
    private String codContaExtrato;

    @Column(name="digito")
    private String digito;

    @Column
    private String tipo;


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkSpace getWorkSpace() {
        return workSpace;
    }

    public AccountBank workSpace(WorkSpace workSpace) {
        this.workSpace = workSpace;
        return this;
    }

    public String getNomeDaContaBancaria() {
        return nomeDaContaBancaria;
    }

    public AccountBank nomeDaContaBancaria(String nomeDaContaBancaria) {
        this.nomeDaContaBancaria = nomeDaContaBancaria;
        return this;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public AccountBank valorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
        return this;
    }

    public String getCodContaExtrato() {
        return codContaExtrato;
    }

    public AccountBank codContaExtrato(String codContaExtrato) {
        this.codContaExtrato = codContaExtrato;
        return this;
    }

    public String getDigito() {
        return digito;
    }

    public AccountBank digito(String digito) {

        this.digito = digito;
        return this;
    }
}
