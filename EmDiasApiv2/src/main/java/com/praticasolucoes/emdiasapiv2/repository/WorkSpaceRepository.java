package com.praticasolucoes.emdiasapiv2.repository;

import com.praticasolucoes.emdiasapiv2.domain.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WorkSpace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Long> {
}
