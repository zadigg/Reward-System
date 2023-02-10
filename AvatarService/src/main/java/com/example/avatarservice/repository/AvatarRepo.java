package com.example.avatarservice.repository;

import com.example.avatarservice.domain.Avatar;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvatarRepo extends MongoRepository<Avatar, Long> {

}
