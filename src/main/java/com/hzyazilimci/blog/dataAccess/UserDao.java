package com.hzyazilimci.blog.dataAccess;

import com.hzyazilimci.blog.entities.sourceEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByUsernameOrEmail(String username, String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    User findById(int id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
