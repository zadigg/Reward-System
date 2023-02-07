package com.example.avatarservice.controller;

import com.example.avatarservice.domain.Avatar;
import com.example.avatarservice.service.AvatarService;
import com.example.avatarservice.service.IAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    @Autowired
    private IAvatarService iAvatarService;

    @PostMapping("/add-avatars")
    public ResponseEntity<?> addAvatar(@RequestBody Avatar avatar){
        iAvatarService.addAvatar(avatar);
        return new ResponseEntity<>(avatar, HttpStatus.CREATED);
    }

    @PutMapping("/{avatarId}")
    public ResponseEntity<?> updateAvatar(@PathVariable long avatarId, @RequestBody Avatar avatar){
        return ResponseEntity.ok(iAvatarService.updateAvatar(avatar));
    }

    @DeleteMapping("/remove/{avatarId}")
    public ResponseEntity<?> deleteAvatar(@PathVariable Long avatarId){
        iAvatarService.deleteAvatar(avatarId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getAllAvatar(){
        iAvatarService.getAllAvatar();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAvatarById(@PathVariable long id){
        iAvatarService.getAvatar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
