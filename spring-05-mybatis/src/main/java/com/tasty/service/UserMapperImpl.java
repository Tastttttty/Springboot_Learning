package com.tasty.service;

import com.tasty.dao.UserMapper;
import com.tasty.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperImpl implements UserMapper {
    @Override
    public List<User> queryUserList() {
        return this.queryUserList();
    }

    @Override
    public User queryUserById(int id) {
        return this.queryUserById(id);
    }
}
