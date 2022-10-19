package com.studentdemo.studentdemo.service;

import com.studentdemo.studentdemo.dto.MarkReqDTO;
import com.studentdemo.studentdemo.model.Marks;
import com.studentdemo.studentdemo.util.Response;

public interface MarksService {
    Response addMarks(MarkReqDTO mark);

    Response getMarksByStudentId(Integer studentId);

    Response updateMarks(Integer markId, MarkReqDTO markDto);

    Response deleteMarks(Integer markId);

    Response findMarkById(Integer markId);

    Response findAllMarks();
}
