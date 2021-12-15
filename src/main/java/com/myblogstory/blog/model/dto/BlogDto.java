package com.myblogstory.blog.model.dto;

import com.myblogstory.blog.model.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BlogDto {

    private String name;
    private String text;
    private LocalDate data;
    private User user;
}
