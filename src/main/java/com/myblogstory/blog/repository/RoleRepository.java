package com.myblogstory.blog.repository;

import com.myblogstory.blog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Подключение модуля SpringJPA для {@link Role} class
 * @author Н.Черненко
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
