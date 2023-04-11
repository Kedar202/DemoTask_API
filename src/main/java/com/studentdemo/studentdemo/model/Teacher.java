package com.studentdemo.studentdemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;

    @OneToOne
    @JoinColumn(name = "subjectId")
    @JsonBackReference
    private SubjectData subjectData;

    @OneToOne
    @JoinColumn(name = "groupId")
    @JsonBackReference
    private GroupDetails groups;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public SubjectData getSubjectData() {
        return subjectData;
    }

    public void setSubjectData(SubjectData subjectData) {
        this.subjectData = subjectData;
    }

    public GroupDetails getGroups() {
        return groups;
    }

    public void setGroups(GroupDetails groups) {
        this.groups = groups;
    }
}
