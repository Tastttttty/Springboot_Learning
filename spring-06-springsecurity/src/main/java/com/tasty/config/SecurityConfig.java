package com.tasty.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//链式编程
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页都能访问，leve1下的功能页需要相应的用户名
        http.authorizeHttpRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
        //没有权限自动跳转到登录页,需要在controller层下开启相关页面
        http.formLogin();
        //防止网站工具
//        http.csrf().disable();
        //注销成功后来到首页
        http.logout().logoutSuccessUrl("/");
        http.rememberMe();
    }

//认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //数据来源应为数据库
        //给用户通过用户名和密码(需要加密)来给role属性
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user1").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
