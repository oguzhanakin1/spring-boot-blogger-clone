package com.spring.bloggerclone.repository;

import com.spring.bloggerclone.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long>
{
    RefreshToken findByUserId(Long userId);
}
