package com.alfonsosarno.fonzy.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

        @NotBlank(message = "Username obbligatorio")
        @Size(min = 3, max = 15, message = "lo username deve avere una lunghezza compresa tra 3 e 15 caratteri")
        String username,

        @NotBlank(message = "L'email è obbligatoria!")
        @Email(message = "L'email inserito non è corretto")
        String email,

        @NotBlank(message = "La password è obbligatoria!")
        @Size(min = 6, message = "La password deve avere minimo 6 caratteri")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{6,}$",
                message = "La password deve contenere almeno una lettera maiuscola, una minuscola, un numero e un carattere speciale, e deve essere lunga almeno 6 caratteri."
        )
        String password) {
}
