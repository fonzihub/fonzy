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

        User found = this.userService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), found.getPassword())) {
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
