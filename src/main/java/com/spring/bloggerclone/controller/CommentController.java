package com.spring.bloggerclone.controller;

import com.spring.bloggerclone.model.Comment;
import com.spring.bloggerclone.service.ICommentService;
import com.spring.bloggerclone.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/comments")
public class CommentController
{
    @Autowired
    private ICommentService commentService;

    private IPostService postService;

    @PostMapping("{postId}")
    public ResponseEntity<?> createPost(@RequestBody Comment comment, @PathVariable Long postId)
    {
        return new ResponseEntity<>(commentService.createComment(comment, postId), HttpStatus.CREATED);
    }
}
