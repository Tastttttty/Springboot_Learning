package com.tasty.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated//JSR303数据校验 需先在pom文件下导入 spring-boot-starter-validation
@Component//注入bean
@ConfigurationProperties(prefix = "person")
public class Person {
    //@Email 表示name属性赋值时必须是邮箱的格式
    private String name;
    private Integer age;
    private String sex;
    private Date date;
    private boolean ishappy;
    private Map<String, Object> maps;
    private List<Object> list;
    private Dog dog;
}
