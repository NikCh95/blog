package com.myblogstory.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    // Заголовок статьи
    private String name;

    // Основной текст статьи
    private String text;

    // Время публикации
    private Date data;
}
