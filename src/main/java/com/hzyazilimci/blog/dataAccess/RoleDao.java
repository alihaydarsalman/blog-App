package com.hzyazilimci.blog.dataAccess;

import com.hzyazilimci.blog.entities.sourceEntities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role,Integer> {

    Optional<Role> findRoleByRoleName(String rolename);
    Optional<Role> findByRoleName(String roleName);

    boolean existsByRoleName(String rolename);
}
