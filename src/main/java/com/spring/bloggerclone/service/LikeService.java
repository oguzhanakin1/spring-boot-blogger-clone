package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.Like;
import com.spring.bloggerclone.model.Post;
import com.spring.bloggerclone.model.User;
import com.spring.bloggerclone.repository.LikeRepository;
import com.spring.bloggerclone.repository.PostRepository;
import com.spring.bloggerclone.repository.UserRepository;
import com.spring.bloggerclone.response.LikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService extends BaseService implements ILikeService
{
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Like createLike(Like like, Long postId)
    {
        User currentUser = getCurrentUser();
        like.setUser(currentUser);
        currentUser.getLikes().add(like);
        Post post = postRepository.findById(postId).orElse(null);
        like.setPost(post);
        post.getLikes().add(like);
        return likeRepository.save(like);

    }

    @Override
    public List<LikeResponse> showAllLikes()
    {
        List<Like> list = likeRepository.findAll();
        return list.stream().map(l -> {
            return new LikeResponse(l);
        }).collect(Collectors.toList());
    }

    @Override
    public List<LikeResponse> showPostsLikesByPostId(Long postId)
    {

        Post post = postRepository.findById(postId).orElseThrow(null);
        List<Like> list = post.getLikes();
        return list.stream().map(l -> {
            return new LikeResponse(l);
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteLike(Long likeId)
    {
        likeRepository.deleteById(likeId);
    }
}
