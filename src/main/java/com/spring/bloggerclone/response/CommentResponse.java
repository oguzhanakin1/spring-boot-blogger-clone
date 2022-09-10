package com.spring.bloggerclone.response;

import com.spring.bloggerclone.model.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse
{
    Long id;
    String commentBody;
    LocalDateTime commentCreateTime;
    Long userId;
    String firstName;
    String lastName;
    String profilePhoto;
    LocalDateTime userCreateTime;
    Long postId;

    public CommentResponse(Comment entity)
    {
        this.id = entity.getId();
        this.commentBody = entity.getCommentBody();
        this.commentCreateTime = entity.getCommentCreateTime();
        this.userId = entity.getUser().getId();
        this.firstName = entity.getUser().getFirstName();
        this.lastName = entity.getUser().getLastName();
        this.profilePhoto = entity.getUser().getProfilePhoto();
        this.userCreateTime = entity.getUser().getUserCreateTime();
        this.postId = entity.getPost().getId();
    }
}
