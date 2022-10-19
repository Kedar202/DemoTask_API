package com.studentdemo.studentdemo.serviceImpl;

import com.studentdemo.studentdemo.dao.SubjectDao;
import com.studentdemo.studentdemo.model.SubjectData;
import com.studentdemo.studentdemo.service.SubjectService;
import com.studentdemo.studentdemo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectDao subjectDao;

    @Override
    public Response addSubject(SubjectData subjectData) {
        Response response = new Response();
        try {
            SubjectData subject = subjectDao.getByName(subjectData.getTitle());
            if (subject == null) {
                subjectDao.save(subjectData);
                return new Response("200", "teacher Added SuccessFully", new ArrayList<>());
            } else {
                return new Response("500", "Teacher Not Added SuccessFully", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Response updateSubject(Integer subjectId, SubjectData subjectData) {
        Response response = new Response();
        try {
            Optional<SubjectData> subjectData1 = subjectDao.findById(subjectId);
            if (subjectData1.isPresent()) {
                subjectData1.get().setTitle(subjectData.getTitle());
                subjectDao.save(subjectData);
                return new Response("200", "Subject Updated Successfully", new ArrayList<>());
            } else {
                return new Response("500", "Subject Not Updated", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response deleteSubject(Integer subjectId) {
        Response response = new Response();
        try {
            Optional<SubjectData> subjectData1 = subjectDao.findById(subjectId);
            if (subjectData1.isPresent()) {
                subjectDao.delete(subjectData1.get());
                return new Response("200", "Subject Deleted SuccessFully", new ArrayList<>());
            } else {
                return new Response("500", "Subject Not Deleted SuccessFully", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response findSubjectById(Integer subjectId) {
        Response response = new Response();
        try {
            Optional<SubjectData> subjectData1 = subjectDao.findById(subjectId);
            if (subjectData1.isPresent()) {
                return new Response("200", "Data Fetched SuccessFully", subjectData1.get());
            } else {
                return new Response("500", "Data Not Fetched SuccessFully", subjectData1.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response findAllSubject() {
        Response response = new Response();
        try {
            List<SubjectData> subjectData1 = subjectDao.findAll();
            return new Response("200", "Data Fetched SuccessFully", subjectData1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
