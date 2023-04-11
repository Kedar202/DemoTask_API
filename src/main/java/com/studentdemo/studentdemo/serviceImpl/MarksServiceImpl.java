package com.studentdemo.studentdemo.serviceImpl;

import com.studentdemo.studentdemo.dao.MarksDao;
import com.studentdemo.studentdemo.dao.StudentDao;
import com.studentdemo.studentdemo.dao.SubjectDao;
import com.studentdemo.studentdemo.dto.MarkReqDTO;
import com.studentdemo.studentdemo.model.Marks;
import com.studentdemo.studentdemo.model.SubjectData;
import com.studentdemo.studentdemo.service.MarksService;
import com.studentdemo.studentdemo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MarksServiceImpl implements MarksService {

    @Autowired
    MarksDao marksDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    SubjectDao subjectDao;

    @Override
    public Response addMarks(MarkReqDTO mark) {
        Response response = new Response();
        try {
            Marks marks = marksDao.existORNot(mark.getStudentId(), mark.getSubjectId());
            if (marks == null) {
                Marks marksDetails = new Marks();
                marksDetails.setCreateDate(new Date());
                marksDetails.setStudentEntity(studentDao.findById(mark.getStudentId()).get());
                marksDetails.setSubjectData(subjectDao.findById(mark.getSubjectId()).get());
                marksDetails.setMarks(mark.getMarks());
                marksDao.save(marksDetails);
                return new Response("200", "Data Saved SuccessFully", new ArrayList<>());
            } else {
                return new Response("500", "Data Not Saved SuccessFully", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response getMarksByStudentId(Integer studentId) {
        Response response = new Response();
        List<Map<String, Object>> mapList = null;
        try {
            List<Marks> marksList = marksDao.getMarksByStudentId(studentId);
            mapList = new ArrayList<>();
            for (Marks marks : marksList) {
                Map<String, Object> map = new HashMap<>();
                map.put(subjectDao.findById(marks.getSubjectData().getSubjectId()).get().getTitle(), marks.getMarks());
                mapList.add(map);
            }
            return new Response("200", "Data Fetched SuccessFully", mapList);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return response;
    }

    @Override
    public Response updateMarks(Integer markId, MarkReqDTO markDto) {
        Response response = new Response();
        try {
            Optional<Marks> marks = marksDao.findById(markId);
            System.out.println("data is present" + marks.get());
            if (marks.isPresent()) {
                marks.get().setStudentEntity(studentDao.findById(markDto.getStudentId()).get());
                marks.get().setSubjectData(subjectDao.findById(markDto.getSubjectId()).get());
                marks.get().setMarks(markDto.getMarks());
                marksDao.save(marks.get());
                return new Response("200", "Data Updated SuccessFully", null);
            } else {
                return new Response("500", "Data Not Saved SuccessFully", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response deleteMarks(Integer markId) {
        Response response = new Response();
        try {
            Optional<Marks> marks = marksDao.findById(markId);
            if (marks.isPresent()) {
                marksDao.delete(marks.get());
                return new Response("200", "Data Deleted SuccessFully", null);
            } else {
                return new Response("500", "Data Not Deleted SuccessFully", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response findMarkById(Integer markId) {
        Response response = new Response();
        try {
            Optional<Marks> marks = marksDao.findById(markId);
            if (marks.isPresent()) {
                return new Response("200", "Data Fetched SuccessFully", marks.get());
            } else {
                return new Response("500", "Data Not Fetched", marks.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response findAllMarks() {
        Response response = new Response();
        try {
            List<Marks> marks = marksDao.findAll();
            response.setResultObj(marks);
            return new Response("200", "Data Fetched SuccessFully", marks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
