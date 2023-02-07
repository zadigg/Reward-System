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
    public Avatar updateAvatar(Long avatarId ,Avatar avatar) {
        Optional<Avatar> oldAvatar= avatarRepo.findById(avatarId);
        if (oldAvatar.isPresent()){
            Avatar updatedAvatar = avatarRepo.save(avatar);
            return updatedAvatar;
        }

        else {
            return avatarRepo.save(avatar);
        }

    }

    @Override
    public boolean deleteAvatar(Long id) {
        try{
            avatarRepo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public Avatar getAvatarById(Long avatarId) {
        Optional<Avatar> avatar= avatarRepo.findById(avatarId);
       return avatar.orElse(null);
    }

    @Override
    public List<Avatar> getAllAvatars() {
        return avatarRepo.findAll();
    }
}
