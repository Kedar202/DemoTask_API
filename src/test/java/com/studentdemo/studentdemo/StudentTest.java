package com.studentdemo.studentdemo;

import com.studentdemo.studentdemo.dao.GroupDao;
import com.studentdemo.studentdemo.dao.StudentDao;
import com.studentdemo.studentdemo.dto.StudentReqDTO;
import com.studentdemo.studentdemo.model.GroupDetails;
import com.studentdemo.studentdemo.model.StudentEntity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentTest {

    @Autowired
    StudentDao studentDao;

    @Autowired
    GroupDao groupDao;

    @Test
    @Order(1)
    public void addStudentAPITest() {
        GroupDetails groupDetails = new GroupDetails();
        String filename= UUID.randomUUID().toString();
        groupDetails.setGroupId(3);
        groupDetails.setName("abc");

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentId(22);
        studentEntity.setFirstName("abc");
        studentEntity.setLastName("def");
        studentEntity.setGroups(groupDetails);

        Optional<StudentEntity> student = studentDao.findById(22);

        studentDao.save(studentEntity);

        Optional<StudentEntity> student1 = studentDao.findById(22);

        assertNotEquals(student, student1);
    }

    @Test
    @Order(2)
    public void updateStudentAPITest() {
        Optional<GroupDetails> groupDetails = groupDao.findById(3);

        Optional<StudentEntity> student = studentDao.findById(22);
        if (groupDetails.isPresent() && student.isPresent()) {

            groupDetails.get().setGroupId(3);

            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setStudentId(22);
            studentEntity.setFirstName("abc");
            studentEntity.setLastName("def");
            studentEntity.setGroups(groupDetails.get());

            studentDao.save(studentEntity);
        }
        Optional<StudentEntity> student1 = studentDao.findById(22);

        assertNotEquals(student, student1);
    }

    @Test
    @Order(3)
    @Transactional
    public void deleteStudentAPITest() {
        Optional<StudentEntity> student = studentDao.findById(19);
        studentDao.deleteById(19);

        Optional<StudentEntity> student1 = studentDao.findById(19);
        assertNotEquals(student, student1);
    }
}
