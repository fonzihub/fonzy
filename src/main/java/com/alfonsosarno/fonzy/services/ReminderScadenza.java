package com.alfonsosarno.fonzy.services;

import com.alfonsosarno.fonzy.entities.Abbonamento;
import com.alfonsosarno.fonzy.repositories.AbbonamentoRepository;
import com.alfonsosarno.fonzy.tools.MailgunSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@EnableScheduling
@Service
public class ReminderScadenza {
    @Autowired
    AbbonamentoRepository abbonamentoRepository;
    @Autowired
    MailgunSender mailgunSender;




    @Scheduled(cron = "0 0 8 * * *")
    public void sendReminder(){
        LocalDate oggi = LocalDate.now();
        LocalDate fraTreGiorni = oggi.plusDays(3);
        List<Abbonamento> inScadenza = abbonamentoRepository.findByDataRinnovoBetween(oggi, fraTreGiorni);

        for(Abbonamento a : inScadenza){
            mailgunSender.sendBillingEmail(a.getUser(), a);
        }
    }
}
