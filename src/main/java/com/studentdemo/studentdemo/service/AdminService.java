package com.studentdemo.studentdemo.service;

import com.studentdemo.studentdemo.model.UserEntity;
import com.studentdemo.studentdemo.util.Response;

public interface AdminService {
    Response saveUser(UserEntity user);
}
