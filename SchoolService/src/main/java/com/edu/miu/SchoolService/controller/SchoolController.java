package com.edu.miu.SchoolService.controller;


import com.edu.miu.SchoolService.domain.School;
import com.edu.miu.SchoolService.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schools")
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping
    public ResponseEntity<?> addTeacher(@RequestBody School school){
        schoolService.addSchool(school);
        return new ResponseEntity<School>(school, HttpStatus.CREATED);
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<?> getSchool(@PathVariable String schoolId){
        School school = schoolService.getSchool(schoolId);
        if(school==null){
            return new ResponseEntity<String>("Teacher with Id= "+schoolId+" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<School>(school, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllSchools(){
        List<School> schools = schoolService.getAllSchools();
        if(schools.size()==0){
            return new ResponseEntity<String>("No School Found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<School>>(schools, HttpStatus.OK);
    }
    @PutMapping("/{schoolId}")
    public ResponseEntity<?> updateTeacher(@PathVariable String schoolId, @RequestBody School school) {
        boolean isUpdated = schoolService.updateSchool(schoolId, school);
        if(!isUpdated){
            return new ResponseEntity<String>("School with Id= "+schoolId+" isn't found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<School>(school, HttpStatus.OK);
    }

    @DeleteMapping("/{schoolId}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String schoolId) {
        boolean isDeleted = schoolService.deleteSchool(schoolId);
        if(!isDeleted){
            return new ResponseEntity<>("School with Id= "+schoolId+" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("School with Id= "+schoolId+" is deleted.", HttpStatus.OK);
    }
}

