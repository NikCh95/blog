package com.myblogstory.blog.service;

import com.myblogstory.blog.model.User;
import com.myblogstory.blog.model.dto.UserDto;

import java.util.List;

/**
 * Методы по работе с БД
 * @author Н.Черненко
 */

public interface UserService {

    /**
     * @return всех пользователей
     */
    List<User> findAll();

    /**
     * @param user - user identification
     * @return сохраненного пользователя
     */
    User saveUser(User user);

//    UserDto saveUser(UserDto user);

    /**
     * @param id - user identification
     * @return пользователя по id
     */
    User getByIdUser(Long id);

    /**
     * удалить пользователя
     * @param id - user identification
     */
    void deleteUser(Long id);

    /**
     *
     * @param userName - user identification
     * @return пользователя по имени
     */
    User findByUserName(String userName);

}
