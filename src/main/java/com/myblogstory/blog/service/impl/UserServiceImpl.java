package com.myblogstory.blog.service.impl;

//import com.myblogstory.blog.mapper.UserMapper;

import com.myblogstory.blog.model.Role;
import com.myblogstory.blog.model.User;
import com.myblogstory.blog.model.dto.UserDto;
import com.myblogstory.blog.repository.UserRepository;
import com.myblogstory.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * Имплементация {@link UserService} interface
 * @author Н.Черненко
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

//    private  final UserMapper mapper;

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<String> saveUser(UserDto userDto) {
        User user = new User();

        User existingUser = this.findByUserName(userDto.getEmail());
        if (existingUser != null) {
            throw new UsernameNotFoundException ("Данные данного пользователя уже существуют");
        }

        user.setUserName(userDto.getUserName());
        user.setLastName(userDto.getLastName());
        user.setPatronymic(userDto.getPatronymic());
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        User addUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(addUser.getId()).toUri();
        ResponseEntity.created(location).body(addUser);
        return ResponseEntity.ok("Пользователь успешно зарегистрировался");
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getByIdUser(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void updateUser(User user) {
        User userDB = userRepository.findById(user.getId()).orElseThrow();
        userRepository.save(userDB);
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
        throw new UsernameNotFoundException("Пользователь не найден");
    }

    /**
     * (findByUserName and findByEmailAndPassword) - методы необходимые для UserDetailsService
     */

    @Override
    public User findByUserName(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = findByUserName(email);
        if (user != null) {
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
