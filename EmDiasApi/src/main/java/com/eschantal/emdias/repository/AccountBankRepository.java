package com.eschantal.emdias.repository;

import com.eschantal.emdias.domain.AccountBank;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the AccountBank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountBankRepository extends JpaRepository<AccountBank, Long> {

    @Query("select x from AccountBank as x where x.workSpace.id = ?1")
    Optional<List<AccountBank>> findByWorkSpace(Long workSpaceId);
}
