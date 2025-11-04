package com.alfonsosarno.fonzy.payloads;

import com.alfonsosarno.fonzy.entities.ruolo;

import java.util.UUID;

public record UserResponseDTO(UUID id, String username, String email, ruolo ruolo) {
}
