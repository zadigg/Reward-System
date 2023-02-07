package com.edu.miu.TeacherService.service;

import com.edu.miu.TeacherService.domain.Teacher;
import com.edu.miu.TeacherService.integration.EmailMessage;
import com.edu.miu.TeacherService.integration.EmailPublisher;
import com.edu.miu.TeacherService.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final EmailPublisher emailPublisher;
    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);

        // publish email
        EmailMessage message = new EmailMessage(teacher.getContact().getEmail(), "Welcome to our school!",
                "Dear " + teacher.getFirstName() + ",\n\nWelcome to our school! We're glad you joined us.\n\nBest regards,\nSchool Team");

        emailPublisher.publish("newTopic",message);
    }

    public Teacher getTeacher(String teacherId){
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        return teacher.orElse(null);
    }

    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    public boolean updateTeacher( String teacherId, Teacher teacher) {
        Optional<Teacher> oldTeacher = teacherRepository.findById(teacherId);
        if (oldTeacher.isEmpty()) {
            return false;
        }
        teacher.setTeacherId(teacherId);
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return true;
    }

    public boolean deleteTeacher(String teacherId){
        try {
            teacherRepository.deleteById(teacherId);
            return true;
        } catch (Exception e){
            return false;
        }

    }
}

