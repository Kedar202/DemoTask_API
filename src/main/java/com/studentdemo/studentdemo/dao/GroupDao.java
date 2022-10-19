package com.studentdemo.studentdemo.dao;


import com.studentdemo.studentdemo.model.GroupDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends JpaRepository<GroupDetails, Integer> {
    @Query("SELECT g FROM GroupDetails g where g.name = :name")
    public GroupDetails getByGroupName(String name);
}
