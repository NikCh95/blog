package com.myblogstory.blog.service.impl;

import com.myblogstory.blog.model.Blog;
import com.myblogstory.blog.repository.BlogRepository;
import com.myblogstory.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Имплементация {@link BlogService} interface
 * @author Н.Черненко
 */

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Override
    public Blog saveBlog(Blog blog) {
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
        blogRepository.getById(id);
    }

    @Override
    public void updateBlog(Blog blog) {
        Blog blogs = blogRepository.findById(blog.getId()).orElseThrow();
        blogRepository.save(blogs);
    }
}
