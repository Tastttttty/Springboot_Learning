package com.tasty.dao;

import com.tasty.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    List<User> queryUserList();

    User queryUserById(@Param("userid") int id);
}
