package com.alfonsosarno.fonzy.services;

import com.alfonsosarno.fonzy.entities.Abbonamento;
import com.alfonsosarno.fonzy.entities.User;
import com.alfonsosarno.fonzy.exceptions.NotFoundException;
import com.alfonsosarno.fonzy.payloads.NewAbbonamentoRequestDTO;
import com.alfonsosarno.fonzy.repositories.AbbonamentoRepository;

import com.alfonsosarno.fonzy.tools.MailgunSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AbbonamentoService {

    private final MailgunSender mailgunSender;

    @Autowired
    private AbbonamentoRepository abbonamentoRepository;

    // GET ALL
    public List<Abbonamento> findAll() {
        return this.abbonamentoRepository.findAll();
    }

    // CREATE
    public Abbonamento createAbbonamento(NewAbbonamentoRequestDTO body, User user) {

        Abbonamento a = new Abbonamento();

        a.setNomeAbbonamento(body.nomeAbbonamento());
        a.setPrezzo(body.prezzo());
        a.setDataRinnovo(body.dataRinnovo());
        a.setFrequenza(body.frequenza());
        a.setCategoria(body.categoria());
        a.setUser(user);
        a.setLogoUrl(body.logoUrl());

//        String nome = body.nomeAbbonamento().toLowerCase();
//        a.setLogoUrl("https://logo.clearbit.com/" + nome + ".com");

        try {
            Abbonamento saved = abbonamentoRepository.save(a);
            System.out.println("SALVATO: " + saved);
            return saved;
        } catch (Exception e) {
            System.err.println(" ERRORE SALVATAGGIO: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }


    }

    public List<Abbonamento> findByUser(User user) {
        return this.abbonamentoRepository.findByUser(user);
    }

    // FIND BY ID
    public Abbonamento findById(UUID abbonamentoId) {
        return this.abbonamentoRepository.findById(abbonamentoId)
                .orElseThrow(() -> new NotFoundException(abbonamentoId));
    }

    // UPDATE
    public Abbonamento findByIdAndUpdate(UUID abbonamentoId, NewAbbonamentoRequestDTO body) {
        Abbonamento abb = this.findById(abbonamentoId);

        if (body.nomeAbbonamento() != null) abb.setNomeAbbonamento(body.nomeAbbonamento());
        if (body.categoria() != null) abb.setCategoria(body.categoria());
        if (body.prezzo() != null) abb.setPrezzo(body.prezzo());
        if (body.dataRinnovo() != null) abb.setDataRinnovo(body.dataRinnovo());
        if (body.frequenza() != null) abb.setFrequenza(body.frequenza());

        return this.abbonamentoRepository.save(abb);
    }

    // DELETE
    public void findByIdAndDelete(UUID abbonamentoId) {
        Abbonamento found = this.findById(abbonamentoId);
        this.abbonamentoRepository.delete(found);
    }
//--------------- MAILGUN


    public AbbonamentoService(AbbonamentoRepository abbonamentoRepository, MailgunSender mailgunSender) {
        this.abbonamentoRepository = abbonamentoRepository;
        this.mailgunSender = mailgunSender;
    }

    public void inviaEmailScadenzaAbbonamenti() {
        LocalDate traTreGiorni = LocalDate.now().plusDays(3);
        List<Abbonamento> inScadenza = abbonamentoRepository.findByDataRinnovo(traTreGiorni);

        for (Abbonamento abb : inScadenza) {
            mailgunSender.sendBillingEmail(abb.getUser(), abb);
        }
    }
}
