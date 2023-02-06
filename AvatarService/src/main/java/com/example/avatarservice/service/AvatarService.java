package com.example.avatarservice.service;

import com.example.avatarservice.domain.Avatar;
import com.example.avatarservice.repository.AvatarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvatarService implements IAvatarService {
    @Autowired
    private AvatarRepo avatarRepo;
    @Override
    public Avatar addAvatar(Avatar avatar) {
        return avatarRepo.save(avatar);
    }

    @Override
    public Avatar updateAvatar(Avatar avatar) {
        Optional<Avatar> avatar1= avatarRepo.findById(avatar.getId());
        if(avatar1.isPresent()){
            Avatar avatar2= avatar1.get();
            avatar2.setBody(avatar.getHead());
            avatar2.getHair();
            avatar2.getEye();
            avatar2.getEyebrow();
            avatar2.getNose();
            avatar2.getMouth();
            avatar2.getEars();
            avatar2.getBody();
            avatar2.getHat();
            avatar2.getTop();
            avatar2.getTopColor();
            avatar2.getHatColor();
            return avatarRepo.save(avatar2);
        }
        else {
            return avatarRepo.save(avatar);
        }

    }

    @Override
    public void deleteAvatar(Long id) {
        avatarRepo.deleteById(id);
    }

    @Override
    public Avatar getAvatar(Long id) {
        Optional<Avatar> avatar= avatarRepo.findById(id);
        if(avatar.isPresent()){
            return avatar.get();
        }
        else {
            return null;
        }
    }

    @Override
    public List<Avatar> getAllAvatar() {
        return avatarRepo.findAll();
    }
}
