package com.myblogstory.blog.service.impl;

//import com.myblogstory.blog.mapper.UserMapper;
import com.myblogstory.blog.model.Role;
import com.myblogstory.blog.model.User;
import com.myblogstory.blog.model.dto.UserDto;
import com.myblogstory.blog.repository.RoleRepository;
import com.myblogstory.blog.repository.UserRepository;
import com.myblogstory.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Имплементация {@link UserService} interface
 * @author Н.Черненко
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
//
//    private  final UserMapper mapper;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        User users = new User();

        //Проверка существует ли пользователь
        User existingUser = this.findByUserName(user.getEmail());

        if (existingUser != null) {
            throw new UsernameNotFoundException ("Данные данного пользователя уже существуют");
        }

        //Создать безопасный пароль
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        BeanUtils.copyProperties(user, users);

        //Просвоение роли
        Role userRole = roleRepository.findByName("ROLE_USER");
        users.setRoles(userRole);

        //Запись данных в БД
//        final User user = mapper.map(userDto);
        return  userRepository.save(users);
    }

    @Override
    public User getByIdUser(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
        throw new UsernameNotFoundException("Пользователь не найден");
    }

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
