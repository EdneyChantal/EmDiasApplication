package com.praticasolucoes.emdiasbi.repository;

import com.praticasolucoes.emdiasbi.domain.AtData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface AtDataRepository extends JpaRepository<AtData,Long> {
    Optional<AtData> findByCvDt(ZonedDateTime cvdt);
}
