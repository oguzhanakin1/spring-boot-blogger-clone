package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService
{
    List<User> findAll();

    Optional<User> findByUsername(String username);

    User findByUserId(Long userId);

    User saveUser(User user);

    void deleteUser(Long userId);

    User editUser(Long userId, User user);

    User changePassword(Long userId, String password);
}
