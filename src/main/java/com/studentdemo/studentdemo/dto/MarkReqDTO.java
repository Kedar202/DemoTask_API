package com.studentdemo.studentdemo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.studentdemo.studentdemo.model.StudentEntity;
import com.studentdemo.studentdemo.model.SubjectData;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class MarkReqDTO {

    private Integer marks;

    private Integer subjectId;

    private Integer studentId;

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
