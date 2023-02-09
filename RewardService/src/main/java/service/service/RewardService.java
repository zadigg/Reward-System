package service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.domain.Reward;
import service.repository.RewardRepository;
import java.util.List;
import java.util.Optional;

@Service
public class RewardService {
    @Autowired
    RewardRepository rewardRepository;

    public void addReward(Reward reward){
        rewardRepository.save(reward);
    }

    public Reward getReward(String rewardId){
        Optional<Reward> reward = rewardRepository.findById(rewardId);
        return reward.orElse(null);
    }

    public List<Reward> getAllRewards(){
        return rewardRepository.findAll();
    }

    public boolean updateReward( String rewardId, Reward reward) {
        Optional<Reward> existingReward = rewardRepository.findById(rewardId);
        if (existingReward.isEmpty()) {
            return false;
        }
        reward.setRewardId(rewardId);
        Reward updatedReward = rewardRepository.save(reward);
        return true;
    }

    public boolean deleteReward(String rewardId){
        try {
            rewardRepository.deleteById(rewardId);
            return true;
        } catch (Exception e){
            return false;
        }

    }
}

