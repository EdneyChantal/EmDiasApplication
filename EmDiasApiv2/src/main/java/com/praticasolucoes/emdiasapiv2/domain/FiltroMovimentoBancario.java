package com.praticasolucoes.emdiasapiv2.domain;

import java.time.ZonedDateTime;

public class FiltroMovimentoBancario {

    private long accountBankId ;
    private ZonedDateTime dataInicial;
    private ZonedDateTime dataFinal;
    private long workSpaceId;
    private long naturePlanId ;
    private long projetoId ;
    private ZonedDateTime dataReferencia;

    public ZonedDateTime getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(ZonedDateTime dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(long projetoId) {
        this.projetoId = projetoId;
    }

    public long getNaturePlanId() {
        return naturePlanId;
    }

    public void setNaturePlanId(long naturePlanId) {
        this.naturePlanId = naturePlanId;
    }

    public long getWorkSpaceId() {
        return workSpaceId;
    }

    public void setWorkSpaceId(long workSpaceId) {
        this.workSpaceId = workSpaceId;
    }

    public long getAccountBankId() {
        return accountBankId;
    }

    public void setAccountBankId(long accountBankId) {
        this.accountBankId = accountBankId;
    }

    public ZonedDateTime getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(ZonedDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public ZonedDateTime getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(ZonedDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }
}
