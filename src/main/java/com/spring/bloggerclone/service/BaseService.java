package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.User;
import com.spring.bloggerclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BaseService
{
    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(()-> new UsernameNotFoundException("username not found"));
    }
}
