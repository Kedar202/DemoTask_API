package com.studentdemo.studentdemo.controller;

import com.studentdemo.studentdemo.dto.TeacherReqDto;
import com.studentdemo.studentdemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/createTeacher")
    public ResponseEntity<?> createTeacher (@RequestBody TeacherReqDto teacherReqDto){

        return new ResponseEntity<>(teacherService.createTeacher(teacherReqDto), HttpStatus.OK);
    }

    @GetMapping("/getStudentByTeacherId")
    public ResponseEntity<?> getStudentByTeacherId (@RequestParam("teacherId") Integer teacherId){

        return new ResponseEntity<>(teacherService.getStudentByTeacherId(teacherId), HttpStatus.OK);
    }

    @PostMapping("/updateTeacher")
    public ResponseEntity<?> updateTeacher (@RequestParam Integer teacherId , @RequestBody TeacherReqDto teacherReqDto){

        try {
            return new ResponseEntity<>(teacherService.updateTeacher(teacherId,teacherReqDto), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/deleteTeacher")
    public ResponseEntity<?> deleteTeacher (@RequestParam Integer teacherId ){

        try {
            return new ResponseEntity<>(teacherService.deleteTeacher(teacherId), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/findTeacherById")
    public ResponseEntity<?> findTeacherById (@RequestParam Integer teacherId ){

        try {
            return new ResponseEntity<>(teacherService.findTeacherById(teacherId), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/findAllTeacher")
    public ResponseEntity<?> findAllGroups (){

        try {
            return new ResponseEntity<>(teacherService.findAllTeacher(), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
