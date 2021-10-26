package com.praticasolucoes.emdiasbi.domain;

import javax.persistence.*;
import java.io.Serializable;


public class WorkSpace implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public WorkSpace nome(String nome) {
        nome = nome;
        return this;
    }



}
