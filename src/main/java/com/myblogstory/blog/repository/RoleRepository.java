package com.myblogstory.blog.repository;

import com.myblogstory.blog.model.Role;
import com.myblogstory.blog.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Подключение модуля SpringJPA для {@link Role} class
 * @author Н.Черненко
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(Roles name);
}
