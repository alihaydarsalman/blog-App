package com.hzyazilimci.blog.api.controllers;

import com.hzyazilimci.blog.business.abstracts.UserService;
import com.hzyazilimci.blog.core.utilities.results.Result;
import com.hzyazilimci.blog.entities.requests.userRequests.CreateUserRequest;
import com.hzyazilimci.blog.entities.requests.userRequests.LoginRequest;
import com.hzyazilimci.blog.entities.requests.userRequests.UpdateUserRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signUp")
    public Result signUp(@RequestBody CreateUserRequest createUserRequest){
        return this.userService.signUp(createUserRequest);
    }
    @PostMapping("/logIn")
    public Result logIn(@RequestBody @Valid LoginRequest loginRequest){
        return this.userService.logIn(loginRequest);
    }

    @PutMapping("/updateUserFields")
    public Result updateUser(@RequestBody UpdateUserRequest updateUserRequest){
        return this.userService.updateUser(updateUserRequest);
    }
}
