package com.studentdemo.studentdemo.controller;

import com.studentdemo.studentdemo.model.GroupDetails;
import com.studentdemo.studentdemo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/group")
public class GroupConroller {

    @Autowired
    GroupService groupService;

    @PostMapping("/addGroup")
    public ResponseEntity<?> addStudent (@RequestBody GroupDetails groupDetails){

        return new ResponseEntity<>(groupService.addGroup(groupDetails), HttpStatus.OK);
    }

    @PostMapping("/updateGroup")
    public ResponseEntity<?> updateGroup (@RequestParam Integer groupId , @RequestBody GroupDetails groupDetails){

        try {
            return new ResponseEntity<>(groupService.updateGroup(groupId,groupDetails), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/deleteGroup")
    public ResponseEntity<?> deleteGroup (@RequestParam Integer groupId ){

        try {
            return new ResponseEntity<>(groupService.deleteGroup(groupId), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/findGroupById")
    public ResponseEntity<?> findGroupById (@RequestParam Integer groupId ){

        try {
            return new ResponseEntity<>(groupService.findGroupById(groupId), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/findAllGroups")
    public ResponseEntity<?> findAllGroups (){

        try {
            return new ResponseEntity<>(groupService.findAllGroups(), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
