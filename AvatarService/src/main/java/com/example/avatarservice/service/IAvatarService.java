package com.example.avatarservice.service;

import com.example.avatarservice.domain.Avatar;
import com.example.avatarservice.domain.Avatars;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IAvatarService {
    public Boolean addAvatar(Avatar avatar, Long studentNumber);

    public Boolean addAvatarToStudent(Long studentNumber, String elementType);
    public Avatar updateAvatar(Long id,Avatar avatar);
    public boolean deleteAvatar(Long studentNumber, @PathVariable String elementType);
    public Avatar getAvatarById(Long id);
    public Avatars getAllAvatars();

}
