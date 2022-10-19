package com.studentdemo.studentdemo.serviceImpl;

import com.studentdemo.studentdemo.dao.GroupDao;
import com.studentdemo.studentdemo.dao.StudentDao;
import com.studentdemo.studentdemo.dto.StudentReqDTO;
import com.studentdemo.studentdemo.model.StudentEntity;
import com.studentdemo.studentdemo.service.StudentService;
import com.studentdemo.studentdemo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    GroupDao groupDao;

    @Override
    public Response addStudent(StudentReqDTO studentReqDTO) {
        Response response = new Response();
        try {
            if (studentReqDTO != null) {
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setFirstName(studentReqDTO.getFirstName());
                studentEntity.setLastName(studentReqDTO.getLastName());
                studentEntity.setGroups(groupDao.findById(studentReqDTO.getGroupId()).get());
                studentDao.save(studentEntity);
                return new Response("200", "Student Added SuccessFully", new ArrayList<>());
            }
        } catch (Exception e) {


        }
        return response;
    }

    @Override
    public Response updateStudent(Integer studentId, StudentReqDTO studentReqDTO) {
        Response response = new Response();
        try {
            Optional<StudentEntity> student = studentDao.findById(studentId);
            if (student.isPresent()) {
                if (studentReqDTO != null) {
                    StudentEntity studentEntity = student.get();
                    studentEntity.setFirstName(studentReqDTO.getFirstName());
                    studentEntity.setLastName(studentReqDTO.getLastName());
                    studentEntity.setGroups(groupDao.findById(studentReqDTO.getGroupId()).get());
                    studentDao.save(studentEntity);
                    response.setMessage("success");
                    response.setStatus("200");
                    return new Response("200", "Student Updated SuccessFully", new ArrayList<>());
                }
            } else {
                return new Response("500", "Student Not Updated", new ArrayList<>());
            }
        } catch (Exception e) {
            return new Response("500", "Student Not Updated", new ArrayList<>());
        }
        return response;
    }

    @Override
    public Response deleteStudent(Integer studentId) {
        Response response = new Response();
        try {
            Optional<StudentEntity> student = studentDao.findById(studentId);
            if (student.isPresent()) {
                studentDao.delete(student.get());
                return new Response("200", "Student Deleted SuccessFully", new ArrayList<>());
            } else {
                return new Response("500", "Student Not Deleted", new ArrayList<>());
            }

        } catch (Exception e) {

        }
        return response;
    }

    @Override
    public Response findStudentById(Integer studentId) {
        Response response = new Response();
        try {
            Optional<StudentEntity> student = studentDao.findById(studentId);
            if (student.isPresent()) {
                response.setResultObj(student.get());
                return new Response("200", "Data Fetched SuccessFully", student.get());
            } else {
                return new Response("500", "Data Not Fetched SuccessFully", student.get());
            }

        } catch (Exception e) {

        }
        return response;
    }

    @Override
    public Response findAllStudents() {
        Response response = new Response();
        try {
            List<StudentEntity> studentEntities = studentDao.findAll();
            return new Response("200", "Data Fetched SuccessFully", studentEntities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
