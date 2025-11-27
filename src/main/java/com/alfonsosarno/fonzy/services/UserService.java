package com.alfonsosarno.fonzy.services;

import com.alfonsosarno.fonzy.entities.User;
import com.alfonsosarno.fonzy.exceptions.BadRequestException;
import com.alfonsosarno.fonzy.exceptions.NotFoundException;
import com.alfonsosarno.fonzy.payloads.UserRequestDTO;
import com.alfonsosarno.fonzy.repositories.UserRepository;
import com.alfonsosarno.fonzy.tools.MailgunSender;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private MailgunSender mailgunSender;

    public Page<User> findAll(int page, int size, String sortBy) {

        if (size > 50) size = 50;

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending()
        );

        return this.userRepository.findAll(pageable);
    }

    public User save(UserRequestDTO body) {

        this.userRepository.findByEmail(body.email()).ifPresent(u -> {
            throw new BadRequestException("L'email " + u.getEmail() + " è già in uso!");
        });

        User newUser = new User(
                body.username(),
                body.email(),
                bcrypt.encode(body.password())
        );

        User saved = this.userRepository.save(newUser);

        mailgunSender.sendRegistrationEmail(saved);

        return saved;
    }

    public User findById(UUID id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public User findByIdAndUpdate(UUID id, UserRequestDTO body) {

        User found = this.findById(id);

        if (!found.getEmail().equals(body.email())) {
            this.userRepository.findByEmail(body.email()).ifPresent(user -> {
                throw new BadRequestException("L'email " + user.getEmail() + " è già in uso");
            });
        }

        found.setUsername(body.username());
        found.setEmail(body.email());
        found.setPassword(bcrypt.encode(body.password()));

        User updated = this.userRepository.save(found);

        log.info("L'utente con id " + updated.getIdUser() + " è stato aggiornato.");

        return updated;
    }

    public void findByIdAndDelete(UUID id) {
        User found = this.findById(id);
        this.userRepository.delete(found);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
    }
}
