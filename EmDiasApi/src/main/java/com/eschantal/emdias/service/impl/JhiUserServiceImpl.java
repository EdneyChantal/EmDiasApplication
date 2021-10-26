package com.eschantal.emdias.service.impl;

import com.eschantal.emdias.service.JhiUserService;
import com.eschantal.emdias.domain.JhiUser;
import com.eschantal.emdias.repository.JhiUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link JhiUser}.
 */
@Service
@Transactional
public class JhiUserServiceImpl implements JhiUserService {

    private final Logger log = LoggerFactory.getLogger(JhiUserServiceImpl.class);

    private final JhiUserRepository jhiUserRepository;

    public JhiUserServiceImpl(JhiUserRepository jhiUserRepository) {
        this.jhiUserRepository = jhiUserRepository;
    }

    @Override
    public JhiUser save(JhiUser jhiUser) {
        log.debug("Request to save JhiUser : {}", jhiUser);
        return jhiUserRepository.save(jhiUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JhiUser> findAll() {
        log.debug("Request to get all JhiUsers");
        return jhiUserRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<JhiUser> findOne(Long id) {
        log.debug("Request to get JhiUser : {}", id);
        return jhiUserRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete JhiUser : {}", id);
        jhiUserRepository.deleteById(id);
    }
}
