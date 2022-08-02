package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.User;

public interface IAuthenticationService
{
    String signInAndReturnJWT(User signInRequest);
}
