package com.alfonsosarno.fonzy.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Subscription {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idSubscription;
    private String nomeAbbonamento;
    private double prezzo;
    @Enumerated(EnumType.STRING)
    private frequenza frequenza;
    private String categoria;
    private LocalDate dataRinnovo;

    @ManyToOne
    private User user;

    public Subscription(String nomeAbbonamento, double prezzo, frequenza frequenza, String categoria, LocalDate dataRinnovo) {
        this.nomeAbbonamento = nomeAbbonamento;
        this.prezzo = prezzo;
        this.frequenza = frequenza;
        this.categoria = categoria;
        this.dataRinnovo = dataRinnovo;
    }
}
