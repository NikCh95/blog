package com.myblogstory.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myblogstory.blog.model.Blog;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс сбора данных, которые отправляются при регистрации нового пользователя
 * @author Н.Черненко
 */

@Data
public class UserDto {

    private String userName;
    private String lastName;
    private String patronymic;

    @NotEmpty
    @Size(min=4)
    private String password;

    @Email
    private String email;
    private String[] roles;
    private Set<Blog> blogs;
}
