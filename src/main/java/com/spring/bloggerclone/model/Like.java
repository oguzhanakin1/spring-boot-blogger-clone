package com.spring.bloggerclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Autowired
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)
    @JsonIgnore
    private Post post;

    @Autowired
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;
}
