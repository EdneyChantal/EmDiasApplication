package com.praticasolucoes.emdiasbi.cucumber;

import com.praticasolucoes.emdiasbi.EmdiasBiApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = EmdiasBiApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
