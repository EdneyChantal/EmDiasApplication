package com.eschantal.emdias.service.impl;

import com.eschantal.emdias.service.ProjetoNaturezaService;
import com.eschantal.emdias.domain.ProjetoNatureza;
import com.eschantal.emdias.repository.ProjetoNaturezaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ProjetoNatureza}.
 */
@Service
@Transactional
public class ProjetoNaturezaServiceImpl implements ProjetoNaturezaService {

    private final Logger log = LoggerFactory.getLogger(ProjetoNaturezaServiceImpl.class);

    private final ProjetoNaturezaRepository projetoNaturezaRepository;

    public ProjetoNaturezaServiceImpl(ProjetoNaturezaRepository projetoNaturezaRepository) {
        this.projetoNaturezaRepository = projetoNaturezaRepository;
    }

    @Override
    public ProjetoNatureza save(ProjetoNatureza projetoNatureza) {
        log.debug("Request to save ProjetoNatureza : {}", projetoNatureza);
        return projetoNaturezaRepository.save(projetoNatureza);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjetoNatureza> findAll() {
        log.debug("Request to get all ProjetoNaturezas");
        return projetoNaturezaRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProjetoNatureza> findOne(Long id) {
        log.debug("Request to get ProjetoNatureza : {}", id);
        return projetoNaturezaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjetoNatureza : {}", id);
        projetoNaturezaRepository.deleteById(id);
    }

    @Override
    public List<ProjetoNatureza> buscarPorProjetoeIntervaloData(Long ProjetoId, ZonedDateTime dataInicial, ZonedDateTime dataFinal) {

        return projetoNaturezaRepository.findByIdProjetoIntervaloData(ProjetoId,dataInicial,dataFinal);

    }
}
