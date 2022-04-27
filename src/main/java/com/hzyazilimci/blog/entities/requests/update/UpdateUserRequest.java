package com.hzyazilimci.blog.entities.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private int id;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
}
