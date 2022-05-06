package com.hzyazilimci.blog.business.concretes;

import com.hzyazilimci.blog.business.abstracts.UserService;
import com.hzyazilimci.blog.business.constants.messages.BusinessMessages;
import com.hzyazilimci.blog.core.utilities.exceptions.BusinessException;
import com.hzyazilimci.blog.core.utilities.mapping.ModelMapperService;
import com.hzyazilimci.blog.core.utilities.results.Result;
import com.hzyazilimci.blog.core.utilities.results.SuccessResult;
import com.hzyazilimci.blog.dataAccess.RoleDao;
import com.hzyazilimci.blog.dataAccess.UserDao;
import com.hzyazilimci.blog.entities.requests.userRequests.CreateUserRequest;
import com.hzyazilimci.blog.entities.requests.userRequests.LoginRequest;
import com.hzyazilimci.blog.entities.requests.userRequests.UpdateUserRequest;
import com.hzyazilimci.blog.entities.sourceEntities.Role;
import com.hzyazilimci.blog.entities.sourceEntities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Collections;

@Service
public class UserManager implements UserService {

    private final ModelMapperService modelMapperService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserManager(@Lazy ModelMapperService modelMapperService,
                       @Lazy AuthenticationManager authenticationManager,
                       @Lazy PasswordEncoder passwordEncoder,
                       @Lazy UserDao userDao, @Lazy RoleDao roleDao) {
        this.modelMapperService = modelMapperService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }
    @Override
    public Result logIn(LoginRequest loginRequest) {

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOremail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new SuccessResult(BusinessMessages.GlobalSuccessMessages.SUCCESS_LOG_IN);
    }
    @Override
    public Result signUp(CreateUserRequest createUserRequest) {

        isUserExistsByEmail(createUserRequest.getEmail());
        isUserExistsByUserName(createUserRequest.getUsername());

        Role role = this.roleDao.findByRoleName("ROLE_USER").get();

        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        user.setId(0);
        user.setRoles(Collections.singleton(role));
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        userDao.save(user);

        return new SuccessResult(BusinessMessages.GlobalSuccessMessages.SUCCESS_REGISTER);
    }

    @Override
    public Result updateUser(UpdateUserRequest updateUserRequest) {

        isUserExistById(updateUserRequest.getId());
        isUserExistsByUserName(updateUserRequest.getUsername());
        isUserExistsByUserName(updateUserRequest.getEmail());

        User user = this.userDao.findById(updateUserRequest.getId());
        user.setId(updateUserRequest.getId());
        user.setName(updateUserRequest.getName());
        user.setLastname(updateUserRequest.getLastname());
        user.setUsername(updateUserRequest.getUsername());
        user.setEmail(updateUserRequest.getEmail());
        user.setRegisterDate(LocalDate.now());
        user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));

        this.userDao.save(user);

        return new SuccessResult(BusinessMessages.GlobalSuccessMessages.SUCCESS_UPDATE);
    }

    @Override
    public User getUserById(int userId){
        isUserExistById(userId);

        return this.userDao.findById(userId);
    }

    private void isUserExistById(int id){
        if (!userDao.existsById(id)){
            throw new BusinessException(BusinessMessages.UserExceptionMessages.USER_NOT_FOUND);
        }
    }

    private void isUserExistsByEmail(String email){
        if (userDao.existsByEmail(email)){
            throw new BusinessException(BusinessMessages.UserExceptionMessages.EMAIL_ALREADY_EXISTS);
        }
    }

    private void isUserExistsByUserName(String userName){
        if (userDao.existsByUsername(userName)){
            throw new BusinessException(BusinessMessages.UserExceptionMessages.USERNAME_EXISTS);
        }
    }
}
