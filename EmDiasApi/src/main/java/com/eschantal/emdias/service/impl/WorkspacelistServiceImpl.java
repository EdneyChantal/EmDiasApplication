package com.eschantal.emdias.service.impl;

import com.eschantal.emdias.domain.WorkSpace;
import com.eschantal.emdias.repository.WorkSpaceRepository;
import com.eschantal.emdias.service.WorkspacelistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkspacelistServiceImpl implements WorkspacelistService {

    private final Logger log = LoggerFactory.getLogger(WorkspacelistServiceImpl.class);
    private WorkSpaceRepository workSpaceRepository;

    @Autowired
    public WorkspacelistServiceImpl(WorkSpaceRepository workSpaceRepository) {
        this.workSpaceRepository = workSpaceRepository;
    }

    @Override
    public List<WorkSpace> getAllWorkspace() {
        return this.workSpaceRepository.findAll();
    }
}
