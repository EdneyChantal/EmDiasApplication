package com.praticasolucoes.emdias.gateway.domain;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Id;
import javax.persistence.Transient;

@Table("tbloginworkspace")
public class LoginWorkSpace {

    @Id
    @Column("idworklogin")
    private Long id;

    @Column("login")
    private String login;

    @Column("idworkdspace")
    private Long idWorkSpace;

    @Transient
    private WorkSpace workSpace;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public WorkSpace getWorkSpace() {
        return workSpace;
    }

    public void setWorkSpace(WorkSpace workSpace) {
        this.workSpace = workSpace;
    }

    public Long getIdWorkSpace() {
        return idWorkSpace;
    }

    public void setIdWorkSpace(Long idWorkSpace) {
        this.idWorkSpace = idWorkSpace;
    }
}
