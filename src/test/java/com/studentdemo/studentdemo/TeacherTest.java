package com.studentdemo.studentdemo;

import com.studentdemo.studentdemo.dao.GroupDao;
import com.studentdemo.studentdemo.dao.StudentDao;
import com.studentdemo.studentdemo.dao.SubjectDao;
import com.studentdemo.studentdemo.dao.TeacherDao;
import com.studentdemo.studentdemo.dto.TeacherReqDto;
import com.studentdemo.studentdemo.model.*;
import com.studentdemo.studentdemo.service.TeacherService;
import com.studentdemo.studentdemo.util.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherTest {

    @Autowired
    StudentDao studentDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    GroupDao groupDao;

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    TeacherService teacherService;

    @Test
    @Order(2)
    public void getStudentByTeacherIdAPITest() {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(3);
        Response response = teacherService.getStudentByTeacherId(teacher.getTeacherId());
        assertNotEquals(null, response);
    }

    @Test
    @Order(1)
    public void createTeacherAPITest() {
        TeacherReqDto teacherReqDto = new TeacherReqDto();

        GroupDetails groupDetails = new GroupDetails();
        groupDetails.setGroupId(2);

        SubjectData subjectData = new SubjectData();
        subjectData.setSubjectId(2);

        teacherReqDto.setGroupId(groupDetails.getGroupId());
        teacherReqDto.setSubjectId(subjectData.getSubjectId());

        Response response = teacherService.createTeacher(teacherReqDto);
        assertNotEquals(null, response);
    }

    @Test
    @Order(3)
    public void updateTeacherAPITest() {
        TeacherReqDto teacherReqDto = new TeacherReqDto();

        teacherReqDto.setGroupId(5);
        teacherReqDto.setSubjectId(4);

        Response actualResponse = teacherService.updateTeacher(3, teacherReqDto);
        assertNotEquals(null, actualResponse);
    }

    @Test
    @Order(6)
    @Transactional
    public void deleteTeacher() {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(2);
        Response actualResponse = teacherService.deleteTeacher(2);
        assertNotEquals(null, actualResponse);
    }

    @Test
    @Order(4)
    public void findTeacherById() {
        Response actualResponse = teacherService.findTeacherById(3);
        assertNotEquals(null, actualResponse);
    }

    @Test
    @Order(5)
    public void findAllTeacher() {
        Response actualResponse = teacherService.findAllTeacher();
        List<Teacher> teachers = new ArrayList<>();
        assertNotEquals(teachers, actualResponse);
    }


}
