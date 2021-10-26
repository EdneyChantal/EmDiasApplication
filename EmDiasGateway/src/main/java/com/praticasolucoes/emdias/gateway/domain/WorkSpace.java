package com.praticasolucoes.emdias.gateway.domain;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "tbworkspace")
public class WorkSpace implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_workspace")
    private Long id;

    @Column(name="ds_workspace")
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
