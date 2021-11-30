package com.myblogstory.blog.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность пользователя
 * @author Н.Черненко
 */

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * имя
     */
    private String userName;

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * Отчество
     */
    private String patronymic;


    /**
     * Пароль для авторизации пользователя
     */
    @Size(min = 4)
    private String password;

    /**
     * email пользователя
     */
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name="users_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="roles_id", referencedColumnName="id"))
    private Set<Role> roles = new HashSet<>();
}