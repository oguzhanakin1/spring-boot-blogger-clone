package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.Post;
import com.spring.bloggerclone.response.PostResponse;

import java.util.List;

public interface IPostService
{
    List<PostResponse> showAllPosts();

    List<Post> examplePostsForHomePage();

    Post createPost(Post post);

    void deletePost(Long postId);

    Post findByPostId(Long postId);

    Post editPost(Long postId, Post post);
}
