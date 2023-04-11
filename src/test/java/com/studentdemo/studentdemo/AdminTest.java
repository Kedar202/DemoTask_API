package com.studentdemo.studentdemo;

import com.studentdemo.studentdemo.dao.UserRepository;
import com.studentdemo.studentdemo.model.UserEntity;
import com.studentdemo.studentdemo.service.AdminService;
import com.studentdemo.studentdemo.serviceImpl.AdminServiceImpl;
import com.studentdemo.studentdemo.util.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminTest {

    @Autowired
    UserRepository adminDao;

    @Autowired
    AdminService adminService;

    @Test
    @Order(1)
    public void saveUser() {
        UserEntity saveUser = new UserEntity();
        Optional<UserEntity> user = adminDao.findById(2);
        saveUser.setId(1);
        saveUser.setUserName("abc");
        saveUser.setPassword("ab");
        Response actualResponse = adminService.saveUser(saveUser);
        System.out.println("saved User"+actualResponse);
        Response response = new Response("200", "User has been created successfully !");
        assertNotEquals(response, actualResponse);

    }

}
