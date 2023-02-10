package com.example.avatarservice.controller;

import com.example.avatarservice.domain.Avatar;
import com.example.avatarservice.domain.Avatars;
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


    @PostMapping("/add-avatars/{studentNumber}")
    public ResponseEntity<?> createAvatar(@RequestBody Avatar avatar, @PathVariable Long studentNumber){
        Boolean createdAvatar = iAvatarService.addAvatar(avatar, studentNumber);
        if (!createdAvatar){
            return new ResponseEntity<String>("No " + studentNumber + " element found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Avatar>(avatar, HttpStatus.CREATED);
    }

    @PutMapping("/{avatarId}")
    public ResponseEntity<Avatar> updateAvatar(@PathVariable Long avatarId, @RequestBody Avatar avatar){
        Avatar updatedAvatar = iAvatarService.updateAvatar(avatarId,avatar);
        return ResponseEntity.ok(updatedAvatar);
    }

    @DeleteMapping("/remove/{studentNumber}/{elementType}")
    public ResponseEntity<String> deleteAvatar(@PathVariable Long studentNumber, @PathVariable String elementType){
        boolean isDeleted= iAvatarService.deleteAvatar(studentNumber, elementType);
        if (!isDeleted){
            return new ResponseEntity<>("Avatar for studentNumber " +studentNumber+" not found.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("element with type= "+elementType+" is successfully deleted.",HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllAvatar(){
        Avatars avatars = iAvatarService.getAllAvatars();
        if (avatars==null){
            return new ResponseEntity<String>("No avatars found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Avatars>(avatars, HttpStatus.OK);
    }

    @GetMapping("/{avatarId}")
    public ResponseEntity<?> getAvatarById(@PathVariable Long avatarId){
        Avatar avatar = iAvatarService.getAvatarById(avatarId);
        if (avatar==null){
            return new ResponseEntity<String>("Avatar with id= "+avatarId+" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Avatar>(avatar, HttpStatus.OK);
    }

    @PutMapping("/add-element/{studentNumber}/{elementType}")
    public ResponseEntity<?> addAvatarToStudent(@PathVariable Long studentNumber, @PathVariable String elementType){
        Boolean addedAvatar = iAvatarService.addAvatarToStudent(studentNumber, elementType);
        if (!addedAvatar){
            return new ResponseEntity<String>("No " + elementType + " element found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Avatar>(HttpStatus.CREATED);
    }

}
