package com.praticasolucoes.emdiasapiv2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "tbmovementbank")

public class MovementBank  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmovementbank")
    private Long id;

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "id_accountbank"))
    private AccountBank accountBank;

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "id_natureplan"))
    private NaturePlan naturePlan;

    @Column(name="movementdate")
    private ZonedDateTime movementDate;

    @Column(name="movementvalue")
    private Double movementValue;

    @Column(name="history")
    private String history;

    @Column(name="number_doc")
    private String numberDoc;

    @Column(name="accid")
    private String accid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountBank getAccountBank() {
        return accountBank;
    }

    public MovementBank accountBank(AccountBank accountBank) {

        this.accountBank = accountBank;
        return this;
    }

    public NaturePlan getNaturePlan() {
        return naturePlan;
    }

    public MovementBank naturePlan(NaturePlan naturePlan) {
        this.naturePlan = naturePlan;
        return this;
    }

    public ZonedDateTime getMovementDate() {
        return movementDate;
    }

    public MovementBank movementDate(ZonedDateTime movementDate) {
        this.movementDate = movementDate;
        return this;
    }

    public Double getMovementValue() {
        return movementValue;
    }

    public MovementBank movementValue(Double movementvalue) {
        this.movementValue = movementvalue;
        return this;
    }

    public String getHistory() {
        return history;
    }

    public MovementBank history(String history) {
        this.history = history;
        return this;
    }

    public String getNumberDoc() {
        return numberDoc;
    }

    public MovementBank numberDoc(String numberDoc) {
        this.numberDoc = numberDoc;
        return this;
    }

    public String getAccid() {
        return accid;
    }

    public MovementBank accid(String accid) {
        this.accid = accid;
        return this;
    }
}
