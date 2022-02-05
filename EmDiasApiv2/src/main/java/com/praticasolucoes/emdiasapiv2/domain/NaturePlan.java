package com.praticasolucoes.emdiasapiv2.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbnatureplan")
public class NaturePlan  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_natureplan")
    private Long id;

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "id_workspace" , referencedColumnName = "id_workspace"))
    private WorkSpace workSpace;

    @Column(name="ds_natureplan")
    String descNaturePlan;

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "id_natureplan_father"))
    private NaturePlan naturePlanFather;


    @Enumerated(EnumType.STRING)
    @Column(name="ind_type")
    private TypeNaturePlan typeNaturePlan;

    @Column
    private String ind_control_orcamento;

    public String getInd_control_orcamento() {
        return ind_control_orcamento;
    }

    public void setInd_control_orcamento(String ind_control_orcamento) {
        this.ind_control_orcamento = ind_control_orcamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkSpace getWorkSpace() {
        return workSpace;
    }

    public NaturePlan workSpace(WorkSpace workSpace) {
        this.workSpace = workSpace;
        return this;
    }

    public String getDescNaturePlan() {
        return descNaturePlan;
    }

    public NaturePlan descNaturePlan(String descNaturePlan) {
        this.descNaturePlan = descNaturePlan;
        return this;
    }

    public TypeNaturePlan getTypeNaturePlan() {
        return typeNaturePlan;
    }

    public NaturePlan typeNaturePlan(TypeNaturePlan typeNaturePlan) {
        this.typeNaturePlan = typeNaturePlan;
        return this;
    }

    public NaturePlan getNaturePlanFather() {
        return naturePlanFather;
    }

    public NaturePlan naturePlanFather(NaturePlan naturePlanFather) {
        this.naturePlanFather = naturePlanFather;
        return this;
    }
}
