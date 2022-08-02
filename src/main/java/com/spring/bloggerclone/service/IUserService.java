package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService
{
    List<User> findAll();

    Optional<User> findByUsername(String username);

    User saveUser(User user);

}
