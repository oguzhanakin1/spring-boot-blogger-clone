package com.spring.bloggerclone.repository;

import com.spring.bloggerclone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment,Long>
{
    /*@Query("From Comments Delete * where posts.postId=:postId")
    public void deleteAllCommentsByPostId(@Param("postId") Long postId);*/
}
