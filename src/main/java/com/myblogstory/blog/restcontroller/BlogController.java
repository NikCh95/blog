package com.myblogstory.blog.restcontroller;


import com.myblogstory.blog.model.Blog;
import com.myblogstory.blog.service.impl.BlogServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Контроллер для работы с дынными базы данных, идентичен {@link UserController} class
 * @author Н.Черненко
 */

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BlogController {

    private final BlogServiceImpl blogService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog addBlog(@RequestBody Blog blog) {
        return blogService.saveBlog(blog);
    }

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.findAll();
    }

    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable("id") Long id) {
        return blogService.getById(id);
    }

    @PutMapping("/upDateBlog")
    public ResponseEntity<String> updateBlog(@RequestBody Blog blog) {
        try {
            blogService.updateBlog(blog);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        try {
            blogService.deleteBlog(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
