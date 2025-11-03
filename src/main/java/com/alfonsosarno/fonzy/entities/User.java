package com.alfonsosarno.fonzy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idUser;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private ruolo ruolo;

    public User(String nome, String cognome, String email, String password, ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }
}
