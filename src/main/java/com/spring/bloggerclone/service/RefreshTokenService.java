package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.RefreshToken;
import com.spring.bloggerclone.model.User;
import com.spring.bloggerclone.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService implements IRefreshTokenService
{
    @Value("${authentication.jwt.expiration-in-ms}")
    Long tokenExpireTimeInSeconds;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public String createNewToken(User user)
    {
        RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
        if(token == null)
        {
            token = new RefreshToken();
            token.setUser(user);
        }
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(tokenExpireTimeInSeconds)));
        refreshTokenRepository.save(token);
        return token.getToken();
    }

    @Override
    public boolean isRefreshExpired(RefreshToken token)
    {
        return token.getExpiryDate().before(new Date());
    }

    @Override
    public RefreshToken getByUser(Long userId)
    {
        return refreshTokenRepository.findByUserId(userId);
    }
}
