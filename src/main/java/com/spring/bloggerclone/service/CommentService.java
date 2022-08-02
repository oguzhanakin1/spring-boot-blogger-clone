package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.Comment;
import com.spring.bloggerclone.model.Post;
import com.spring.bloggerclone.model.User;
import com.spring.bloggerclone.repository.CommentRepository;
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
public class CommentService implements ICommentService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Long postId)
    {
        comment.setCommentCreateTime(LocalDateTime.now());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(()-> new UsernameNotFoundException("username not found"));
        comment.setUser(user);
        Map<Long,Comment> usersComments = user.getComments();
        usersComments.put(comment.getId(),comment);
        Post post = postRepository.getById(postId);
        comment.setPost(post);
        Map<Long,Comment> postsComments = post.getComments();
        postsComments.put(comment.getId(),comment);
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, Long postId)
    {
        Post post = postRepository.getById(postId);

    }
}
