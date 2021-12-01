package com.myblogstory.blog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Класс описания полей для создания сущности в БД
 * @author Н.Черненко
 */

@Data
@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Заголовок
     */
    private String name;

    /**
     * Основной текст
     */
    private String text;

    /**
     * Время публикации
     */
    private Date data;

    @OneToOne
    @JoinTable(name = "blogs_users",
            joinColumns = @JoinColumn(name="blogs_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="users_id", referencedColumnName="id"))
    private User users;
}
