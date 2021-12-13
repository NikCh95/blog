package com.myblogstory.blog.service;

import com.myblogstory.blog.model.User;
import com.myblogstory.blog.model.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Методы по работе с БД
 * @author Н.Черненко
 */

public interface UserService {

    /**
     * @return всех пользователей
     */
    List<User> findAllUsers();

    /**
     * @param userDto - user identification
     * @return сохраненного пользователя
     */
    ResponseEntity<String> saveUser(UserDto userDto);

    /**
     * @param id - user identification
     * @return пользователя по id
     */
    User getByIdUser(Long id);

    /**
     *  Изменить данные пользователя
     */
    void updateUser(User user);

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

    User findByEmailAndPassword(String email, String password);

}
