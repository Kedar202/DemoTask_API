package com.studentdemo.studentdemo.service;

import com.studentdemo.studentdemo.dto.StudentReqDTO;
import com.studentdemo.studentdemo.model.StudentEntity;
import com.studentdemo.studentdemo.util.Response;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    Response addStudent(StudentReqDTO studentReqDTO);

    Response updateStudent(Integer studentId, StudentReqDTO studentReqDTO);

    Response deleteStudent(Integer studentId);

    Response findStudentById(Integer studentId);

    Response findAllStudents();
}
