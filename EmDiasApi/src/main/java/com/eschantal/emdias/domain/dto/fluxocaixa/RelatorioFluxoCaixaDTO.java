package com.eschantal.emdias.domain.dto.fluxocaixa;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class RelatorioFluxoCaixaDTO {
    private class typeColumun {
        private String title;
        private String field;

        public typeColumun(String title, String field) {
            this.title = title;
            this.field = field;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }

    private Set<LocalDate> fieldColumnsValues;
    private RelatorioFluxoCaixa relatorioFluxoCaixa;
    private Set<String> keyLinhas ;
    private DateTimeFormatter dateFormatter;
    private Set<LinhaFluxoCaixaMesDTO> linhaFluxoCaixaMesDTOSet;
    private LinhaFluxoCaixaMesDTO linhaFluxoCaixaMesDTO;
    private List<typeColumun> configuracaoColunas;


    public RelatorioFluxoCaixaDTO(RelatorioFluxoCaixa relatorioFluxoCaixa) {
        this.relatorioFluxoCaixa = relatorioFluxoCaixa;
        this.dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        configuracaoColunas = new ArrayList<>();
        linhaFluxoCaixaMesDTOSet = new TreeSet<>();
        fieldColumnsValues = this.relatorioFluxoCaixa
            .getSumAllDays()
            .keySet();
        keyLinhas = relatorioFluxoCaixa.getLinhaFluxoCaixaSet().keySet();
        keyLinhas
            .stream()
            .forEach(this::operacao);

        configuracaoColunas.add(new typeColumun("Id","id"));
        configuracaoColunas.add(new typeColumun("Descrição","descricao"));
        configuracaoColunas.addAll(fieldColumnsValues
            .stream()
            .map(this::popularConfiguracaoColunas)
            .collect(Collectors.toList()));
    }

    public typeColumun popularConfiguracaoColunas(LocalDate dia) {
        Class<?> dto = linhaFluxoCaixaMesDTO.getClass();
        Field nomeColuna =
            Arrays.stream(dto.getDeclaredFields())
            .sequential()
            .filter(method -> method.getName().indexOf(
                String.format("coluna%d",dia.getDayOfMonth() )
            )!=-1)
            .findFirst()
            .orElseGet(null);
        return new typeColumun(dia.format(dateFormatter),nomeColuna.getName());
    }
    public void processarDia(LocalDate dia) {
        CelulaFluxoCaixa celulaFluxoCaixa =
            Optional.ofNullable(this.relatorioFluxoCaixa
                    .getLinhaFluxoCaixaSet()
                    .get(linhaFluxoCaixaMesDTO.getId())
                    .getMapDias()
                    .get(dia))
                .orElseGet( ()-> new CelulaFluxoCaixa(dia,new BigDecimal(0)));
        Class<?> dto = linhaFluxoCaixaMesDTO.getClass();
        Arrays.stream(dto.getDeclaredMethods())
            .sequential()
            .filter(method -> method.getName().indexOf(
                String.format("setColuna%d",dia.getDayOfMonth() )
            )!=-1)
            .forEach(method -> {
                try {
                    Object args[] = new Object[1];
                    args[0]= NumberFormat.getCurrencyInstance().format(celulaFluxoCaixa.getValor());
                    method.invoke(linhaFluxoCaixaMesDTO,args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
    }

    public List<typeColumun> getConfiguracaoColunas() {
        return configuracaoColunas;
    }
    private void zeraColunas(){
        Class<?> dto = linhaFluxoCaixaMesDTO.getClass();
        Arrays.stream(dto.getDeclaredMethods())
            .sequential()
            .filter(method -> method.getName().indexOf(
                "setColuna")
                !=-1)
            .forEach(method -> {
                try {
                    Object args[] = new Object[1];
                    args[0]= new Double(0).toString();
                    method.invoke(linhaFluxoCaixaMesDTO,args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
    }
    @JsonIgnore
    public void operacao(String keyLinha) {
        linhaFluxoCaixaMesDTO = new LinhaFluxoCaixaMesDTO(keyLinha,this
            .relatorioFluxoCaixa
            .getLinhaFluxoCaixaSet()
            .get(keyLinha)
            .getDescricao());
        zeraColunas();
        this.fieldColumnsValues
            .stream()
            .forEach(this::processarDia);
        this.linhaFluxoCaixaMesDTOSet.add(linhaFluxoCaixaMesDTO);
    }

    public Set<LinhaFluxoCaixaMesDTO> getLinhaFluxoCaixaMesDTOSet() {
        return linhaFluxoCaixaMesDTOSet;
    }
}
