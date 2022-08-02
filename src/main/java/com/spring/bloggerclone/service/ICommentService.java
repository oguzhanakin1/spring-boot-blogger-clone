package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.Comment;

public interface ICommentService
{
    Comment createComment(Comment comment, Long postId);
}
