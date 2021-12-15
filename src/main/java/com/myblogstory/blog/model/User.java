package com.myblogstory.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "t_users")
public class User extends AuditModel {

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

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "roles")
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private Set<Blog> blogs = new HashSet<>();

}