package com.studentdemo.studentdemo.controller;

import com.studentdemo.studentdemo.dto.MarkReqDTO;
import com.studentdemo.studentdemo.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/marks")
public class MarksController {

    @Autowired
    MarksService marksService;

    @PostMapping("/addMarks")
    public ResponseEntity<?> addMarks (@RequestBody MarkReqDTO markDto){

        return new ResponseEntity<>(marksService.addMarks(markDto), HttpStatus.OK);
    }
    @PostMapping("/updateMarks")
    public ResponseEntity<?> updateMarks (@RequestParam Integer markId ,  @RequestBody MarkReqDTO markDto){

        try {
            return new ResponseEntity<>(marksService.updateMarks(markId,markDto), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/deleteMarks")
    public ResponseEntity<?> deleteMarks (@RequestParam Integer markId ){

        try {
            return new ResponseEntity<>(marksService.deleteMarks(markId), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/findMarkById")
    public ResponseEntity<?> findMarkById (@RequestParam Integer markId ){

        try {
            return new ResponseEntity<>(marksService.findMarkById(markId), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/findAllMarks")
    public ResponseEntity<?> findAllMarks (){

        try {
            return new ResponseEntity<>(marksService.findAllMarks(), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping ("/getMarksByStudentId")
    public ResponseEntity<?> getMarksByStudentId (@RequestParam("studentId") Integer studentId){

        return new ResponseEntity<>(marksService.getMarksByStudentId(studentId), HttpStatus.OK);
    }
}
