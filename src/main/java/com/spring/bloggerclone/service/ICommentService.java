package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.Comment;
import com.spring.bloggerclone.response.CommentResponse;

import java.util.List;

public interface ICommentService
{
    Comment createComment(Comment comment, Long postId);

    void deleteComment(Long commentId);

    Comment updateComment(Long commentId, Comment comment);

    List<Comment> showAllComments();

    List<CommentResponse> findByPostId(Long postId);

    Comment findByCommentId(Long commentId);

}
