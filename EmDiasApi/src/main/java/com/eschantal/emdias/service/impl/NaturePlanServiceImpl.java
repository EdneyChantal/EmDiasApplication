package com.eschantal.emdias.service.impl;

import com.eschantal.emdias.service.NaturePlanService;
import com.eschantal.emdias.domain.NaturePlan;
import com.eschantal.emdias.repository.NaturePlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NaturePlan}.
 */
@Service
@Transactional
public class NaturePlanServiceImpl implements NaturePlanService {

    private final Logger log = LoggerFactory.getLogger(NaturePlanServiceImpl.class);

    private final NaturePlanRepository naturePlanRepository;

    public NaturePlanServiceImpl(NaturePlanRepository naturePlanRepository) {
        this.naturePlanRepository = naturePlanRepository;
    }

    @Override
    public NaturePlan save(NaturePlan naturePlan) {
        log.debug("Request to save NaturePlan : {}", naturePlan);
        return naturePlanRepository.save(naturePlan);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NaturePlan> findAll() {
        log.debug("Request to get all NaturePlans");
        return naturePlanRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NaturePlan> findOne(Long id) {
        log.debug("Request to get NaturePlan : {}", id);
        return naturePlanRepository.findById(id);
    }

    @Override
    public List<NaturePlan> buscarTodosdoWorkspace(Long idWorkSpace) {
        return naturePlanRepository.findByWorkSpaceId(idWorkSpace)
            .stream()
            .filter(nat->nat.getNaturePlanFather()!=null)
            .collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NaturePlan : {}", id);
        naturePlanRepository.deleteById(id);
    }
}
