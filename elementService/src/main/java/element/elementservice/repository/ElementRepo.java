package element.elementservice.repository;

import element.elementservice.domain.Element;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ElementRepo extends MongoRepository<Element, String> {
    List<Element> findByType(String type);
}
