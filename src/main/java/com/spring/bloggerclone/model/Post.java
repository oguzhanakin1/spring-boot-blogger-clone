package com.spring.bloggerclone.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "post_photo",columnDefinition = "TEXT")
    private String postPhoto;

    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Column(name = "post_description", nullable = false)
    private String postDescription;

    @Column(name = "post_body",columnDefinition = "TEXT", nullable = false)
    private String postBody;

    @Column(name="post_create_time")
    private LocalDateTime postCreateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Like> likes;
}
