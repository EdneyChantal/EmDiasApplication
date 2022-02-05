package com.praticasolucoes.emdiasapiv2.repository;


import com.praticasolucoes.emdiasapiv2.domain.UserWorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserWorkSpace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserWorkSpaceRepository extends JpaRepository<UserWorkSpace, Long> {
}
