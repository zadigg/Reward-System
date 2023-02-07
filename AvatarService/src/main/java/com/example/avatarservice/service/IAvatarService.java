package com.example.avatarservice.service;

import com.example.avatarservice.domain.Avatar;

import java.util.List;

public interface IAvatarService {
    public Avatar addAvatar(Avatar avatar);
    public Avatar updateAvatar(Long id,Avatar avatar);
    public boolean deleteAvatar(Long id);
    public Avatar getAvatarById(Long id);
    public List<Avatar> getAllAvatars();

}
