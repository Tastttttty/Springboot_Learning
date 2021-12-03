package com.tasty.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//编写自己配置的数据源
@Configuration
public class DruidConfig {
    //配置druid数据源注入spring容器中
//    @ConfigurationProperties当将该注解作用于方法上时，如果想要有效的绑定配置，那么该方法需要有@Bean注解且所属Class需要有@Configuration注解。
    @ConfigurationProperties(prefix = "spring.datasource")//拿到yml文件中的注册的datasource实例
    @Bean//注入到bean中 bean中注册实例化对象
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }


//    配置Druid数据源监控
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        initParams.put("allow", "");
        bean.setInitParameters(initParams);
        return bean;
    }

//  配置Druid web监控 filter过滤器
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        //  exclusions: 哪些请求进行过滤排除掉，不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*,/jdbc/*");
        bean.setInitParameters(initParams);
        //      /*过滤所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
