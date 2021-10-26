package com.praticasolucoes.emdiasbi.service.impl;

import com.praticasolucoes.emdiasbi.domain.AccountBank;
import com.praticasolucoes.emdiasbi.domain.AtConta;
import com.praticasolucoes.emdiasbi.repository.AtContaRepository;
import com.praticasolucoes.emdiasbi.service.FatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaFatoService  extends  FatoService<AccountBank,AtConta> {
    private final AtContaRepository atContaRepository ;

    @Autowired
    public ContaFatoService(AtContaRepository atContaRepository) {
        this.atContaRepository = atContaRepository;
    }
    public  void insereFato(AccountBank accountBank){
        AtConta atConta = new AtConta();
        atConta.setNomeConta(accountBank.getNomeDaContaBancaria());
        atConta.setChIdconta(accountBank.getId());
        atConta.setTipo(accountBank.getTipo());
        atContaRepository.save(atConta);

    }

    @Override
    protected Optional<AtConta> buscaFato(AccountBank item) {
        return atContaRepository.findByChIdconta(item.getId());
    }
}
