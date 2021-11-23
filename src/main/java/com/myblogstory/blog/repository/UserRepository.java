package com.myblogstory.blog.repository;

import com.myblogstory.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Подключение модуля SpringJPA для {@link User} class
 * @author Н.Черненко
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
