package com.praticasolucoes.emdiasbi.repository;

import com.praticasolucoes.emdiasbi.domain.AtTipoMovim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtTipoMovimRepository extends JpaRepository<AtTipoMovim,Long> {

    Optional<AtTipoMovim> findByDescricao (String descricao);
}
