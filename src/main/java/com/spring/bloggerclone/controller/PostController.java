package com.spring.bloggerclone.controller;

import com.spring.bloggerclone.model.Post;
import com.spring.bloggerclone.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/posts")
public class PostController
{
    @Autowired
    private IPostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post)
    {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    @PostMapping("{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId,
                                        @RequestBody Post post)
    {
        return ResponseEntity.ok(postService.editPost(postId, post));
    }

    @GetMapping("/home")
    public ResponseEntity<List<Post>> examplePostsForHomePage()
    {
        return ResponseEntity.ok(postService.examplePostsForHomePage());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> findAll()
    {
        return ResponseEntity.ok(postService.showAllPosts());
    }

    @GetMapping("{postId}")
    public ResponseEntity<?> findPostByPostId(@PathVariable Long postId)
    {
        return ResponseEntity.ok(postService.findByPostId(postId));
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePostByPostId(@PathVariable Long postId)
    {

        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
