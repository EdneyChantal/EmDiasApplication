package com.praticasolucoes.emdiasapiv2.repository;


import com.praticasolucoes.emdiasapiv2.domain.NaturePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the NaturePlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NaturePlanRepository extends JpaRepository<NaturePlan, Long> {

   @Query("Select u from NaturePlan u where u.workSpace.id = ?1")
   public List<NaturePlan> findByWorkSpaceId(long idworkspace);
}
