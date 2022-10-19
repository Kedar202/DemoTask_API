package com.studentdemo.studentdemo.dao;

import com.studentdemo.studentdemo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao extends JpaRepository<Teacher,Integer> {
    @Query("SELECT m from Teacher m where group_id = :groupId AND subject_id = :subjectId")
    Teacher existORNot(Integer groupId, Integer subjectId);
}
