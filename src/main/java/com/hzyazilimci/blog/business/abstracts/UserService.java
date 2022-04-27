package com.hzyazilimci.blog.business.abstracts;

import com.hzyazilimci.blog.core.utilities.results.Result;
import com.hzyazilimci.blog.entities.requests.create.CreateUserRequest;
import com.hzyazilimci.blog.entities.requests.loginRequest.LoginRequest;
import com.hzyazilimci.blog.entities.requests.update.UpdateUserRequest;

public interface UserService {

   Result signUp(CreateUserRequest createUserRequest);
   Result logIn(LoginRequest loginRequest);
   Result updateUser(UpdateUserRequest updateUserRequest);
}
