package com.studentdemo.studentdemo.dao;

import com.studentdemo.studentdemo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    @Query("SELECT u from UserEntity u where userName = :userName ")
    Optional<UserEntity> findOneByEmailIgnoreCase(String userName);

    @Query("SELECT u from UserEntity u where status = 'INACTIVE' ")
    List<UserEntity> getInActiveUser();
}
