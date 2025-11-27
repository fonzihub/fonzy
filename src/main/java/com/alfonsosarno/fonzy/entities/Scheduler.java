package com.alfonsosarno.fonzy.entities;

import com.alfonsosarno.fonzy.services.AbbonamentoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final AbbonamentoService abbonamentoService;

    public Scheduler(AbbonamentoService abbonamentoService) {
        this.abbonamentoService = abbonamentoService;
    }

    @Scheduled(cron = "0 0 9 * * ?") // ogni giorno alle 9:00
    public void controllaAbbonamentiInScadenza() {
        abbonamentoService.inviaEmailScadenzaAbbonamenti();
    }
}
