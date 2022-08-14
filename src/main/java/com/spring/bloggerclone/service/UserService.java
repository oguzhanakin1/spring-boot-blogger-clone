package com.spring.bloggerclone.service;

import com.spring.bloggerclone.model.User;
import com.spring.bloggerclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
   private PasswordEncoder passwordEncoder;

    protected User getCurrentUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(()-> new UsernameNotFoundException("username not found"));
    }

    @Override
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

   @Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUserId(Long userId)
    {
        return userRepository.getById(userId);
    }

    @Override
    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserCreateTime(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId)
    {
        User user = userRepository.getById(userId);
        User accountOwner = getCurrentUser();
        if( accountOwner == user)
        {
            userRepository.deleteById(userId);
        }
        else
            throw new RuntimeException("You can't delete this account!!!");
    }

    @Override
    public User editUser(Long userId, User user)
    {
        User userToEdit = userRepository.getById(userId);
        User accountOwner = getCurrentUser();
        if(accountOwner == userToEdit)
        {
            userToEdit.setFirstName(user.getFirstName());
            userToEdit.setLastName(user.getLastName());
            userToEdit.setUsername(user.getUsername());
            userToEdit.setEmail(user.getEmail());
            userToEdit.setProfilePhoto(user.getProfilePhoto());
            userToEdit.setPassword(user.getPassword());
            return userRepository.save(userToEdit);
        }
        else
            throw new RuntimeException("You can't edit this profile!!!");
    }
}
