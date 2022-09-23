package com.spring.bloggerclone.service;


import com.spring.bloggerclone.model.Like;
import com.spring.bloggerclone.response.LikeResponse;

import java.util.List;

public interface ILikeService
{
    Like createLike(Long postId);

    List<LikeResponse> showAllLikes();

    List<LikeResponse> showPostsLikesByPostId(Long postId);


    void deleteLike(Long likeId);
}
