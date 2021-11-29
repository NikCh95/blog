package com.myblogstory.blog.security;

import com.myblogstory.blog.model.User;
import com.myblogstory.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Подключение {@link UserDetailsService} interface для настройки SpringSecurity
 * @author Н.Черненко
 */

@Service
@RequiredArgsConstructor
public class UserDetailsSecurity implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' не найден", userName));
        }

        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
