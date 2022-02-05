package com.praticasolucoes.emdiasapiv2.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;


public class NaturezaResumo implements Serializable {

    private static final long serialVersionUID = 1L;
    private ProjetoNatureza projetoNatureza;
    private long idNature;
    private double valorTotal;
    private NaturePlan naturePlan;
    private ZonedDateTime dia;
    private AccountBank accountBank;

    public AccountBank getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(AccountBank accountBank) {
        this.accountBank = accountBank;
    }

    public ProjetoNatureza getProjetoNatureza() {
        return projetoNatureza;
    }

    public void setProjetoNatureza(ProjetoNatureza projetoNatureza) {
        this.projetoNatureza = projetoNatureza;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getIdNature() {
        return idNature;
    }

    public void setIdNature(long idNature) {
        this.idNature = idNature;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public NaturePlan getNaturePlan() {
        return naturePlan;
    }

    public void setNaturePlan(NaturePlan naturePlan) {
        this.naturePlan = naturePlan;
    }

    public ZonedDateTime getDia() {
        return dia;
    }

    public void setDia(ZonedDateTime dia) {
        this.dia = dia;
    }
}
