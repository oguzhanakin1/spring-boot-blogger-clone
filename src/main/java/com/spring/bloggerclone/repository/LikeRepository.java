package com.spring.bloggerclone.repository;

import com.spring.bloggerclone.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long>
{
}
