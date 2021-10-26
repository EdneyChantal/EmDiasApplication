package com.eschantal.emdias.repository;

import com.eschantal.emdias.domain.WorkSpace;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WorkSpace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Long> {
}
