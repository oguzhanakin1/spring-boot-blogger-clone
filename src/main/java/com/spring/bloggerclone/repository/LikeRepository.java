package com.spring.bloggerclone.repository;

import com.spring.bloggerclone.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long>
{
    @Query(value = " select l.id, l.user_id, l.post_id From likes l where l.user_id =:userId and l.post_id=:postId", nativeQuery = true)
    Like findLikeByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);

}
