package com.myblogstory.blog.restcontroller;

import com.myblogstory.blog.config.jwt.JwtProvider;
import com.myblogstory.blog.config.jwt.AuthResponse;
import com.myblogstory.blog.model.User;
import com.myblogstory.blog.model.dto.UserDto;
import com.myblogstory.blog.security.CustomUserDetails;
import com.myblogstory.blog.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    private final JwtProvider jwtProvider;

    /**
     * Сохранить пользователя
     * @param userDto
     * @return
     */
    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserDto userDto) {
        return userServiceImpl.saveUser(userDto);
    }

    /**
     * Авторизация пользователя
     */
    @PostMapping("/authorization")
    public ResponseEntity<?> userLogin(@Valid @RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateJwtToken(authentication);
        CustomUserDetails userBean = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userBean.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setRoles(roles);
        return ResponseEntity.ok(authResponse);
    }
}
