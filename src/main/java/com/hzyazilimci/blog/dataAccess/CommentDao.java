package com.hzyazilimci.blog.dataAccess;

import com.hzyazilimci.blog.entities.sourceEntities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {

    Comment findById(int comId);

    List<Comment> findAllByPost_Id(int postId);
}
