package com.myblogstory.blog.config;

import com.myblogstory.blog.security.UserDetailsSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

/**
 * Class конфигурации по настройке прав доступа и хешированию пароля
 * @author Н.Черненко
 */

@EnableWebSecurity
@RequiredArgsConstructor
public class MyBlogStoryConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsSecurity userDetailsSecurity;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsSecurity);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsSecurity).passwordEncoder(passwordEncoder());
    }

    /**
     * @return зашифрованный пароль( сложность кодировки ("bcrypt" = 5))
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//      http .exceptionHandling()
//                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/");
//    }
}
