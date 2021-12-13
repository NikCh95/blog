package com.myblogstory.blog.model;

import lombok.Data;

import javax.persistence.*;

/***
 * Роль пользователя в приложении
 * @author Н.Черненко
 */

@Data
@Entity
@Table(name = "t_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Roles roleName;
}
