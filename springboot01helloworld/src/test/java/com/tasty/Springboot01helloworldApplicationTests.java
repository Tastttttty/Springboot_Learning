package com.tasty;

import com.tasty.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot01helloworldApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    Person person;

    @Test
    public void test(){
        System.out.println(person);
    }

}
