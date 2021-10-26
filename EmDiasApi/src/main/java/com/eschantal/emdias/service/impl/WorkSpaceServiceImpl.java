package com.eschantal.emdias.service.impl;

import com.eschantal.emdias.service.WorkSpaceService;
import com.eschantal.emdias.domain.WorkSpace;
import com.eschantal.emdias.repository.WorkSpaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link WorkSpace}.
 */
@Service
@Transactional
public class WorkSpaceServiceImpl implements WorkSpaceService {

    private final Logger log = LoggerFactory.getLogger(WorkSpaceServiceImpl.class);

    private final WorkSpaceRepository workSpaceRepository;

    public WorkSpaceServiceImpl(WorkSpaceRepository workSpaceRepository) {
        this.workSpaceRepository = workSpaceRepository;
    }

    @Override
    public WorkSpace save(WorkSpace workSpace) {
        log.debug("Request to save WorkSpace : {}", workSpace);
        return workSpaceRepository.save(workSpace);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkSpace> findAll() {
        log.debug("Request to get all WorkSpaces");
        return workSpaceRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WorkSpace> findOne(Long id) {
        log.debug("Request to get WorkSpace : {}", id);
        return workSpaceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkSpace : {}", id);
        workSpaceRepository.deleteById(id);
    }
}
