package com.myblogstory.blog.repository;

import com.myblogstory.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Подключение модуля SpringJPA для {@link Blog} class
 * @author Н.Черненко
 */

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
