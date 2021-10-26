package com.eschantal.emdias.service.impl;

import com.eschantal.emdias.service.UserWorkSpaceService;
import com.eschantal.emdias.domain.UserWorkSpace;
import com.eschantal.emdias.repository.UserWorkSpaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link UserWorkSpace}.
 */
@Service
@Transactional
public class UserWorkSpaceServiceImpl implements UserWorkSpaceService {

    private final Logger log = LoggerFactory.getLogger(UserWorkSpaceServiceImpl.class);

    private final UserWorkSpaceRepository userWorkSpaceRepository;

    public UserWorkSpaceServiceImpl(UserWorkSpaceRepository userWorkSpaceRepository) {
        this.userWorkSpaceRepository = userWorkSpaceRepository;
    }

    @Override
    public UserWorkSpace save(UserWorkSpace userWorkSpace) {
        log.debug("Request to save UserWorkSpace : {}", userWorkSpace);
        return userWorkSpaceRepository.save(userWorkSpace);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserWorkSpace> findAll() {
        log.debug("Request to get all UserWorkSpaces");
        return userWorkSpaceRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UserWorkSpace> findOne(Long id) {
        log.debug("Request to get UserWorkSpace : {}", id);
        return userWorkSpaceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserWorkSpace : {}", id);
        userWorkSpaceRepository.deleteById(id);
    }
}
