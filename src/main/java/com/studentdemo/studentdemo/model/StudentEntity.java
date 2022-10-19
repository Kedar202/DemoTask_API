package com.studentdemo.studentdemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;
	private String firstName;
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
	@JoinColumn(name = "groupId")
	@JsonBackReference
	private GroupDetails groups;


	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public GroupDetails getGroups() {
		return groups;
	}

	public void setGroups(GroupDetails groups) {
		this.groups = groups;
	}
}
