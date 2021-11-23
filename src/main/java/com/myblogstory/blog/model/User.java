package com.myblogstory.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность пользователя
 * @author Н.Черненко
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * имя пользователя
     */
    private String userName;

    /**
     * Пароль для авторизации пользователя
     */
    @NotEmpty
    @Size(min = 4)
    private String password;

    /**
     * Пароль для сверка с веденным паролем
     */
    @NotEmpty
    @Transient
    private String passwordConfirm;

    /**
     * email пользователя
     */
    private String email;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}