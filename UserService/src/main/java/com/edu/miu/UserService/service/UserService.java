package com.edu.miu.UserService.service;

import com.edu.miu.UserService.domain.User;
import com.edu.miu.UserService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User addUser(User user) {
        User alreadyExist = getUser(user.getUsername());
        if(alreadyExist!=null){
            return null;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User getUser(String username) {
        return userRepository.findById(username).orElse(null);
    }

    public User updateUser(String id, User user) {
        User existingUser = getUser(user.getUsername());
        if (existingUser == null) {
            return null;
        }
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        existingUser.setRole(user.getRole());
        userRepository.save(existingUser);
        return existingUser;
    }
    public boolean deleteUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    public List<User> getAllSchools(){
        return userRepository.findAll();
    }

    public boolean checkPassword(User user, String password) {
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }
}

