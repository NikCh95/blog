package com.myblogstory.blog.config.jwt;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * JwtProvider — обычный компонент для работы с jwt token.
 * @author Н.Черненко
 */

@Log
@Component
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    /**
     *  Метод на вход которого будет приходить email пользователя, а на выходе будет строка jwt.
     *  Jwts.builder() конструкцию которая позволяет создать этот самый токен. в setSubject  добавлен email пользователя,
     *  чтобы потом его оттуда забрать в фильтре, когда пользователь будет делать запрос.
     *  setExpiration — указанно 15 дней.
     *  В случае если пройдет 15 дней и токен не обновить — будет выброшено сообщение об ошибке в методе {validateToken},
     *  который будет описан ниже. signWith — принимает на вход алгоритм подписи и кодовое слово,
     *  которое потом потребуется для расшифровки.
     */
    public String generateToken(String email) {
        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
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
            log.severe("недействительный токен");
        }
        return false;
    }

    /**
     *Получить информацию о email пользователя
     */
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
