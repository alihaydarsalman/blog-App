package com.hzyazilimci.blog.entities.dtos.blogDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPostDto {

    private int id;
    private String title;
    private String description;
    private String content;
    private LocalDate postDate;
}
