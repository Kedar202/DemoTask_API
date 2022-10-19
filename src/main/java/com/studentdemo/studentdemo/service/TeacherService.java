package com.studentdemo.studentdemo.service;

import com.studentdemo.studentdemo.dto.TeacherReqDto;
import com.studentdemo.studentdemo.util.Response;

public interface TeacherService {
    Response createTeacher(TeacherReqDto teacherReqDto);

    Response getStudentByTeacherId(Integer teacherId);

    Response updateTeacher(Integer teacherId, TeacherReqDto teacherReqDto);

    Response deleteTeacher(Integer teacherId);

    Response findTeacherById(Integer teacherId);

    Response findAllTeacher();
}
