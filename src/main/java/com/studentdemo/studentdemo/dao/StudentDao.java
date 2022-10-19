package com.studentdemo.studentdemo.dao;

import com.studentdemo.studentdemo.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<StudentEntity,Integer> {

    @Query("SELECT s from StudentEntity s where group_id = :groupId")
    List<StudentEntity> findByGroupId(Integer groupId);
}
