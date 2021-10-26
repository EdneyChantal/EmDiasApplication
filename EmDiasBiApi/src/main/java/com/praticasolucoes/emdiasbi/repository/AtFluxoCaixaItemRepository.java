package com.praticasolucoes.emdiasbi.repository;

import com.praticasolucoes.emdiasbi.domain.AtFluxoCaixaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtFluxoCaixaItemRepository extends JpaRepository<AtFluxoCaixaItem,Long> {

    Optional<AtFluxoCaixaItem> findByCvIdNatureza(Long cvIdNatureza);
}
