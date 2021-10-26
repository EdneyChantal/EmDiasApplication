package com.eschantal.emdias.repository;

import com.eschantal.emdias.domain.NaturePlan;

import org.springframework.data.jpa.repository.*;
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
