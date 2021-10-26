package com.praticasolucoes.emdiasbi.service.impl;


import com.praticasolucoes.emdiasbi.domain.AtFluxoCaixaItem;
import com.praticasolucoes.emdiasbi.domain.NaturePlan;
import com.praticasolucoes.emdiasbi.repository.AtFluxoCaixaItemRepository;
import com.praticasolucoes.emdiasbi.service.FatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FluxoCaixaItemService extends FatoService<NaturePlan, AtFluxoCaixaItem> {
    private final AtFluxoCaixaItemRepository atFluxoCaixaItemRepository;

    @Autowired
    public FluxoCaixaItemService(AtFluxoCaixaItemRepository atFluxoCaixaItemRepository) {
        this.atFluxoCaixaItemRepository = atFluxoCaixaItemRepository;
    }

    @Override
    protected void insereFato(NaturePlan item) {
       AtFluxoCaixaItem atFluxoCaixaItem = new AtFluxoCaixaItem();
       atFluxoCaixaItem.setCvIdNatureza(item.getId());
       atFluxoCaixaItem.setDescricao(item.getDescNaturePlan());
       this.atFluxoCaixaItemRepository.save(atFluxoCaixaItem);
    }

    @Override
    protected Optional<AtFluxoCaixaItem> buscaFato(NaturePlan item) {
       return  atFluxoCaixaItemRepository.findByCvIdNatureza(item.getId());
    }
}
