package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.Post;
import com.spring.bloggerclone.response.PostResponse;

import java.util.List;

public interface IPostService
{
    List<PostResponse> showAllPosts();

    List<PostResponse> examplePostsForHomePage();

    Post createPost(Post post);

    void deletePost(Long postId);

    PostResponse findByPostId(Long postId);

    Post editPost(Long postId, Post post);
}
