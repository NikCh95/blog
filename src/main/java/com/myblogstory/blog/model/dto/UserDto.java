package com.myblogstory.blog.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Класс передачи данных
 */

@Data
public class UserDto {

    private Long id;
    private String userName;

    @NotEmpty
    @Size(min=4)
    private String password;

    @Email
    private String email;
}
