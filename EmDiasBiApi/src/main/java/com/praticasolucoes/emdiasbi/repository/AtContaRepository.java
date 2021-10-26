package com.praticasolucoes.emdiasbi.repository;

import com.praticasolucoes.emdiasbi.domain.AtConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtContaRepository  extends JpaRepository<AtConta,Long > {

    Optional<AtConta> findByChIdconta(Long chIdConta);
}
