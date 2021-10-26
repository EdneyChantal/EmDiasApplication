package com.praticasolucoes.emdiasbi.service;

import com.praticasolucoes.emdiasbi.domain.AccountBank;
import com.praticasolucoes.emdiasbi.domain.FiltroMovimentoBancario;
import com.praticasolucoes.emdiasbi.domain.NaturezaResumo;
import com.praticasolucoes.emdiasbi.domain.ProjetoNatureza;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("emdias")
public interface EmDiasApp {

    @RequestMapping(method = RequestMethod.GET,value="/api/account-banks")
    List<AccountBank> getAccountBank();

    @RequestMapping(method =RequestMethod.POST,value="/api/projeto-naturezas/buscarpeloprojetodatas")
     List<ProjetoNatureza> getPrevisto(@RequestBody FiltroMovimentoBancario filtroMovimentoBancario);

    @RequestMapping(method =RequestMethod.POST,value="/api/total-natureza")
    List<NaturezaResumo> getRealizado(@RequestBody FiltroMovimentoBancario filtroMovimentoBancario);

}
