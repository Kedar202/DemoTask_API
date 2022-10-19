package com.studentdemo.studentdemo.dao;

import com.studentdemo.studentdemo.model.StudentEntity;
import com.studentdemo.studentdemo.model.SubjectData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDao extends JpaRepository<SubjectData,Integer> {
    @Query("SELECT s from SubjectData s where s.title = :title")
    SubjectData getByName(String title);
}
