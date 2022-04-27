package com.hzyazilimci.blog.business.abstracts;

import com.hzyazilimci.blog.core.utilities.results.Result;
import com.hzyazilimci.blog.entities.requests.userRequests.CreateUserRequest;
import com.hzyazilimci.blog.entities.requests.userRequests.LoginRequest;
import com.hzyazilimci.blog.entities.requests.userRequests.UpdateUserRequest;

public interface UserService {

   Result signUp(CreateUserRequest createUserRequest);
   Result logIn(LoginRequest loginRequest);
   Result updateUser(UpdateUserRequest updateUserRequest);
}
