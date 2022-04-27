package com.hzyazilimci.blog.entities.dtos.blogDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDto {

    private int id;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private LocalDate registerDate;
    private List<GetRoleDto> roles;
}
