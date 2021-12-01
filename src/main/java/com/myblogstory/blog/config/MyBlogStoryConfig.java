package com.myblogstory.blog.config;

import com.myblogstory.blog.config.jwt.JwtAuthenticationEntryPoint;
import com.myblogstory.blog.config.jwt.JwtFilter;
import com.myblogstory.blog.security.UserDetailsSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class конфигурации по настройке прав доступа и хешированию пароля
 *
 * @author Н.Черненко
 */

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyBlogStoryConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsSecurity userDetailsSecurity;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsSecurity).passwordEncoder(passwordEncoder());
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

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests().antMatchers("/auth/registration", "/auth/authorization").permitAll()
                .antMatchers(HttpMethod.GET, "/user/**").permitAll();

        http.addFilterBefore(jwtFilter(),UsernamePasswordAuthenticationFilter.class);
    }
}
