package com.alfonsosarno.fonzy.services;

import com.alfonsosarno.fonzy.entities.User;
import com.alfonsosarno.fonzy.exceptions.UnauthorizedException;
import com.alfonsosarno.fonzy.payloads.LoginDTO;
import com.alfonsosarno.fonzy.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;


    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        //  Controllo credenziali
        // Controllo nel DB se esiste un utente con quell'indirizzo email (fornito nel body)
        User found = this.userService.findByEmail(body.email());

        //  Se esiste verifico che la sua password corrisponda a quella del body
        //  Se una delle 2 verifiche non va a buon fine --> 401
        if (bcrypt.matches(body.password(), found.getPassword())) {
            //  Se credenziali OK --> Genero un access token
            // Ritorno il token
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }

    }



}
