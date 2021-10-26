package com.praticasolucoes.emdias.gateway.repository;

import com.praticasolucoes.emdias.gateway.domain.LoginWorkSpace;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface LoginWorkSpaceRepository extends R2dbcRepository<LoginWorkSpace, Long> {
    Flux<LoginWorkSpace> findByLogin(String login);
}
