package com.myblogstory.blog.config.jwt;


import com.myblogstory.blog.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static io.jsonwebtoken.lang.Strings.hasText;

/**
 * Класс фильтр
 * @author Н.Черненко
 */

@Log
@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION = "Authorization";
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                           FilterChain filterChain) throws IOException, ServletException {
        logger.info("сделать фильтр ...");
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null && jwtProvider.validateToken(token)) {
            String userEmail = jwtProvider.getEmailFromToken(token);
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userEmail);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
