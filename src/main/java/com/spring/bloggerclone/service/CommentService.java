package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.Comment;
import com.spring.bloggerclone.model.Post;
import com.spring.bloggerclone.model.User;
import com.spring.bloggerclone.repository.CommentRepository;
import com.spring.bloggerclone.repository.PostRepository;
import com.spring.bloggerclone.repository.UserRepository;
import com.spring.bloggerclone.response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService extends BaseService implements ICommentService
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
        User currentUser = getCurrentUser();
        comment.setCommentCreateTime(LocalDateTime.now());
        comment.setUser(currentUser);
        List<Comment> usersComments = currentUser.getComments();
        usersComments.add(comment);
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post not found!!"));
        comment.setPost(post);
        List<Comment> postsComments = post.getComments();
        postsComments.add(comment);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId)
    {
        User currentUser = getCurrentUser();
        Comment currentComment = commentRepository.getById(commentId);
        Post commentedPost = currentComment.getPost();
        User postOwner = commentedPost.getUser();
        User commentOwner = commentRepository.getById(commentId).getUser();
        if(currentUser == postOwner || currentUser == commentOwner)
        {
            commentRepository.deleteById(commentId);
        }
        else
            throw new RuntimeException("You can't delete this comment!!!");
    }

    @Override
    public Comment updateComment(Long commentId, Comment comment)
    {
        User currentUser = getCurrentUser();
        Comment commentToEdit = commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("comment not found"));
        User commentOwner = commentToEdit.getUser();
        if(currentUser == commentOwner)
        {
            commentToEdit.setCommentBody(comment.getCommentBody());
            return commentRepository.save(commentToEdit);
        }
        else
            throw new RuntimeException("You can't update this comment!!!");
    }

    @Override
    public List<Comment> showAllComments()
    {
        return commentRepository.findAll();
    }


    @Override
    public List<CommentResponse> findByPostId(Long postId)
    {
        Post post = postRepository.findById(postId).orElse(null);
        List<Comment> comments = post.getComments();
        return comments.stream().map(p -> {
            return new CommentResponse(p);
        }).collect(Collectors.toList());
    }

    @Override
    public Comment findByCommentId(Long commentId)
    {
        return commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("comment not found"));
    }

}
