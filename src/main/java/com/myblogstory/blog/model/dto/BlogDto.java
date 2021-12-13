package com.myblogstory.blog.model.dto;

import com.myblogstory.blog.model.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BlogDto {
    // Заголовок статьи
    private String name;

    // Основной текст статьи
    private String text;

    // Время публикации
    private LocalDate data;

    private User user;
}
