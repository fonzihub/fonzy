package com.alfonsosarno.fonzy.controllers;

import com.alfonsosarno.fonzy.entities.User;
import com.alfonsosarno.fonzy.exceptions.ValidationException;
import com.alfonsosarno.fonzy.payloads.LoginDTO;
import com.alfonsosarno.fonzy.payloads.LoginResponseDTO;
import com.alfonsosarno.fonzy.payloads.UserRequestDTO;
import com.alfonsosarno.fonzy.services.AuthService;
import com.alfonsosarno.fonzy.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService usersService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body){
        return new LoginResponseDTO(authService.checkCredentialsAndGenerateToken(body));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Validated UserRequestDTO body, BindingResult validationResult){
        // @Validated serve per "attivare" la validazione
        // BindingResult è un oggetto che contiene tutti gli errori e anche dei metodi comodi da usare tipo .hasErrors()

        if(validationResult.hasErrors()){
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return this.usersService.save(body);
    }
}
