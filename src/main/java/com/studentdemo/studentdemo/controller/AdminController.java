package com.studentdemo.studentdemo.controller;

import com.studentdemo.studentdemo.dto.StudentReqDTO;
import com.studentdemo.studentdemo.model.UserEntity;
import com.studentdemo.studentdemo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/registration")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/registerUser")
    public ResponseEntity<?> userRegistration(@RequestBody UserEntity user) {
        try {
            return new ResponseEntity<>(adminService.saveUser(user), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
