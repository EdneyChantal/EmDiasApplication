package com.eschantal.emdias.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
