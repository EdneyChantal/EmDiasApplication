package com.eschantal.emdias.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A UserWorkSpace.
 */
@Entity
@Table(name = "tbuserworkspace")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserWorkSpace implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idworkspace")
    @JsonIgnoreProperties(value = "idworkspace", allowSetters = true)
    private WorkSpace workSpace;

    @ManyToOne
    @JoinColumn(name="login",referencedColumnName = "id")
    private JhiUser user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkSpace getWorkSpace() {
        return workSpace;
    }

    public UserWorkSpace workSpace(WorkSpace workSpace) {
        this.workSpace = workSpace;
        return this;
    }

    public void setWorkSpace(WorkSpace workSpace) {
        this.workSpace = workSpace;
    }

    public JhiUser getUser() {
        return user;
    }

    public UserWorkSpace user(JhiUser jhiUser) {
        this.user = jhiUser;
        return this;
    }

    public void setUser(JhiUser jhiUser) {
        this.user = jhiUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserWorkSpace)) {
            return false;
        }
        return id != null && id.equals(((UserWorkSpace) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserWorkSpace{" +
            "id=" + getId() +
            "}";
    }
}
