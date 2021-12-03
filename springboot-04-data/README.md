### 注意点

##### 1.@ConfigurationProperties

可以作用在方法和实例上，

``` yaml
spring:
#  这是一个DataSource实例
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
#    自定义数据源
    type: com.alibaba.druid.pool.DruidDataSource
    #Spring Boot 默认是不注入这些属性值的，需要自己绑定
    #druid 数据源专有配置
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    #配置监控统计拦截的filters，stat:监控统计、log4j：日志记录、wall：防御sql注入
    #如果允许时报错  java.lang.ClassNotFoundException: org.apache.log4j.Priority
    #则导入 log4j 依赖即可，Maven 地址：https://mvnrepository.com/artifact/log4j/log4j
    filters: stat,wall,log4j
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

```
将自定义的 Druid数据源添加到容器中，不再让 SpringBoot自动创建，改用自己的数据源druid      
绑定全局配置文件中的 druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource从而让它们生效  @ConfigurationProperties(prefix = "spring.datasource")：作用就是将全局配置文件中前缀为 spring.datasource的属性值注入到 com.alibaba.druid.pool.DruidDataSource 的同名参数中
```

```java
//编写自己配置的数据源
@Configuration
public class DruidConfig {
//    @ConfigurationProperties当将该注解作用于方法上时，如果想要有效的绑定配置，那么该方法需要有@Bean注解且所属Class需要有@Configuration注解。
    //拿到yml文件中的注册的datasource实例
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean//注入到bean中 bean中注册实例化对象
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
}
```

