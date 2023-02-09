package service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.domain.User;
import service.repository.UserDetailRepository;
import java.util.Optional;

@Service
public class UserDetailService {

    @Autowired
    UserDetailRepository userDetailRepository;

    public void addUser(User user) {
        userDetailRepository.save(user);
    }
    public User getUserName(String userID){
        Optional<User> user = userDetailRepository.findById(userID);
        return user.orElse(null);
    }

    public boolean updateUserId( String userId, User user) {
        Optional<User> existingUserId = userDetailRepository.findById(userId);
        if (existingUserId.isEmpty()) {
            return false;
        }
        existingUserId.get().setName(user.getName());
        existingUserId.get().setRole(user.getRole());
        existingUserId.get().setPassword(user.getPassword());
        userDetailRepository.save(existingUserId.get());
        return true;
    }

    public boolean removeUser(String userId){
        try {
            userDetailRepository.deleteById(userId);
            return true;
        } catch (Exception e){
            return false;
        }

    }


}
