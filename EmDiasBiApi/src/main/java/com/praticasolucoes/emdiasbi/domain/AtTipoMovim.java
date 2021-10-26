package com.praticasolucoes.emdiasbi.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="AT_TYPE_MOVIM")
public class AtTipoMovim  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "at_type_movim_id_seq")
    @SequenceGenerator(name = "at_type_movim_id_seq",sequenceName = "AT_TYPE_MOVIM_ID_seq",allocationSize = 1)
    @Column(name="ID")
    private Long id;
    @Column(name="DESCRICAO")
    private String descricao;
    @Column(name="CV_ID")
    private Long cvId;

    public Long getCvId() {
        return cvId;
    }

    public void setCvId(Long cvId) {
        this.cvId = cvId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtTipoMovim that = (AtTipoMovim) o;
        return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao);
    }
}
