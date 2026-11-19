package org.example.antidoto.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey chave;
    private final long expiracao;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiracao
    ) {
        byte[] bytes = Base64.getDecoder().decode(secret);
        this.chave = Keys.hmacShaKeyFor(bytes);
        this.expiracao = expiracao;
    }

    public String gerarToken(String email, String role) {
        Date agora = new Date();
        Date vencimento = new Date(
                agora.getTime() + expiracao
        );

        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(agora)
                .expiration(vencimento)
                .signWith(chave)
                .compact();
    }

    public Claims validarToken(String token) {
        return Jwts.parser()
                .verifyWith(chave)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
