package com.eschantal.emdias.service.impl;

import com.eschantal.emdias.service.ProjetoService;
import com.eschantal.emdias.domain.Projeto;
import com.eschantal.emdias.repository.ProjetoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Projeto}.
 */
@Service
@Transactional
public class ProjetoServiceImpl implements ProjetoService {

    private final Logger log = LoggerFactory.getLogger(ProjetoServiceImpl.class);

    private final ProjetoRepository projetoRepository;

    public ProjetoServiceImpl(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @Override
    public Projeto save(Projeto projeto) {
        log.debug("Request to save Projeto : {}", projeto);
        return projetoRepository.save(projeto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Projeto> findAll() {
        log.debug("Request to get all Projetos");
        return projetoRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Projeto> findOne(Long id) {
        log.debug("Request to get Projeto : {}", id);
        return projetoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Projeto : {}", id);
        projetoRepository.deleteById(id);
    }
}
