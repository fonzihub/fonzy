package com.alfonsosarno.fonzy.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"user"})
public class Abbonamento {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(name = "id_subscription")
    private UUID idAbbonamento;
    private String nomeAbbonamento;
    private Double prezzo;
    @Enumerated(EnumType.STRING)
    private frequenza frequenza;
    private String categoria;
    private LocalDate dataRinnovo;
    private String logoUrl;

    @ManyToOne
    private User user;

//    @ManyToOne
//    private Catalogo catalogo;



    public Abbonamento(String nomeAbbonamento, Double prezzo, frequenza frequenza, String categoria, LocalDate dataRinnovo) {
        this.nomeAbbonamento = nomeAbbonamento;
        this.prezzo = prezzo;
        this.frequenza = frequenza;
        this.categoria = categoria;
        this.dataRinnovo = dataRinnovo;
    }



}
