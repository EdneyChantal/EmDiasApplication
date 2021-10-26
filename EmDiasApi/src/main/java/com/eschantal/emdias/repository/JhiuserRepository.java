package com.eschantal.emdias.repository;

import com.eschantal.emdias.domain.JhiUser;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the JhiUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JhiuserRepository extends JpaRepository<JhiUser, Long> {
}
