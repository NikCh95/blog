package com.myblogstory.blog.restcontroller;


import com.myblogstory.blog.model.Blog;
import com.myblogstory.blog.model.dto.BlogDto;
import com.myblogstory.blog.repository.BlogRepository;
import com.myblogstory.blog.service.impl.BlogServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Контроллер для работы с дынными базы данных, идентичен {@link AuthController} class
 *
 * @author Н.Черненко
 */

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BlogController {

    private final BlogServiceImpl blogService;
    private final BlogRepository blogRepository;

    /**
     * Добавить статью в базу данных
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Blog> addBlog(@RequestBody @Valid BlogDto blogDto) {
        Blog blog =  blogService.saveBlog(blogDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
                .buildAndExpand(blog.getId()).toUri();
        return ResponseEntity.created(location).body(blog);
    }

    @GetMapping
    @ResponseBody
    public List<Blog> getAllPhotos() {
        return blogService.findAll();
    }
}