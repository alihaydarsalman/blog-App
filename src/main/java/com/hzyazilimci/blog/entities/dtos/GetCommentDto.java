package com.hzyazilimci.blog.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentDto {

    private int id;
    private String name;
    private String email;
    private String commentBody;
    private LocalDate commentDate;
}
