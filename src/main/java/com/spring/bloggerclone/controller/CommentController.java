package com.spring.bloggerclone.controller;

import com.spring.bloggerclone.model.Comment;
import com.spring.bloggerclone.response.CommentResponse;
import com.spring.bloggerclone.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
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

    @PostMapping("/edit/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId,
                                        @RequestBody Comment comment)
    {
        return ResponseEntity.ok(commentService.updateComment(commentId, comment));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> findAll()
    {
        return ResponseEntity.ok(commentService.showAllComments());
    }

    @GetMapping("{commentId}")
    public ResponseEntity<?> findCommentByCommentId(@PathVariable Long commentId)
    {
        return ResponseEntity.ok(commentService.findByCommentId(commentId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponse>> findCommentByPostId(@PathVariable Long postId)
    {
        return ResponseEntity.ok(commentService.findByPostId(postId));
    }

}
