package com.spring.bloggerclone.request;

import lombok.Data;

@Data
public class LikeCreateRequest
{
    Long id;
    Long userId;
    Long postId;
}
