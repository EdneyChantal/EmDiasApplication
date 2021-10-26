package com.praticasolucoes.emdiasbi.domain;


import java.io.Serializable;

public class NaturePlan implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private WorkSpace workSpace;
    String descNaturePlan;
    private NaturePlan naturePlanFather;
    private TypeNaturePlan typeNaturePlan;
    private String ind_control_orcamento;

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



    public String getInd_control_orcamento() {
        return ind_control_orcamento;
    }

    public void setInd_control_orcamento(String ind_control_orcamento) {
        this.ind_control_orcamento = ind_control_orcamento;
    }
}
