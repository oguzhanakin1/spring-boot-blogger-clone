package com.spring.bloggerclone.response;

import com.spring.bloggerclone.model.Post;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
    List<LikeResponse> postsLikes;

    public PostResponse(Post entity, List<LikeResponse> likes)
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
        this.postsLikes = likes;
    }
}
