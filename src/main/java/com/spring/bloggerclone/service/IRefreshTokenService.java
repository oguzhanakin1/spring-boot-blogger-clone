package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.RefreshToken;
import com.spring.bloggerclone.model.User;

public interface IRefreshTokenService
{
    String createNewToken(User user);

    boolean isRefreshExpired(RefreshToken token);

    RefreshToken getByUser(Long userId);
}
