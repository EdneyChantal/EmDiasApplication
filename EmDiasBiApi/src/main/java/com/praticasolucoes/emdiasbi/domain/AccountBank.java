package com.praticasolucoes.emdiasbi.domain;



import java.io.Serializable;

public class AccountBank implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private WorkSpace workSpace;
    private String nomeDaContaBancaria;
    private Double valorInicial;
    private String codContaExtrato;
    private String digito;
    private String tipo;

    public String getTipo() {
        return tipo;
    }
    public AccountBank tipo(String tipo ){
        this.tipo = tipo;
        return this;
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
