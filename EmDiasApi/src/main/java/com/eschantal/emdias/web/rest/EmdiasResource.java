package com.eschantal.emdias.web.rest;

import com.eschantal.emdias.domain.AccountBank;
import com.eschantal.emdias.domain.NaturePlan;
import com.eschantal.emdias.domain.WorkSpace;
import com.eschantal.emdias.repository.AccountBankRepository;
import com.eschantal.emdias.repository.NaturePlanRepository;
import com.eschantal.emdias.service.AccountBankService;
import com.eschantal.emdias.service.NaturePlanService;
import com.eschantal.emdias.service.WorkspacelistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * EmdiasResource controller
 */
@RestController
@RequestMapping("/api/emdias")
public class EmdiasResource {

    private final Logger log = LoggerFactory.getLogger(EmdiasResource.class);


    @Autowired
    public EmdiasResource() {
    }


}
