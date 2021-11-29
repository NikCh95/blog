package com.myblogstory.blog.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Класс сбора данных, которые отправляются при регистрации нового пользователя
 * @author Н.Черненко
 */

@Data
public class UserDto {

    private String userName;

    @NotEmpty
    @Size(min=4)
    private String password;

    @Email
    private String email;

    private String[] roles;
}
