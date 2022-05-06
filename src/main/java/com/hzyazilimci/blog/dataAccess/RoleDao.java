package com.hzyazilimci.blog.dataAccess;

import com.hzyazilimci.blog.entities.sourceEntities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role,Integer> {

    Optional<Role> findRoleByRoleName(String rolename);
    Optional<Role> findByRoleName(String roleName);

    boolean existsByRoleName(String rolename);
}
