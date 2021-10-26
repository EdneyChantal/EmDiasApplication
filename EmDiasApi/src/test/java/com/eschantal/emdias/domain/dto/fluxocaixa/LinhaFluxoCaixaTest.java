package com.eschantal.emdias.domain.dto.fluxocaixa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Linha do fluxo de Caixa")
public class LinhaFluxoCaixaTest {

    protected LinhaFluxoCaixa linhaFluxoCaixa;

    @BeforeEach
    public void init (){
        linhaFluxoCaixa = new LinhaFluxoCaixa("1","linha teste");
    }


    @Test
    void add() {
        LocalDate agora = ZonedDateTime.now().toLocalDate();

        assertEquals(0,linhaFluxoCaixa.getMapDias().size());
        linhaFluxoCaixa.add(agora,new BigDecimal(1200));
        assertEquals(1,linhaFluxoCaixa.getMapDias().size());
        linhaFluxoCaixa.add(agora,new BigDecimal(500));
        assertEquals(1,linhaFluxoCaixa.getMapDias().size());
        assertEquals(new BigDecimal(1700),linhaFluxoCaixa.getMapDias().get(agora).getValor());

        LocalDate agora2 = ZonedDateTime.now().toLocalDate().plusDays(1);
        linhaFluxoCaixa.add(agora2,new BigDecimal(1350));
        assertEquals(2,linhaFluxoCaixa.getMapDias().size());
        assertEquals(new BigDecimal(1350),linhaFluxoCaixa.getMapDias().get(agora2).getValor());
        assertEquals(new BigDecimal(1700),linhaFluxoCaixa.getMapDias().get(agora).getValor());

        linhaFluxoCaixa.add(agora2,new BigDecimal(-200));
        assertEquals(new BigDecimal(1150),linhaFluxoCaixa.getMapDias().get(agora2).getValor());

        linhaFluxoCaixa.add(agora2, (Double) null);




    }
    @Test
    void pegaDiaMaisProximo() {
        LocalDate agora = ZonedDateTime.now().toLocalDate();
        List<LocalDate>  listasDias =
            IntStream
            .range(0,10)
            .mapToObj(i->agora.plusDays(i))
            .collect(Collectors.toList());
        listasDias
            .stream()
            .forEach(dia-> linhaFluxoCaixa.add(dia,new BigDecimal(dia.getDayOfMonth()*1000)));
        assertEquals(10,linhaFluxoCaixa.getMapDias().size());
        CelulaFluxoCaixa retornado = linhaFluxoCaixa.pegaDiaMaisProximo(listasDias.get(4));
        assertEquals(listasDias.get(3),retornado.getDia());
        assertEquals(new BigDecimal(
            listasDias.get(3).getDayOfMonth()*1000),
        retornado.getValor());


    }
}
