package com.eschantal.emdias.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


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
        this.nome = nome;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkSpace workSpace = (WorkSpace) o;
        if (this.id==null) return false ;
        return Objects.equals(id, workSpace.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
