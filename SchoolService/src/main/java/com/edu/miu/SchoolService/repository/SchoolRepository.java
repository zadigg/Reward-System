package com.edu.miu.SchoolService.repository;

import com.edu.miu.SchoolService.domain.School;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchoolRepository extends MongoRepository<School, String> {
}
