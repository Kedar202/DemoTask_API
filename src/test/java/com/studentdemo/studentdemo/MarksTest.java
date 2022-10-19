package com.studentdemo.studentdemo;

import com.studentdemo.studentdemo.dao.MarksDao;
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

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MarksTest {

    @Autowired
    MarksDao marksDao;

    @Test
    @Order(1)
    public void getMarksByStudentIdAPITest() {
        Marks marks = new Marks();
        StudentEntity studentEntity = new StudentEntity();
        GroupDetails groupDetails = new GroupDetails();
        SubjectData subjectData = new SubjectData();
        subjectData.setTitle("Maths");
        subjectData.setSubjectId(1);
        groupDetails.setName("abc");
        groupDetails.setGroupId(1);
        studentEntity.setStudentId(1);
        studentEntity.setGroups(groupDetails);
        marks.setMardkId(1);
        marks.setMarks(90);
        marks.setStudentEntity(studentEntity);
        marks.setSubjectData(subjectData);
        marksDao.save(marks);
        List<Marks> marksList = new ArrayList<>();
        List<Marks> existingList = marksDao.findAll();
        marksList.add(marks);
        assertNotEquals(existingList, marksList);
    }

}
