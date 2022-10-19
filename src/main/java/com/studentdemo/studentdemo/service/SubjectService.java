package com.studentdemo.studentdemo.service;

import com.studentdemo.studentdemo.model.SubjectData;
import com.studentdemo.studentdemo.util.Response;

public interface SubjectService {
    Response addSubject(SubjectData subjectData);

    Response updateSubject(Integer subjectId, SubjectData subjectData);

    Response deleteSubject(Integer subjectId);

    Response findSubjectById(Integer subjectId);

    Response findAllSubject();
}
