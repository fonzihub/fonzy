package com.alfonsosarno.fonzy.security;

import com.alfonsosarno.fonzy.entities.User;
import com.alfonsosarno.fonzy.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.UUID;
import java.util.stream.DoubleStream;

@Component



public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(User user) {

        return Jwts.builder()
//               data emissione toke
                .issuedAt(new Date(System.currentTimeMillis()))
//               data scadenza token
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
//               a chi appartiene il token
                .subject(String.valueOf(user.getIdUser()))
//               firmo il token con HMAC
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
//               compatto nell stringa finale che è il token
                .compact();

    }
public void verifyToken(String accessToken){
        try{
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(accessToken);
        }catch(Exception exception){
            throw new UnauthorizedException("Ci sono stati errori nel token! Effettua di nuovo il login!");
        }
}

    public UUID extractIdFromToken(String accessToken) {
        return UUID.fromString(Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build()
                .parseSignedClaims(accessToken)
                .getPayload()
                .getSubject());
    }
}