package com.eschantal.emdias.service.impl;

import com.eschantal.emdias.service.AccountBankService;
import com.eschantal.emdias.domain.AccountBank;
import com.eschantal.emdias.repository.AccountBankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link AccountBank}.
 */
@Service
@Transactional
public class AccountBankServiceImpl implements AccountBankService {

    private final Logger log = LoggerFactory.getLogger(AccountBankServiceImpl.class);

    private final AccountBankRepository accountBankRepository;

    public AccountBankServiceImpl(AccountBankRepository accountBankRepository) {
        this.accountBankRepository = accountBankRepository;
    }

    @Override
    public AccountBank save(AccountBank accountBank) {
        log.debug("Request to save AccountBank : {}", accountBank);
        return accountBankRepository.save(accountBank);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountBank> findAll() {
        log.debug("Request to get all AccountBanks");
        return accountBankRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AccountBank> findOne(Long id) {
        log.debug("Request to get AccountBank : {}", id);
        return accountBankRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountBank : {}", id);
        accountBankRepository.deleteById(id);
    }
}
