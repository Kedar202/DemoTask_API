package com.studentdemo.studentdemo.controller;

import com.studentdemo.studentdemo.dto.StudentReqDTO;
import com.studentdemo.studentdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;


    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent (@RequestBody StudentReqDTO studentReqDTO){
        return new ResponseEntity<>(studentService.addStudent(studentReqDTO), HttpStatus.OK);
    }

    @PostMapping("/updateStudent")
    public ResponseEntity<?> updateStudent (@RequestParam Integer studentId ,  @RequestBody StudentReqDTO studentReqDTO){

        try {
            return new ResponseEntity<>(studentService.updateStudent(studentId,studentReqDTO), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/deleteStudent")
    public ResponseEntity<?> deleteStudent (@RequestParam Integer studentId ){

        try {
            return new ResponseEntity<>(studentService.deleteStudent(studentId), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/findStudentById")
    public ResponseEntity<?> findStudentById (@RequestParam Integer studentId ){

        try {
            return new ResponseEntity<>(studentService.findStudentById(studentId), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/findAllStudents")
    public ResponseEntity<?> findAllStudents (){

        try {
            return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
