package com.hzyazilimci.blog.security;

import com.hzyazilimci.blog.business.constants.messages.BusinessMessages;
import com.hzyazilimci.blog.core.utilities.exceptions.BusinessException;
import com.hzyazilimci.blog.dataAccess.UserDao;
import com.hzyazilimci.blog.entities.sourceEntities.Role;
import com.hzyazilimci.blog.entities.sourceEntities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailManager implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public CustomUserDetailManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOremail) throws UsernameNotFoundException {
        User user = this.userDao.findByUsernameOrEmail(usernameOremail, usernameOremail)
                .orElseThrow(() -> new BusinessException(BusinessMessages.UserExceptionMessages.USER_NOT_FOUND + ": "+ usernameOremail));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),mapRolesToAuthorize(user.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorize(Set<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}
