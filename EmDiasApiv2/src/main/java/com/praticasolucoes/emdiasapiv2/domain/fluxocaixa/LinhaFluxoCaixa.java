package com.praticasolucoes.emdiasapiv2.domain.fluxocaixa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LinhaFluxoCaixa implements Serializable,Comparable<LinhaFluxoCaixa> {
    private final String id;
    private final String descricao;
    private Map<LocalDate, CelulaFluxoCaixa> mapDias;

    public LinhaFluxoCaixa(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.mapDias = new TreeMap<LocalDate,CelulaFluxoCaixa>();
    }
    public LinhaFluxoCaixa add(ZonedDateTime dia , BigDecimal valor) {
        return add(dia.toLocalDate(),valor);
    }
    public LinhaFluxoCaixa add(ZonedDateTime dia , Double valor) {
        return add (dia.toLocalDate(),valor);
    }
    public LinhaFluxoCaixa add(LocalDate dia , Double valor) {
        if (valor!=null)  return this.add(dia,BigDecimal.valueOf(valor.doubleValue()));
        return this;
    }
    public void put(LocalDate dia , BigDecimal valor) {
        this.getMapDias().put(dia,CelulaFluxoCaixaBuilder.umaCelulaFluxoCaixaDTO(dia).withValor(valor).build());
   }
    public LinhaFluxoCaixa add(LocalDate  dia , BigDecimal valor){
        if (!this.getMapDias().containsKey(dia)) {
            this.getMapDias().put(dia, CelulaFluxoCaixaBuilder
                .umaCelulaFluxoCaixaDTO(dia)
                .withValor(valor)
                .build());
        } else{
            this.getMapDias()
                .put(dia ,
                    this.getMapDias()
                        .get(dia)
                        .add(valor));
        }
        return this;
    }
    public String getId() {
        return id;
    }
    public CelulaFluxoCaixa pegaDiaMaisProximo(ZonedDateTime dia) {
        return pegaDiaMaisProximo(dia.toLocalDate());
    }
    public CelulaFluxoCaixa pegaDiaMaisProximo(LocalDate dia) {
        List<LocalDate> diasList =
            this.getMapDias().keySet().stream()
            .filter(day-> day.isBefore(dia))
            .collect(Collectors.toList());
        if (diasList !=null  && diasList.size() > 0) {
           return this.getMapDias().get(diasList.get(diasList.size()-1));
        } else{
           return null;
        }
    }
    public String getDescricao() {
        return descricao;
    }

    public Map<LocalDate, CelulaFluxoCaixa> getMapDias() {
        return mapDias;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinhaFluxoCaixa that = (LinhaFluxoCaixa) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(LinhaFluxoCaixa o) {
        return this.getId().compareTo(o.getId());
    }
}
