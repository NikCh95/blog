package com.myblogstory.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Класс описания полей для создания сущности в БД
 * @author Н.Черненко
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_blogs")
public class Blog extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Заголовок статьи
    private String name;

    // Основной текст статьи
    private String text;

    // Время публикации
    private LocalDate data  = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "blogs_id")
    @JsonIgnoreProperties("blogs")
    private User user;
}
