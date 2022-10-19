package com.studentdemo.studentdemo;

import com.studentdemo.studentdemo.dao.StudentDao;
import com.studentdemo.studentdemo.model.GroupDetails;
import com.studentdemo.studentdemo.model.Marks;
import com.studentdemo.studentdemo.model.StudentEntity;
import com.studentdemo.studentdemo.model.SubjectData;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherTest {

    @Autowired
    StudentDao studentDao;

    @Test
    @Order(1)
    public void getStudentByTeacherIdAPITest() {
        StudentEntity studentEntity = new StudentEntity();
        GroupDetails groupDetails = new GroupDetails();
        groupDetails.setGroupId(1);
        groupDetails.setName("abc");

        studentEntity.setStudentId(1);
        studentEntity.setGroups(groupDetails);
        studentEntity.setLastName("xyz");
        studentEntity.setFirstName("XYZ");
        studentDao.save(studentEntity);
        List<StudentEntity> studentEntityList = new ArrayList<>();
        studentEntityList.add(studentEntity);
        List<StudentEntity> existingList = studentDao.findAll();
        assertNotEquals(existingList, studentEntityList);

    }
}
