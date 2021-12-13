package com.myblogstory.blog.config.jwt;

import com.myblogstory.blog.security.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtProvider — обычный компонент для работы с jwt token.
 * @author Н.Черненко
 */

@Log
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtTokenExpiration;

    /**
     *  Метод на вход которого будет приходить email пользователя, а на выходе будет строка jwt.
     *  Jwt.builder() конструкцию которая позволяет создать этот самый токен. в setSubject  добавлен email пользователя,
     *  чтобы потом его оттуда забрать в фильтре, когда пользователь будет делать запрос.
     *  signWith — принимает на вход алгоритм подписи и кодовое слово,
     *  которое потом потребуется для расшифровки.
     */
    public String generateJwtToken(Authentication authentication) {
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.severe("Срок действия токена истек");
        } catch (UnsupportedJwtException unsEx) {
            log.severe("Неподдерживаемый jwt");
        } catch (MalformedJwtException mjEx) {
            log.severe("Неправильный формат jwt");
        } catch (SignatureException sEx) {
            log.severe("Неверная подпись");
        } catch (Exception e) {
            log.severe("Недействительный токен");
        }
        return false;
    }

    /**
     *Получить информацию о email пользователя
     */
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
