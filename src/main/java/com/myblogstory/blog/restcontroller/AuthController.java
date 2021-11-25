package com.myblogstory.blog.restcontroller;

import com.myblogstory.blog.model.User;
import com.myblogstory.blog.model.dto.UserDto;
import com.myblogstory.blog.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class AuthController {

    private final UserServiceImpl userServiceImpl;
    private final AuthenticationManager authenticationManager;

    /**
     * Сохранить пользователя
     * @param userDto
     * @return
     */
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addEmployee(@RequestBody @Valid UserDto userDto) {
        return userServiceImpl.saveUser(userDto);
    }

    /**
     * Авторизация пользователя
     */
    @PostMapping("/authorization")
    public User userLogin(@Valid @RequestBody User user) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (User) authentication;
    }
}
