package com.studentdemo.studentdemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Marks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mardkId;
	
	private Integer marks;

	@ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
	@JoinColumn(name = "subjectId")
	@JsonBackReference
	private SubjectData subjectData;

	@ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
	@JoinColumn(name = "studentId")
	@JsonBackReference
	private StudentEntity studentEntity;

	private Date createDate;

	public Integer getMardkId() {
		return mardkId;
	}

	public void setMardkId(Integer mardkId) {
		this.mardkId = mardkId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getMarks() {
		return marks;
	}
	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public SubjectData getSubjectData() {
		return subjectData;
	}

	public void setSubjectData(SubjectData subjectData) {
		this.subjectData = subjectData;
	}

	public StudentEntity getStudentEntity() {
		return studentEntity;
	}

	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
	}
}
