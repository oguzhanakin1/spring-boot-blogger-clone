package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.RefreshToken;
import com.spring.bloggerclone.model.User;

public interface IAuthenticationService
{
    String signInAndReturnJWT(User signInRequest);

    String refreshToken(RefreshToken token);
}
