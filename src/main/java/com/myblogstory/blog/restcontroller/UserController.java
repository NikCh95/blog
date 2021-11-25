package com.myblogstory.blog.restcontroller;

import com.myblogstory.blog.model.User;
import com.myblogstory.blog.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    /**
     * Найти всех пользователей
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userServiceImpl.findAll();
    }

    /**
     * Найти пользователя по id
     * @param id
     */
    @GetMapping("/{id}")
    public User getByUserId(@PathVariable("id") Long id) {
        return userServiceImpl.getByIdUser(id);
    }

    /**
     * Удалить пользователя
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
