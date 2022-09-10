package com.spring.bloggerclone.response;

import com.spring.bloggerclone.model.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse
{
    Long id;
    String postTitle;
    String postDescription;
    String postBody;
    LocalDateTime postCreateTime;
    Long userId;
    String firstName;
    String lastName;
    String profilePhoto;
    LocalDateTime userCreateTime;

    public PostResponse(Post entity)
    {
        this.id = entity.getId();
        this.postTitle = entity.getPostTitle();
        this.postDescription = entity.getPostDescription();
        this.postBody = entity.getPostBody();
        this.postCreateTime = entity.getPostCreateTime();
        this.userId = entity.getUser().getId();
        this.firstName = entity.getUser().getFirstName();
        this.lastName = entity.getUser().getLastName();
        this.profilePhoto = entity.getUser().getProfilePhoto();
        this.userCreateTime = entity.getUser().getUserCreateTime();

    }
}
