package com.hzyazilimci.blog.api.controllers;


import com.hzyazilimci.blog.business.abstracts.UserService;
import com.hzyazilimci.blog.core.utilities.results.Result;
import com.hzyazilimci.blog.entities.requests.create.CreateUserRequest;
import com.hzyazilimci.blog.entities.requests.loginRequest.LoginRequest;
import com.hzyazilimci.blog.entities.requests.update.UpdateUserRequest;
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


/*private AuthenticationManager authenticationManager;
    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserDao userDao, RoleDao roleDao,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> authenticateUser(@RequestBody @Valid LoginRequest loginRequest){
        Authentication authentication =  this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOremail(),
                        loginRequest.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(BusinessMessages.GlobalSuccessMessages.SUCCESS_SIGN_IN, HttpStatus.OK);
    }

    @PostMapping("/singUp")
    public ResponseEntity<?> registerUser(@RequestBody @Valid CreateUserRequest createUserRequest){
        if (userDao.existsByUsername(createUserRequest.getUsername())){
            return new ResponseEntity<>(BusinessMessages.UserExceptionMessages.USERNAME_EXISTS, HttpStatus.BAD_REQUEST);
        }
        if (userDao.existsByEmail(createUserRequest.getEmail())){
            return new ResponseEntity<>(BusinessMessages.UserExceptionMessages.EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(createUserRequest.getName());
        user.setLastname(createUserRequest.getLastname());
        user.setUsername(createUserRequest.getUsername());
        user.setEmail(createUserRequest.getEmail());
        user.setRegisterDate(LocalDate.now());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        Role roles = roleDao.findByRoleName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userDao.save(user);

        return new ResponseEntity<>(BusinessMessages.GlobalSuccessMessages.SUCCESS_REGISTER,HttpStatus.OK);
    }*/