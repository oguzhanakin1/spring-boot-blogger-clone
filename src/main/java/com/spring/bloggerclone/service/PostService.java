package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.Post;
import com.spring.bloggerclone.model.User;
import com.spring.bloggerclone.repository.PostRepository;
import com.spring.bloggerclone.repository.UserRepository;
import com.spring.bloggerclone.response.LikeResponse;
import com.spring.bloggerclone.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class PostService extends BaseService implements IPostService
{
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ILikeService likeService;

    @Override
    public List<PostResponse> showAllPosts()
    {
       List<Post> list = postRepository.findAll();
       return list.stream().map(p -> {
           List<LikeResponse> likes = likeService.showPostsLikesByPostId(p.getId());
           return new PostResponse(p,likes);
       }).collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> examplePostsForHomePage()
    {
        List<Post> allPosts = postRepository.findAll();
        List<Post> examplePosts = new ArrayList<>();
        for(int i = 0; i < 6 && i < allPosts.size(); i++)
        {
            Random random = new Random();
            int a = random.nextInt(allPosts.size());
            examplePosts.add(allPosts.get(a));
        }
        return examplePosts.stream().map(p -> {
            List<LikeResponse> likes = likeService.showPostsLikesByPostId(p.getId());
            return new PostResponse(p,likes);
        }).collect(Collectors.toList());
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
    public PostResponse findByPostId(Long postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("post not found"));
        List<LikeResponse> likes = likeService.showPostsLikesByPostId(postId);
        return new PostResponse(post,likes);
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
