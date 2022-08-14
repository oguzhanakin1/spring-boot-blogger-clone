package com.spring.bloggerclone.controller;

import com.spring.bloggerclone.model.Comment;
import com.spring.bloggerclone.model.Post;
import com.spring.bloggerclone.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/comments")
public class CommentController
{
    @Autowired
    private ICommentService commentService;

    @PostMapping("{postId}")
    public ResponseEntity<?> createComment(@RequestBody Comment comment, @PathVariable Long postId)
    {
        return new ResponseEntity<>(commentService.createComment(comment, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId)
    {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{commentId}")
    public ResponseEntity<?> updatePost(@PathVariable Long commentId,
                                        @RequestBody Comment comment)
    {
        return ResponseEntity.ok(commentService.updateComment(commentId, comment));
    }
}
