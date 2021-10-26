package com.praticasolucoes.emdiasbi.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Types;
import java.time.ZonedDateTime;

@Repository
public class CuFluxoCaixaRepository  {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CuFluxoCaixaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteFluxo(ZonedDateTime dataini , ZonedDateTime dataFim){
       Object[] psParams = new Object[] {convertToDate(dataini),convertToDate(dataFim)};
       int[] types= new int[]{Types.DATE,Types.DATE};

        String query =  new StringBuffer()
            .append("delete from cu_fluxo_caixa as x where x.id_data in (")
            .append("select y.id from at_data as y where y.cv_dt>=? and  y.cv_dt<=?")
            .append(" )")
            .toString() ;
        int result = jdbcTemplate.update(query,psParams,types);

    };
    private Date convertToDate(ZonedDateTime dateTime) {
        return  Date.valueOf(dateTime.toLocalDate());
    }
}
