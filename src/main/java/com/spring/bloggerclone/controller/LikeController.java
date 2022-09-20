package com.spring.bloggerclone.controller;

import com.spring.bloggerclone.request.LikeCreateRequest;
import com.spring.bloggerclone.response.LikeResponse;
import com.spring.bloggerclone.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/likes")
public class LikeController
{
    @Autowired
    ILikeService likeService;

    @PostMapping
    public ResponseEntity<?> createLike(LikeCreateRequest likeCreateRequest)
    {
        return new ResponseEntity<>(likeService.createLike(likeCreateRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LikeResponse>> showAllLikes()
    {
        return ResponseEntity.ok(likeService.showAllLikes());
    }

    @GetMapping("{postId}")
    public ResponseEntity<List<LikeResponse>> showPostsLikesByPostId(@RequestParam Long postId)
    {
        return ResponseEntity.ok(likeService.showPostsLikesByPostId(postId));
    }

    @DeleteMapping("{likeId}")
    public ResponseEntity<?> deleteLikeByLikeId(@RequestParam Long likeId)
    {
        likeService.deleteLike(likeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
