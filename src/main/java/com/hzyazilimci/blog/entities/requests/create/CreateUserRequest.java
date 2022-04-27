package com.hzyazilimci.blog.entities.requests.create;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
}
