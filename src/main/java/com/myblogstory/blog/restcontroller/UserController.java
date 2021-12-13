package com.myblogstory.blog.restcontroller;

import com.myblogstory.blog.model.User;
import com.myblogstory.blog.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    /**
     * Найти всех пользователей
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userServiceImpl.findAllUsers();
    }

    /**
     * Найти пользователя по id
     */
    @GetMapping(value = "/{id}")
    public User getByUserId(@PathVariable("id") Long id) {
        return userServiceImpl.getByIdUser(id);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        try {
            userServiceImpl.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удалить пользователя
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
