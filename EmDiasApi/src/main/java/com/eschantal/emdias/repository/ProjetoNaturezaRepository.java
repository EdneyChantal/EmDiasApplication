package com.eschantal.emdias.repository;

import com.eschantal.emdias.domain.Projeto;
import com.eschantal.emdias.domain.ProjetoNatureza;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring Data  repository for the ProjetoNatureza entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjetoNaturezaRepository extends JpaRepository<ProjetoNatureza, Long> {

    @Query("select u from ProjetoNatureza u where u.projeto.id = ?1 and u.naturePlan.id = ?2 and u.dia = ?3")
    public ProjetoNatureza findByIdProjetoIdNaturePlan(long idProjeto, long idNaturePlan, ZonedDateTime dia) ;
    @Query("select u from ProjetoNatureza u where u.projeto.id = ?1 and u.dia >= ?2 and u.dia <= ?3")
    List<ProjetoNatureza> findByIdProjetoIntervaloData(Long projetoId,ZonedDateTime dataIni,ZonedDateTime dataFim);

}
