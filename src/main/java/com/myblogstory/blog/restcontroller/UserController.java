package com.myblogstory.blog.restcontroller;

import com.myblogstory.blog.model.User;
import com.myblogstory.blog.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для регистрации и авторизации пользователя
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    /**
     * Сохранить пользователя
     * @param user
     * @return
     */
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public User addEmployee(@RequestBody @Valid User user) {
        return userServiceImpl.saveUser(user);
//        public UserDto addEmployee(@RequestBody @Valid UserDto userDto) {
//            return userServiceImpl.saveUser(userDto);
    }

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
