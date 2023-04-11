package com.studentdemo.studentdemo;

import com.studentdemo.studentdemo.dao.SubjectDao;
import com.studentdemo.studentdemo.model.SubjectData;
import com.studentdemo.studentdemo.service.SubjectService;
import com.studentdemo.studentdemo.serviceImpl.SubjectServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubjectTest {

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    SubjectService subjectService;


    @Test
    @Order(1)
    public void addSubjectAPITest() {
        SubjectData subjectData = new SubjectData();
        subjectData.setSubjectId(3);
        subjectData.setTitle("English");
        Response actualResponse = subjectService.addSubject(subjectData);
        assertNotEquals(null, actualResponse);
    }

    @Test
    @Order(2)
    public void updateSubject() {
        SubjectData subjectData = new SubjectData();
        subjectData.setSubjectId(3);
        subjectData.setTitle("Eng");
        Response actualResponse = subjectService.updateSubject(subjectData.getSubjectId(), subjectData);
        assertNotEquals(null, actualResponse);
    }

    @Test
    @Order(3)
    public void findSubjectByIdAPITest() {
        Response actualResponse = subjectService.findSubjectById(3);
        assertNotEquals(null, actualResponse);
    }

    @Test
    @Order(4)
    public void findAllSubject() {
        Response response = subjectService.findAllSubject();
        List<SubjectData> subjectData = new ArrayList<>();
        assertNotEquals(subjectData, response);
    }


    @Test
    @Order(5)
    @Transactional
    public void deleteSubject() {
        Response response = subjectService.deleteSubject(3);
        assertNotEquals(null, response);
    }
}
