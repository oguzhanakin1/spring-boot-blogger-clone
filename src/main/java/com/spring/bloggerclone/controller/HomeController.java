package com.spring.bloggerclone.controller;

import com.spring.bloggerclone.response.PostResponse;
import com.spring.bloggerclone.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class HomeController
{
    @Autowired
    private IPostService postService;

    @GetMapping("/home")
    public ResponseEntity<List<PostResponse>> examplePostsForHomePage()
    {
        return ResponseEntity.ok(postService.examplePostsForHomePage());
    }
}
