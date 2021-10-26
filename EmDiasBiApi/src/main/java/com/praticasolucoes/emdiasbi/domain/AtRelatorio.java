package com.praticasolucoes.emdiasbi.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="AT_RELATORIO")
public class AtRelatorio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "at_relatorio_id")
    @SequenceGenerator(name="at_relatorio_id",sequenceName = "AT_RELATORIO_ID_seq" ,allocationSize = 1)
    @Column(name="ID")
    private Long id;
    @Column(name="NOME_RELATORIO")
    private String nomeRelatorio;
    @Column(name="CV_ID_PROJETO",unique = true)
    private Long cvIdRelatorio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRelatorio() {
        return nomeRelatorio;
    }

    public void setNomeRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }

    public Long getCvIdRelatorio() {
        return cvIdRelatorio;
    }

    public void setCvIdRelatorio(Long cvIdRelatorio) {
        this.cvIdRelatorio = cvIdRelatorio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtRelatorio that = (AtRelatorio) o;
        return Objects.equals(id, that.id) && Objects.equals(cvIdRelatorio, that.cvIdRelatorio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvIdRelatorio);
    }
}
