package com.hotel.auth;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // clave secreta
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // extrae el email del token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // extrae la fecha de expiracion del token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // extrae un claim especifico de una funcion
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // extrae todos los claims del token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // comprobar si ha expirado el token
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Genera un token JWT para el usuario
    public String generateToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername());
    }

    // crear el token
    private String createToken(String subject) {

        long expirationTime = 1000 * 60 * 60 * 10; // 10 horas

        return Jwts.builder()
                .setSubject(subject) // normalmente es el email
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // validar el token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

