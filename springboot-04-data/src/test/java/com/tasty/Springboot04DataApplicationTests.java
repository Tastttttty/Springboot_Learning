package com.tasty;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot04DataApplicationTests {
    //将yml中的datasource中自动装配
    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println("-------------------------------------------------------------------------------------------");
        //查看一下数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection = dataSource.getConnection();

        DruidDataSource druiddataSource = (DruidDataSource) dataSource;
        int maxActive = druiddataSource.getMaxActive();
        int initialSize = druiddataSource.getInitialSize();
        System.out.println(maxActive + " " + initialSize + " ");
        System.out.println("-------------------------------------------------------------------------------------------");
        //关闭连接
        connection.close();
    }
}
