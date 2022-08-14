package com.spring.bloggerclone.controller;

import com.spring.bloggerclone.model.Post;
import com.spring.bloggerclone.model.User;
import com.spring.bloggerclone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController
{
    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll()
    {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("{username}")
    public ResponseEntity<Optional<User>> findByUsername(@PathVariable String username)
    {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable Long userId)
    {
        return ResponseEntity.ok(userService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user)
    {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUserByUserId(@PathVariable Long userId)
    {

        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId,
                                        @RequestBody User user)
    {
        return ResponseEntity.ok(userService.editUser(userId,user));
    }
}