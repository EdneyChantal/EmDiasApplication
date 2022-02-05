package com.praticasolucoes.emdiasapiv2.repository;


import com.praticasolucoes.emdiasapiv2.domain.MovementBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the MovementBank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovementBankRepository extends JpaRepository<MovementBank, Long> {
   @Query("select u from MovementBank as u where u.accountBank.id = ?1 and u.movementDate >= ?2 and u.movementDate <= ?3")
   List<MovementBank> findByAccountBankAndIntervaloDeData(long accountBankId,ZonedDateTime dataInicial , ZonedDateTime dataFinal);
   @Query("select u from MovementBank as u where u.accountBank.workSpace.id = ?1 and  u.movementDate >= ?2 and u.movementDate <= ?3")
   Optional<List<MovementBank>> findByWorkspaceAndIntervaloData(long workspaceId, ZonedDateTime dataInicial , ZonedDateTime dataFinal);
   @Query("select sum(u.movementValue) from MovementBank u where u.accountBank.id = ?1 and u.movementDate <= ?2")
   Optional<Double>  totalContaAteAData(long accountBankId,ZonedDateTime dataFinal);
   @Query("select u.naturePlan.id as idNature,sum(u.movementValue) as valorTotal from MovementBank as u where u.accountBank.workSpace.id = ?1 and u.movementDate >= ?2 and u.movementDate <= ?3 group by u.naturePlan.id")
   List<Object> totalPorNatureza(long workSpaceId,ZonedDateTime dataInicial , ZonedDateTime dataFinal);
   @Query("select sum(u.movementValue) from MovementBank u where u.accountBank.workSpace.id = ?1 and u.movementDate <= ?2")
   Optional<Double>  totalGeralAteAData(long workspaceId,ZonedDateTime dataFinal);
   @Query("select u from MovementBank u where u.accountBank.id = ?1 and u.movementDate >= ?2 and u.movementDate <= ?3 and u.naturePlan.id = ?4")
        //@Query("select u from MovementBank u where u.accountBank.id = ?1")
   List<MovementBank> findByAccountBankAndIntervaloDeDataNatureza(long accountBankId,ZonedDateTime dataInicial , ZonedDateTime dataFinal,long naturePlanId );
   @Query("select u from MovementBank u where u.movementDate >= ?1 and u.movementDate <= ?2 and u.naturePlan.id = ?3")
        //@Query("select u from MovementBank u where u.accountBank.id = ?1")
   List<MovementBank> findByIntervaloDeDataNatureza(ZonedDateTime dataInicial , ZonedDateTime dataFinal,long naturePlanId );
   @Query("select sum(u.movementValue) as valorTotal from MovementBank as u where u.accountBank.workSpace.id = ?1 and u.movementDate >= ?2 and u.movementDate <= ?3 and u.naturePlan.id = ?4")
   double totalDaNatureza(long workSpaceId, ZonedDateTime dataInicial , ZonedDateTime dataFinal , long naturezaId);
   @Query("select u.movementDate,u.accountBank.id,sum(u.movementValue) as valorTotal from MovementBank as u " +
          "where u.accountBank.workSpace.id = ?1 and u.movementDate >= ?2 and u.movementDate <= " +
          "?3 and u.naturePlan.id = ?4 group by u.movementDate,u.accountBank.id")
   List<Object[]> totalRealizadoNaturezaDia(long workSpaceId, ZonedDateTime dataInicial , ZonedDateTime dataFinal , long naturezaId);
}
