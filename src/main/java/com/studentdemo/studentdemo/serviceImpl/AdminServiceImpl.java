package com.studentdemo.studentdemo.serviceImpl;

import com.studentdemo.studentdemo.dao.UserRepository;
import com.studentdemo.studentdemo.model.UserEntity;
import com.studentdemo.studentdemo.service.AdminService;
import com.studentdemo.studentdemo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Response saveUser(UserEntity user) {
        Response response = new Response();

        Optional<UserEntity> userEntity = userRepository.findOneByEmailIgnoreCase(user.getUserName());

        if (!userEntity.isPresent()) {
            UserEntity userModel = new UserEntity();
            userModel.setUserName(user.getUserName());
            userModel.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(userModel);
            response.setMessage("User has been created successfully !");
            response.setStatus("200");

        } else {
            response.setMessage("Unexpected Error !");
            response.setStatus("500");
            return new Response();
        }
        return response;
    }
}
