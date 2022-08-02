package com.spring.bloggerclone.repository;

import com.spring.bloggerclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long>
{

}
