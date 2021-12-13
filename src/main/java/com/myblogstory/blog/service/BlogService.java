package com.myblogstory.blog.service;

import com.myblogstory.blog.model.Blog;
import com.myblogstory.blog.model.User;
import com.myblogstory.blog.model.dto.BlogDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Основные методы по работе с БД
 */
@Component
public interface BlogService {

    /**
     * Метод по сохраннению данных в БД
     */
     Blog saveBlog(BlogDto blogDto);

    /**
     * Найти все записи в БД
     */
    List<Blog> findAll();

    /**
     * Найти данные по Id
     */
    Blog getById(Long id);

    /**
     * Удалить данные из базы данных
     */
    void deleteBlog(Long id);

    /**
     * изменить данные
     */
    void updateBlog(Blog blog);

    Blog findByName(String name);


}
