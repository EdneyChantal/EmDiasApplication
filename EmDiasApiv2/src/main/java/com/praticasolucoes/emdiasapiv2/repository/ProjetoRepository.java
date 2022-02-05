package com.praticasolucoes.emdiasapiv2.repository;


import com.praticasolucoes.emdiasapiv2.domain.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Projeto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
