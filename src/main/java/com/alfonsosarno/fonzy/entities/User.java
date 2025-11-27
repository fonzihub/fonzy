package com.alfonsosarno.fonzy.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"password", "authorities", "enabled", "accountNonLocked", "accountNonExpired", "credentialsNonExpired"})
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idUser;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private ruolo ruolo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Abbonamento> abbonamenti;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo.USER;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Questo metodo vogliamo che restituisca una lista di Authorities, cioè dei ruoli dell'utente

        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    @JsonProperty("login")
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
