package com.alfonsosarno.fonzy.tools;

import com.alfonsosarno.fonzy.entities.Abbonamento;
import com.alfonsosarno.fonzy.entities.User;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import kong.unirest.core.HttpResponse;


@Component
public class MailgunSender {
    private final String domain;
    private final String apiKey;


    public MailgunSender(@Value("${mailgun.domain}") String domain, @Value("${mailgun.apiKey}") String apiKey) {
        this.domain = domain;
        this.apiKey = apiKey;
    }

    public void sendRegistrationEmail(User recipient) {

        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domain + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "brad@sandbox244a1f5e40eb4904980a949ae26268c6.mailgun.org")
                .queryString("to", recipient.getEmail())
                .queryString("subject", "Registrazione completata")
                .queryString("text", "Ciao, " + recipient.getUsername() + ", benvenuto in Fonzy!")
                .asJson();
        System.out.println(response.getBody());
    }

    public void sendBillingEmail(User recipient, Abbonamento abbonamento) {
        String testo = "Ciao " + recipient.getUsername() +
                ", il tuo abbonamento \"" + abbonamento.getNomeAbbonamento() + "\" scadrà tra 3 giorni, il " +
                abbonamento.getDataRinnovo() + ". Ti invitiamo a rinnovarlo per continuare a usare il servizio.";

        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domain + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "alfonsosarno1@icloud.com")
                .queryString("to", recipient.getEmail())
                .queryString("subject", "Abbonamento in scadenza")
                .queryString("text", testo)
                .asJson();

        System.out.println(response.getBody());
    }

}

