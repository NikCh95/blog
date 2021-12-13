package com.myblogstory.blog.repository;

import com.myblogstory.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Подключение модуля SpringJPA для {@link Blog} class
 * @author Н.Черненко
 */

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Blog findByName(String name);
}
