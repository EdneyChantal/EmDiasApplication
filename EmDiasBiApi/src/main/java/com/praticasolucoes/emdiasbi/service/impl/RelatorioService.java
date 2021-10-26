package com.praticasolucoes.emdiasbi.service.impl;

import com.praticasolucoes.emdiasbi.domain.AtRelatorio;
import com.praticasolucoes.emdiasbi.domain.Projeto;
import com.praticasolucoes.emdiasbi.repository.AtRelatorioRepository;
import com.praticasolucoes.emdiasbi.service.FatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RelatorioService extends FatoService<Projeto, AtRelatorio> {
    private final AtRelatorioRepository atRelatorioRepository;

    @Autowired
    public RelatorioService(AtRelatorioRepository atRelatorioRepository) {
        this.atRelatorioRepository = atRelatorioRepository;
    }

    @Override
    protected void insereFato(Projeto item) {
       AtRelatorio atRelatorio = new AtRelatorio();
       atRelatorio.setNomeRelatorio(item.getNomeProjeto());
       atRelatorio.setCvIdRelatorio(item.getId());
       atRelatorioRepository.save(atRelatorio);
    }

    @Override
    protected Optional<AtRelatorio> buscaFato(Projeto item) {
        return atRelatorioRepository.findByCvIdRelatorio(item.getId());
    }
}
