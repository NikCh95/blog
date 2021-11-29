package com.myblogstory.blog.config.jwt;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Класс ответа при входе в систему
 * @author  Н.Черненко
 */

@Getter
@Setter
public class AuthResponse {
    private String token;
    private List<String> roles;


}
