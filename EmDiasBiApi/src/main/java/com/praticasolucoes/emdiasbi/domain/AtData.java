package com.praticasolucoes.emdiasbi.domain;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name="at_data")
public class AtData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "at_data_id_seq")
    @SequenceGenerator(name = "at_data_id_seq",sequenceName = "at_datA_id_seq",allocationSize = 1)
   private Long id;
    @Column(name="cv_dt")
   private ZonedDateTime cvDt;
    @Column
   private Long dia;
    @Column
   private Long mes;
    @Column
   private Long ano;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCvDt() {
        return cvDt;
    }

    public void setCvDt(ZonedDateTime cv_dt) {
        this.cvDt = cv_dt;
        this.ano = Long.valueOf(this.cvDt.getYear());
        this.mes = Long.valueOf(this.cvDt.getMonthValue());
        this.dia = Long.valueOf(this.cvDt.getDayOfMonth());
    }

    public Long getDia() {
        return dia;
    }

    public void setDia(Long dia) {
        this.dia = dia;
    }

    public Long getMes() {
        return mes;
    }

    public void setMes(Long mes) {
        this.mes = mes;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtData atData = (AtData) o;
        return Objects.equals(id, atData.id) && Objects.equals(cvDt, atData.cvDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvDt);
    }
}
