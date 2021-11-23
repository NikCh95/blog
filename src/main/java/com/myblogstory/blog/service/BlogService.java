package com.myblogstory.blog.service;

import com.myblogstory.blog.model.Blog;

import java.util.List;

/**
 * Основные методы по работе с БД
 */
public interface BlogService {

    /**
     * Метод по сохраннению данных в БД
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * Найти все записи в БД
     * @return
     */
    List<Blog> findAll();

    /**
     * Найти данные по Id
     * @param id
     * @return
     */
    Blog getById(Long id);

    /**
     * Удалить данные из базы данных
     * @param id
     */
    void deleteBlog(Long id);

    /**
     * изменить данные
     * @param blog
     */
    void updateBlog(Blog blog);


}
