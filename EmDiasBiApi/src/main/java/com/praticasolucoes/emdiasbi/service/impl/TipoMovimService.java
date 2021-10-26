package com.praticasolucoes.emdiasbi.service.impl;

import com.praticasolucoes.emdiasbi.domain.AtTipoMovim;
import com.praticasolucoes.emdiasbi.repository.AtTipoMovimRepository;
import com.praticasolucoes.emdiasbi.service.FatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoMovimService extends FatoService<String, AtTipoMovim> {

    private final AtTipoMovimRepository atTipoMovimRepository;

    @Autowired
    public TipoMovimService(AtTipoMovimRepository atTipoMovimRepository) {
        this.atTipoMovimRepository = atTipoMovimRepository;
    }

    @Override
    protected void insereFato(String item) {
      AtTipoMovim atTipoMovim = new AtTipoMovim();
      atTipoMovim.setDescricao(item);
      atTipoMovimRepository.save(atTipoMovim);
    }

    @Override
    protected Optional<AtTipoMovim> buscaFato(String item) {
        return atTipoMovimRepository.findByDescricao(item);
    }
}
