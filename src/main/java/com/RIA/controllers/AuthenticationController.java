package com.RIA.controllers;

import com.RIA.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.RIA.utils.SecurityConstants.JWT_EXPIRATION_TIME;
import static com.RIA.utils.SecurityConstants.JWT_SECRET;

@RestController("/token")
public class AuthenticationController {

    private static final String EMAIL = "fulano@ria.com";
    private static final String PASSWORD = "qwerty";
    private static final String USERNAME = "Fulano";

    @PostMapping
    public String getToken(@RequestBody @Valid User user) {

        String email = user.getEmail();
        String password = user.getPassword();

        if (!EMAIL.equals(email) || !PASSWORD.equals(password)) {
            return "Email o contraseña incorrectos!!";
        }

        //Incluyo el nombre del usuario como uno de los parametros a ser codificados en el token emitido
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", USERNAME);

        return createToken(email, claims);
    }

    private String createToken(String email, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }


}
