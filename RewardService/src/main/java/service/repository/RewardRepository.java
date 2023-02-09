package service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import service.domain.Reward;

public interface RewardRepository extends MongoRepository<Reward, String> {
}
