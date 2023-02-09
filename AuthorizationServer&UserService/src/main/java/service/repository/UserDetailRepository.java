package service.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import service.domain.User;
import java.util.List;
@Repository
    public interface UserDetailRepository extends MongoRepository<User, String> {
        List<User> findByRole(String role);
    }

