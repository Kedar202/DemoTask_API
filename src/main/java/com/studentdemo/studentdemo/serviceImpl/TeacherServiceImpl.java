package com.studentdemo.studentdemo.serviceImpl;

import com.studentdemo.studentdemo.dao.GroupDao;
import com.studentdemo.studentdemo.dao.StudentDao;
import com.studentdemo.studentdemo.dao.SubjectDao;
import com.studentdemo.studentdemo.dao.TeacherDao;
import com.studentdemo.studentdemo.dto.TeacherReqDto;
import com.studentdemo.studentdemo.model.Marks;
import com.studentdemo.studentdemo.model.StudentEntity;
import com.studentdemo.studentdemo.model.SubjectData;
import com.studentdemo.studentdemo.model.Teacher;
import com.studentdemo.studentdemo.service.TeacherService;
import com.studentdemo.studentdemo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    GroupDao groupDao;

    @Autowired
    StudentDao studentDao;

    @Override
    public Response createTeacher(TeacherReqDto teacherReqDto) {
        Response response = new Response();
        try {
            Teacher teacher = teacherDao.existORNot(teacherReqDto.getGroupId(), teacherReqDto.getSubjectId());
            if (teacher == null) {
                Teacher teacherReq = new Teacher();
                teacherReq.setSubjectData(subjectDao.findById(teacherReqDto.getSubjectId()).get());
                teacherReq.setGroups(groupDao.findById(teacherReqDto.getGroupId()).get());
                teacherDao.save(teacherReq);
                new Response("200", "Teacher Created SuccessFully", new ArrayList<>());
            } else {
                new Response("500", "Teacher Not Created SuccessFully", new ArrayList<>());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response getStudentByTeacherId(Integer teacherId) {
        Response response = new Response();
        List<StudentEntity> studentEntities = new ArrayList<>();
        try {
            Optional<Teacher> teacher = teacherDao.findById(teacherId);

            if (teacher.isPresent()) {
                Integer groupId = teacher.get().getGroups().getGroupId();
                studentEntities = studentDao.findByGroupId(groupId);
                response.setResultObj(studentEntities);
                return new Response("200", "Data Fetched SuccessFully", studentEntities);
            } else {
                return new Response("500", "Data Fetched SuccessFully", studentEntities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response updateTeacher(Integer teacherId, TeacherReqDto teacherReqDto) {
        Response response = new Response();
        try {
            Optional<Teacher> teacher = teacherDao.findById(teacherId);
            if (teacher.isPresent()) {
                teacher.get().setSubjectData(subjectDao.findById(teacherReqDto.getSubjectId()).get());
                teacher.get().setGroups(groupDao.findById(teacherReqDto.getGroupId()).get());
                teacherDao.save(teacher.get());
                return new Response("200", "Data Updated SuccessFully", new ArrayList<>());
            } else {
                return new Response("200", "Data Not Updated SuccessFully", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response deleteTeacher(Integer teacherId) {
        Response response = new Response();
        try {
            Optional<Teacher> teacher = teacherDao.findById(teacherId);
            if (teacher.isPresent()) {
                teacherDao.delete(teacher.get());
                response.setStatus("200");
                response.setMessage("Success");
                return new Response("200", "Teacher Deleted SuccessFully", new ArrayList<>());
            } else {
                return new Response("500", "Teacher Not Deleted SuccessFully", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response findTeacherById(Integer teacherId) {
        Response response = new Response();
        try {
            Optional<Teacher> teacher = teacherDao.findById(teacherId);
            if (teacher.isPresent()) {
                response.setResultObj(teacher.get());
                response.setStatus("200");
                response.setMessage("Success");
                return new Response("200", "Data Fetched SuccessFully", teacher.get());
            } else {
                return new Response("500", "Data Not Fetched SuccessFully", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response findAllTeacher() {
        Response response = new Response();
        try {
            List<Teacher> teacher = teacherDao.findAll();

            response.setResultObj(teacher);
            response.setStatus("200");
            response.setMessage("Success");
            return new Response("200", "Data Fetched SuccessFully", teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
