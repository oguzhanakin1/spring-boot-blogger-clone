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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class PostService implements IPostService
{
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    protected User getCurrentUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(()-> new UsernameNotFoundException("username not found"));
    }

    @Override
    public List<Post> showAllPosts()
    {
        return postRepository.findAll();
    }

    @Override
    public List<Post> examplePostsForHomePage()
    {
        List<Post> allPosts = postRepository.findAll();
        List<Post> examplePosts = new ArrayList<>();
        for(int i = 0; i < 5 && i < allPosts.size(); i++)
        {
            Random random = new Random();
            int a = random.nextInt(allPosts.size());
            examplePosts.add(allPosts.get(a));
        }
        return examplePosts;
    }

    @Override
    public Post createPost(Post post)
    {
        User currentUser = getCurrentUser();
        post.setPostCreateTime(LocalDateTime.now());
        post.setUser(currentUser);
        List<Post> usersPosts = currentUser.getPosts();
        usersPosts.add(post);
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId)
    {
        User postOwner = postRepository.getById(postId).getUser();
        User currentUser = getCurrentUser();
        if(currentUser == postOwner)
        {
            postRepository.deleteById(postId);
        }
        else
            throw new RuntimeException("You can't delete this post!!!");
    }

    @Override
    public Post findByPostId(Long postId)
    {
        return postRepository.getById(postId);
    }

    @Override
    public Post editPost(Long postId, Post post)
    {
        User postOwner = postRepository.getById(postId).getUser();
        User currentUser = getCurrentUser();
        if(currentUser == postOwner)
        {
            Post postToEdit = postRepository.getById(postId);

            postToEdit.setPostTitle(post.getPostTitle());

            postToEdit.setPostBody(post.getPostBody());

            postToEdit.setPostPhoto(post.getPostPhoto());

            return postRepository.save(postToEdit);
        }
        else
            throw new RuntimeException("You can't edit this post");
    }
}
