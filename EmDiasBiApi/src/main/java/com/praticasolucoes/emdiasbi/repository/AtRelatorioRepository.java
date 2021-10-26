package com.praticasolucoes.emdiasbi.repository;

import com.praticasolucoes.emdiasbi.domain.AtRelatorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtRelatorioRepository extends JpaRepository<AtRelatorio,Long> {

    Optional<AtRelatorio> findByCvIdRelatorio(Long cvIdRelatorio);
}
