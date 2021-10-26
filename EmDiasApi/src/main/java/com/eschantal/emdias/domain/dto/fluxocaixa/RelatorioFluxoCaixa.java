package com.eschantal.emdias.domain.dto.fluxocaixa;

import com.eschantal.emdias.domain.NaturePlan;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;


public class RelatorioFluxoCaixa implements Serializable {
    private String titulo;
    private Map<String,LinhaFluxoCaixa> linhaFluxoCaixaSet;

    public RelatorioFluxoCaixa(String titulo) {
        this.titulo = titulo;
        this.linhaFluxoCaixaSet = new TreeMap<String,LinhaFluxoCaixa>();
    }
    @JsonIgnore
    public Map<LocalDate,Double> getSumAllDays() {
        Map<LocalDate,Double> totalDiasMap = new TreeMap<LocalDate,Double>();
        this.linhaFluxoCaixaSet.forEach((key,value)->{
             value.getMapDias().
                 forEach((day,celula)->{
                     if (!totalDiasMap.containsKey(day)){
                         totalDiasMap.put(day,celula.getValor().doubleValue());
                     } else {
                         totalDiasMap.put(day,totalDiasMap.get(day).doubleValue()+celula.getValor().doubleValue());
                     }
                 });
        });
        return totalDiasMap;
    }
    public Set<String> getDescriptionLines() {
        return this.linhaFluxoCaixaSet.keySet();
    }
    public void addLinha(NaturePlan naturePlan, ZonedDateTime dia , Double valor) {
        String id = "1." + naturePlan.getId();
        String descricao = naturePlan.getDescNaturePlan();
        if (!linhaFluxoCaixaSet.containsKey(id)){
            linhaFluxoCaixaSet.put(id,LinhaFluxoCaixaBuilder.umaLinhaFluxoCaixaDTO(id,descricao)
                .withDia(dia, BigDecimal.valueOf(valor))
                .build());
        } else {
            linhaFluxoCaixaSet.put(id,linhaFluxoCaixaSet.get(id)
                .add(dia.toLocalDate(),BigDecimal.valueOf(valor)));
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Map<String,LinhaFluxoCaixa> getLinhaFluxoCaixaSet() {
        return linhaFluxoCaixaSet;
    }

    public void setLinhaFluxoCaixaSet(Map<String,LinhaFluxoCaixa> linhaFluxoCaixaSet) {
        this.linhaFluxoCaixaSet = linhaFluxoCaixaSet;
    }
}
