package com.praticasolucoes.emdiasbi.repository;

import com.praticasolucoes.emdiasbi.domain.CuFluxoCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Repository
public interface  CrudCuFluxoCaixaRepository extends JpaRepository<CuFluxoCaixa,Long> {
    @Transactional
    @Modifying
    @Query("delete from CuFluxoCaixa c where c.atData.cvDt >= ?1 and c.atData.cvDt <= ?2")
    public void deleteFluxo(ZonedDateTime dtini , ZonedDateTime dtfim);


}
