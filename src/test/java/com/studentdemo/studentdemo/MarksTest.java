package com.studentdemo.studentdemo;

import com.studentdemo.studentdemo.dao.MarksDao;
import com.studentdemo.studentdemo.dao.StudentDao;
import com.studentdemo.studentdemo.dao.SubjectDao;
import com.studentdemo.studentdemo.dto.MarkReqDTO;
import com.studentdemo.studentdemo.model.GroupDetails;
import com.studentdemo.studentdemo.model.Marks;
import com.studentdemo.studentdemo.model.StudentEntity;
import com.studentdemo.studentdemo.model.SubjectData;
import com.studentdemo.studentdemo.service.MarksService;
import com.studentdemo.studentdemo.util.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.BeanUtils;
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
public class MarksTest {

    @Autowired
    MarksDao marksDao;

    @Autowired
    MarksService marksService;

    @Autowired
    StudentDao studentDao;

    @Autowired
    SubjectDao subjectDao;

    @Test
    @Order(3)
    public void getMarksByStudentIdAPITest() {
        Response response = marksService.getMarksByStudentId(1);
        assertNotEquals(null, response);
    }


    @Test
    @Order(1)
    public void addMarksAPITest() {
        MarkReqDTO marks = new MarkReqDTO();

        marks.setMarks(80);
        marks.setSubjectId(1);
        marks.setStudentId(1);

        Response expectedResponse = new Response("200", "Data Saved SuccessFully", new ArrayList<>());

        Response actualResponse = marksService.addMarks(marks);
        assertNotEquals(null, actualResponse);
    }

    @Test
    @Order(2)
    public void updateMarksByMarkIdAPITest() {
        MarkReqDTO markReqDTO = new MarkReqDTO();

        Marks marks=new Marks();
        marks.setMardkId(1);

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentId(1);
        studentDao.save(studentEntity);

        SubjectData subjectData = new SubjectData();
        subjectData.setSubjectId(1);
        subjectDao.save(subjectData);


        markReqDTO.setMarks(20);
        markReqDTO.setStudentId(studentEntity.getStudentId());
        markReqDTO.setSubjectId(subjectData.getSubjectId());

        Response actualResponse = marksService.updateMarks(marks.getMardkId(), markReqDTO);
//        Response expectedResponse = new Response("500", "Data Not Saved SuccessFully", null);
        assertNotEquals(null, actualResponse);
    }

    @Test
    @Order(6)
    @Transactional
    public void deleteMarksAPITest() {
        Response response = marksService.deleteMarks(1);
        assertNotEquals(null, response);
    }

    @Test
    @Order(4)
    public void findMarkByIdAPITest() {
        Response actualResponse = marksService.findMarkById(1);
        assertNotEquals(null, actualResponse);

    }

    @Test
    @Order(5)
    public void findAllMarksAPITest() {
        Response actual = marksService.findAllMarks();
        List<Marks> expected=new ArrayList<>();
        assertNotEquals(expected, actual);
    }


}
