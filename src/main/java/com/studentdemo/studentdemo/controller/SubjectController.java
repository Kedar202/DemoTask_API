package com.studentdemo.studentdemo.controller;

import com.studentdemo.studentdemo.model.SubjectData;
import com.studentdemo.studentdemo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping("/addSubject")
    public ResponseEntity<?> addSubject (@RequestBody SubjectData subjectData){

        return new ResponseEntity<>(subjectService.addSubject(subjectData), HttpStatus.OK);
    }

    @PostMapping("/updateSubject")
    public ResponseEntity<?> updateSubject (@RequestParam Integer subjectId , @RequestBody SubjectData subjectData){

        try {
            return new ResponseEntity<>(subjectService.updateSubject(subjectId,subjectData), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/deleteSubject")
    public ResponseEntity<?> deleteSubject (@RequestParam Integer subjectId ){

        try {
            return new ResponseEntity<>(subjectService.deleteSubject(subjectId), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/findSubjectById")
    public ResponseEntity<?> findSubjectById (@RequestParam Integer subjectId ){

        try {
            return new ResponseEntity<>(subjectService.findSubjectById(subjectId), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/findAllSubject")
    public ResponseEntity<?> findAllSubject (){

        try {
            return new ResponseEntity<>(subjectService.findAllSubject(), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
