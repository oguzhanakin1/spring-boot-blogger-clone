package com.spring.bloggerclone.repository;

import com.spring.bloggerclone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long>
{
}
