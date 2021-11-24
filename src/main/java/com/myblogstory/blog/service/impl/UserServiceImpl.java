package com.myblogstory.blog.service.impl;

import com.myblogstory.blog.model.Role;
import com.myblogstory.blog.model.Roles;
import com.myblogstory.blog.model.User;
import com.myblogstory.blog.repository.RoleRepository;
import com.myblogstory.blog.repository.UserRepository;
import com.myblogstory.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    //    private  final UserMapper mapper;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
//        final User user = mapper.map(dto);
//        final User result = userRepository.save(user);
//        user = userRepository.findByEmail(user.getEmail());
//
//        if (user == null) {
//            return new User();
//        }
//
//        Set<Role> role = new HashSet<>();
//        role.add(roleRepository.findByName(Roles.ROLE_USER));
//        user.setRoles(role);
//
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
