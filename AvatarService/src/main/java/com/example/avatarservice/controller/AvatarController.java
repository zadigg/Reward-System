package com.example.avatarservice.controller;

import com.example.avatarservice.domain.Avatar;
import com.example.avatarservice.service.AvatarService;
import com.example.avatarservice.service.IAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    @Autowired
    private IAvatarService iAvatarService;

    @PostMapping("/add-avatars")
    public ResponseEntity<Avatar> createAvatar(@RequestBody Avatar avatar){
        Avatar createdAvatar = iAvatarService.addAvatar(avatar);
        return ResponseEntity.ok(createdAvatar);
    }

    @PutMapping("/{avatarId}")
    public ResponseEntity<Avatar> updateAvatar(@PathVariable Long avatarId, @RequestBody Avatar avatar){
        Avatar updatedAvatar = iAvatarService.updateAvatar(avatarId,avatar);
        return ResponseEntity.ok(updatedAvatar);
    }

    @DeleteMapping("/remove/{avatarId}")
    public ResponseEntity<String> deleteAvatar(@PathVariable Long avatarId){
        boolean isDeleted= iAvatarService.deleteAvatar(avatarId);
        if (!isDeleted){
            return new ResponseEntity<>("Avatar with id= " +avatarId+" not found.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Avatar with id= "+avatarId+" is successfully deleted.",HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllAvatar(){
        List<Avatar> avatars = iAvatarService.getAllAvatars();
        if(avatars.size()==0){
            return new ResponseEntity<String>("There is no Avatar in the database.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Avatar>>(avatars, HttpStatus.OK);
    }

    @GetMapping("/{avatarId}")
    public ResponseEntity<?> getAvatarById(@PathVariable Long avatarId){
        Avatar avatar = iAvatarService.getAvatarById(avatarId);
        if (avatar==null){
            return new ResponseEntity<String>("Avatar with id= "+avatarId+" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Avatar>(avatar, HttpStatus.OK);
    }

}
