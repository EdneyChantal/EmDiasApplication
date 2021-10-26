package com.eschantal.emdias.repository;

import com.eschantal.emdias.domain.UserWorkSpace;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserWorkSpace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserWorkSpaceRepository extends JpaRepository<UserWorkSpace, Long> {
}
