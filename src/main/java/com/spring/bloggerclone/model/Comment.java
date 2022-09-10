package com.spring.bloggerclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "comment_body",columnDefinition = "TEXT")
    private String commentBody;

    @Column(name = "comment_create_time")
    private LocalDateTime commentCreateTime;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
