package com.spring.bloggerclone.repository;

import com.spring.bloggerclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>
{
    Optional<User> findByUsername(String username);
}
