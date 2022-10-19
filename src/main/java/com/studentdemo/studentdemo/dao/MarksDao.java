package com.studentdemo.studentdemo.dao;

import com.studentdemo.studentdemo.model.Marks;
import com.studentdemo.studentdemo.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksDao extends JpaRepository<Marks,Integer> {
    @Query("SELECT m from Marks m where student_id = :studentId AND subject_id = :subjectId")
    Marks existORNot(Integer studentId, Integer subjectId);

    @Query("SELECT m from Marks m where student_id = :studentId ")
    List<Marks> getMarksByStudentId(Integer studentId);
}
