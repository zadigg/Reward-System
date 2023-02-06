package com.example.avatarservice.service;

import com.example.avatarservice.domain.Avatar;

import java.util.List;

public interface IAvatarService {
    public Avatar addAvatar(Avatar avatar);
    public Avatar updateAvatar(Avatar avatar);
    public void deleteAvatar(Long id);
    public Avatar getAvatar(Long id);
    public List<Avatar> getAllAvatar();

}
