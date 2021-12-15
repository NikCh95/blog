package com.myblogstory.blog.service.impl;

import com.myblogstory.blog.model.Blog;
import com.myblogstory.blog.model.dto.BlogDto;
import com.myblogstory.blog.repository.BlogRepository;
import com.myblogstory.blog.repository.UserRepository;
import com.myblogstory.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Имплементация {@link BlogService} interface
 *
 * @author Н.Черненко
 */

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Override
    public Blog saveBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        //Проверка записи
        Blog existingBlog = this.findByName(blogDto.getName());
        if (existingBlog == null) {
            throw new UsernameNotFoundException("Данные отсутствуют");
        }
        blog.setName(blogDto.getName());
        blog.setText(blogDto.getText());
        blog.setData(LocalDate.now());
        return blogRepository.save(blog);
    }


    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getById(Long id) {
        return blogRepository.getById(id);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.findById(id);
        blogRepository.deleteById(id);
    }

    @Override
    public void updateBlog(Blog blog) {
        Blog blogs = blogRepository.findById(blog.getId()).orElseThrow();
        blogRepository.save(blogs);
    }

    public Blog findByName(String name) {
        return blogRepository.findByName(name);
    }
}
