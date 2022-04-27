package com.hzyazilimci.blog.dataAccess;

import com.hzyazilimci.blog.entities.sourceEntities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostDao extends JpaRepository<Post,Integer> {

    boolean existsByTitle(String title);

    Post findById(int id);
}
