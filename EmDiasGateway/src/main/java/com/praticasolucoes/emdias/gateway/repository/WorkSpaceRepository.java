package com.praticasolucoes.emdias.gateway.repository;

import com.praticasolucoes.emdias.gateway.domain.WorkSpace;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface WorkSpaceRepository extends R2dbcRepository<WorkSpace,Long> {
    Mono<WorkSpace> findById(Long id);
}
