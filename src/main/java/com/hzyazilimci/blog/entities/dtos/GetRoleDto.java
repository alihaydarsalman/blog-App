package com.hzyazilimci.blog.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoleDto {

    private int id;
    private String roleName;
}
