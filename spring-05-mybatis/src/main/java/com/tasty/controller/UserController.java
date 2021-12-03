package com.tasty.controller;

import com.tasty.dao.UserMapper;
import com.tasty.pojo.User;
import com.tasty.service.UserMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserMapperImpl userMapperimpl;

    @GetMapping("/list")
    public List<User> getUserList(){
        return userMapperimpl.queryUserList();
    }


}
