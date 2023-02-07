package com.edu.miu.TeacherService.repository;

import com.edu.miu.TeacherService.domain.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher, String> {
}
