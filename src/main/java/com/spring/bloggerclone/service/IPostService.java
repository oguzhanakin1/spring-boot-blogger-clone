package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.Post;

import java.util.List;

public interface IPostService
{
    List<Post> showAllPosts();

    Post createPost(Post post);

    void deletePost(Long postId);

    Post findByPostId(Long postId);

    Post editPost(Long postId, Post post);
}
