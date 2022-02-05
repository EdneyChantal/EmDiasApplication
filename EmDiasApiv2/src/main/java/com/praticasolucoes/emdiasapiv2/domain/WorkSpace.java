package com.praticasolucoes.emdiasapiv2.domain;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "tbworkspace")
public class WorkSpace implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator" ,sequenceName="workspace_seq" ,allocationSize=1)
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
