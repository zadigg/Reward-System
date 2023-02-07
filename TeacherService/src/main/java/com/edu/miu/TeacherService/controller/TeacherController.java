package com.edu.miu.TeacherService.controller;

import com.edu.miu.TeacherService.domain.Teacher;
import com.edu.miu.TeacherService.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final SchoolFeignClient schoolFeignClient;

    @PostMapping
    public ResponseEntity<?> addTeacher(@RequestBody Teacher teacher){
        // First, check whether the school is in database or not
        try{
            ResponseEntity<?> isSchoolExist = schoolFeignClient.isSchoolExist(teacher.getSchool().getSchoolId());
            teacherService.addTeacher(teacher);
            return new ResponseEntity<Teacher>(teacher, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<String>("School with Id= "+teacher.getSchool().getSchoolId()+" isn't found.", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<?> getTeacher(@PathVariable String teacherId){
        Teacher teacher = teacherService.getTeacher(teacherId);
        if(teacher==null){
            return new ResponseEntity<String>("Teacher with Id= "+teacherId+" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllTeachers(){
        List<Teacher> teachers = teacherService.getAllTeachers();
        if(teachers.size()==0){
            return new ResponseEntity<String>("No Teacher Found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
    }
    @PutMapping("/{teacherId}")
    public ResponseEntity<?> updateTeacher(@PathVariable String teacherId, @RequestBody Teacher teacher) {
        boolean isUpdated = teacherService.updateTeacher(teacherId, teacher);
        if(!isUpdated){
            return new ResponseEntity<String>("Teacher with id= "+teacherId+" isn't found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String teacherId) {
        boolean isDeleted = teacherService.deleteTeacher(teacherId);
        if(!isDeleted){
            return new ResponseEntity<>("Teacher with Id= "+teacherId+" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Teacher with id= "+teacherId+" is deleted.", HttpStatus.OK);
    }

}

@FeignClient(name = "SchoolService")
interface SchoolFeignClient{
    @GetMapping("/schools/{schoolId}")
    public ResponseEntity<?> isSchoolExist(@PathVariable String schoolId);

}

