package com.eschantal.emdias.domain.dto.fluxocaixa;

import com.eschantal.emdias.domain.NaturePlan;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class RelatorioFluxoCaixa implements Serializable {
    private String titulo;
    private Map<String,LinhaFluxoCaixa> linhaFluxoCaixaSet;
    public final  LocalDate dataInicial;
    public final  LocalDate dataFinal;
    public static final String ID_SALDOINICIAL = "1";
    public static final String DESCRICAO_SALDO_INICIAL = "Saldo Inicial";
    public static final String ID_SALDOFINAL = "2";
    public static final String DESCRICAO_SALDO_FINAL = "Saldo Final";



    public RelatorioFluxoCaixa(String titulo, LocalDate dataInicial, LocalDate dataFinal) {
        this.titulo = titulo;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.linhaFluxoCaixaSet = new TreeMap<String,LinhaFluxoCaixa>();

    }
    public Set<LocalDate> pegarTodosOsDias() {
        LocalDate day = dataInicial;
        Set<LocalDate> days = new TreeSet<LocalDate>();
        while (day.isBefore(dataFinal) || day.isEqual(dataFinal)) {
             days.add(day);
             day = day.plusDays(1);
        }
        return days;
    }
    @JsonIgnore
    public Map<LocalDate,Double> getSumAllDays() {
        Map<LocalDate,Double> totalDiasMap = new TreeMap<LocalDate,Double>();
        this.linhaFluxoCaixaSet
            .forEach((key,value)->{
                if ((value.getId()!= ID_SALDOINICIAL) &&
                      (value.getId()!=ID_SALDOFINAL) ) {
                    value.getMapDias().
                        forEach((day, celula) -> {
                            if (!totalDiasMap.containsKey(day)) {
                                totalDiasMap.put(day, celula.getValor().doubleValue());
                            } else {
                                totalDiasMap.put(day, totalDiasMap.get(day).doubleValue() + celula.getValor().doubleValue());
                            }
                        });
                }
        });
        return totalDiasMap;
    }
    public BigDecimal pegarValorDiaConta(String pIdLinha , LocalDate pdia) {
        if  (!this.getLinhaFluxoCaixaSet()
            .get(pIdLinha)
            .getMapDias()
            .containsKey(pdia)) return new BigDecimal(0);

        return  this.getLinhaFluxoCaixaSet()
            .get(pIdLinha)
            .getMapDias()
            .get(pdia)
            .getValor();

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
