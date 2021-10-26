package com.praticasolucoes.emdiasbi.service.impl;

import com.praticasolucoes.emdiasbi.domain.AtData;
import com.praticasolucoes.emdiasbi.repository.AtDataRepository;
import com.praticasolucoes.emdiasbi.service.FatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DataFatoService extends FatoService<ZonedDateTime,AtData> {
    private final AtDataRepository atDataRepository;

    @Autowired
    public DataFatoService(AtDataRepository atDataRepository) {
        this.atDataRepository = atDataRepository;
    }

    @Override
    public void insereFato(ZonedDateTime localDate){
        AtData atData = new AtData();
        atData.setCvDt(localDate);
        atDataRepository.save(atData);
    }

    @Override
    protected Optional<AtData> buscaFato(ZonedDateTime item) {
        return atDataRepository.findByCvDt(item);
    }
}
