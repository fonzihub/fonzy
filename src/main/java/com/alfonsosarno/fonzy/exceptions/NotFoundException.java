package com.alfonsosarno.fonzy.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID uuid) {
        super("l'abbonamento con id: " + uuid + " non è stato trovato");



    }
        public NotFoundException(String message){
            super(message);
        }
}

