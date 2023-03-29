package com.banckiko.atm.login.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Clave secreta utilizada para firmar los tokens JWT
    private static final long EXPIRATION_TIME = 86400000; // Tiempo de expiración del token (24 horas)

    // Genera un token JWT con el nombre de usuario proporcionado
    public String generateToken(String username) {
        Date now = new Date(); // Fecha y hora actuales
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME); // Fecha y hora de expiración del token

        // Crea el token JWT utilizando la clase Jwts de io.jsonwebtoken
        return Jwts.builder()
                .setSubject(username) // Establece el sujeto del token como el nombre de usuario
                .setIssuedAt(now) // Establece la fecha y hora de emisión del token como la fecha y hora actuales
                .setExpiration(expirationDate) // Establece la fecha y hora de expiración del token como la fecha y hora de expiración calculada
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512) // Firma el token utilizando la clave secreta y el algoritmo HS512
                .compact(); // Convierte el token en una cadena compacta
    }

    // Obtiene el nombre de usuario del token JWT proporcionado
    public String getUsernameFromToken(String token) {
        // Utiliza la clase Jwts de io.jsonwebtoken para analizar y validar el token, y obtener el nombre de usuario del sujeto
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // Establece la clave secreta utilizada para firmar el token
                .parseClaimsJws(token) // Analiza el token y lo convierte en un objeto Jws
                .getBody() // Obtiene los datos del cuerpo del token
                .getSubject(); // Obtiene el nombre de usuario del sujeto
    }

    // Valida el token JWT proporcionado
    public boolean validateToken(String token) {
        try {
            // Utiliza la clase Jwts de io.jsonwebtoken para analizar y validar el token
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true; // El token es válido
        } catch (Exception ex) {
            return false; // El token no es válido
        }
    }
}