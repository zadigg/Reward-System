package com.edu.miu.UserService.controller;

import com.edu.miu.UserService.domain.LoginRequest;
import com.edu.miu.UserService.domain.User;
import com.edu.miu.UserService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        User user = userService.getUser(request.getUsername());
        if (user == null) {
            System.out.println("Login error: Incorrect username or password");
            return new ResponseEntity<>("Login error: Incorrect username or password", HttpStatus.BAD_REQUEST);
        }

        if (!userService.checkPassword(user, request.getPassword())) {
            System.out.println("Login error: Incorrect username or password");
            return new ResponseEntity<>("Login error: Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
        System.out.println("Logged in as "+user.getRole()+".");
        return new ResponseEntity<>("Logged in as "+user.getRole()+".", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        if(savedUser==null){
            return new ResponseEntity<String>("User with Username= "+user.getUsername()+" is already exist.\nTry agan!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllSchools(){
        List<User> users = userService.getAllSchools();
        if(users.size()==0){
            return new ResponseEntity<String>("No School Found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        User user = userService.getUser(username);
        if(user==null){
            return new ResponseEntity<String>("User with Username= "+username+" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateTeacher(@PathVariable String username, @RequestBody User user) {
        User updateUser= userService.updateUser(username, user);
        if(updateUser==null){
            return new ResponseEntity<String>("User with Username= "+username+" isn't found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String username) {
        boolean isDeleted = userService.deleteUser(username);
        if(!isDeleted){
            return new ResponseEntity<>("School with Id= "+username+" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("School with Id= "+username+" is deleted.", HttpStatus.OK);
    }
}

