package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.Post;
import com.spring.bloggerclone.model.User;
import com.spring.bloggerclone.repository.PostRepository;
import com.spring.bloggerclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PostService implements IPostService
{
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Post> showAllPosts()
    {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(Post post)
    {
        post.setPostCreateTime(LocalDateTime.now());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(()-> new UsernameNotFoundException("username not found"));
        post.setUser(user);
        Map<Long,Post> usersPosts = user.getPosts();
        usersPosts.put(post.getId(),post);

        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId)
    {
        postRepository.getById(postId).getComments().clear();
        postRepository.deleteById(postId);
    }

    @Override
    public Post findByPostId(Long postId)
    {
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("There is no such post..."));
    }

    @Override
    public Post editPost(Long postId, Post post)
    {
        Post postToEdit = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("There is no such post..."));

        postToEdit.setPostTitle(post.getPostTitle());

        postToEdit.setPostBody(post.getPostBody());

        return postRepository.save(postToEdit);
    }
}
