package com.eschantal.emdias.domain;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FiltroFluxoCaixa {
    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    private Long workSpaceId;
    private ZonedDateTime dataInicial;
    private ZonedDateTime dataFinal;

    public static ZonedDateTime truncarData(ZonedDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern(DD_MM_YYYY);
        String dataString = data.format(DateTimeFormatter.ISO_DATE);
        return ZonedDateTime.parse(dataString, DateTimeFormatter.ISO_DATE);
    }

    public Long getWorkSpaceId() {
        return workSpaceId;
    }

    public void setWorkSpaceId(Long workSpaceId) {
        this.workSpaceId = workSpaceId;
    }

    public ZonedDateTime getDataInicial() {
        return this.dataInicial;
    }

    public void setDataInicial(ZonedDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public ZonedDateTime getDataFinal() {
        return this.dataFinal;
    }

    public void setDataFinal(ZonedDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }
}
