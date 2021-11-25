package com.myblogstory.blog.service.impl;

//import com.myblogstory.blog.mapper.UserMapper;
import com.myblogstory.blog.model.Role;
import com.myblogstory.blog.model.Roles;
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
    public UserDto saveUser(UserDto userDto) {

        UserDto returnValue = new UserDto();
        User users = new User();

        //Проверка существует ли пользователь
        User existingUser = this.findByUserName(userDto.getEmail());

        if (existingUser != null) {
            throw new UsernameNotFoundException ("Данные данного пользователя уже существуют");
        }

        //Создать безопасный пароль
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        BeanUtils.copyProperties(userDto, users);

        //Просвоение роли
        Set<Role> roles = new HashSet<>();
        String[] roleArr = userDto.getRoles();

        if(roleArr == null) {
            roles.add(roleRepository.findByName(Roles.ROLE_USER));
        }
        users.setRoles(roles);

        //Запись данных в БД
//        final User user = mapper.map(userDto);
        final User result = userRepository.save(users);
        BeanUtils.copyProperties(result, returnValue);
        return returnValue;
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
}
