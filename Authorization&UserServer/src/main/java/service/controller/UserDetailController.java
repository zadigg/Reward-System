package service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import service.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import service.service.UserDetailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/")
public class UserDetailController {

    @Autowired
    UserDetailService userDetailService;


    @PostMapping("/addUser")
    public ResponseEntity<User> addUserDetail(@RequestBody User user) {
            userDetailService.addUser(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId){
        User user = userDetailService.getUserName(userId);
        if(user==null){
            return new ResponseEntity<String>("User with userId= "+userId+" not found in the database.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userId) {
        Boolean userExist = userDetailService.updateUserId(userId, user);
        if(userExist) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No user found with " + user.getName() + " id", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> removeUser( @PathVariable String userId ) {
        Boolean isExist =  userDetailService.removeUser(userId);
        if(isExist) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No userID " + userId + "found", HttpStatus.NOT_FOUND);
        }

    }

}
