package com.eschantal.emdias.domain;

public class Resposta {

    private String mensagem;

    public Resposta() {

    }

    public Resposta(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }



    public static Resposta OPERACAO_REALIZADA_COM_SUCESSO () {
        return new Resposta("Operação Realizada com Sucesso");

    }
}
