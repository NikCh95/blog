package com.myblogstory.blog.restcontroller;

import com.myblogstory.blog.config.jwt.JwtProvider;
import com.myblogstory.blog.model.User;
import com.myblogstory.blog.model.dto.UserDto;
import com.myblogstory.blog.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Контроллер для регистрации и авторизации пользователя
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserServiceImpl userServiceImpl;
    private final JwtProvider jwtProvider;

    /**
     * Сохранить пользователя
     * @param registrationRequest
     * @return
     */
    @PostMapping("/registration")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        User user = new User();
        user.setPassword(registrationRequest.getPassword());
        user.setEmail(registrationRequest.getEmail());
        user.setUserName(registrationRequest.getUserName());
        userServiceImpl.saveUser(user);
        return "OK";
    }

    /**
     * Авторизация пользователя
     */
    @PostMapping("/authorization")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = userServiceImpl.findByEmailAndPassword(request.getEmail(), request.getPassword());
        String token = jwtProvider.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
