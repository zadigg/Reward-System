package com.example.elementservice.repository;

import com.example.elementservice.domain.Element;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElementRepository extends MongoRepository<Element, String> {
    Optional<Element> findByType(String type);
}
