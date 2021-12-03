# springsecurity

#### 1.在config下创建相关配置文件

```java
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
        http.csrf().disable();
        //注销成功后来到首页
        http.logout().logoutSuccessUrl("/");
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
```

需要通过@EnableWebSecurity，使其生效

#### 2.修改前端代码，使不同权限的人访问不同的内容

```html
<!--如果未登录-->
                <div sec:authorize="!isAuthenticated()">
                    <a class="item" th:href="@{/login}">
                        <i class="address card icon"></i> 登录
                    </a>
                </div>

                <!--如果已登录-->
                <div sec:authorize="isAuthenticated()">
                    <a class="item">
                        <i class="address card icon"></i>
                        用户名：<span sec:authentication="principal.username"></span>
                        角色：<span sec:authentication="principal.authorities"></span>
                    </a>
                </div>

                <div sec:authorize="isAuthenticated()">
                    <a class="item" th:href="@{/logout}">
                        <i class="sign-out icon"></i> 注销
                    </a>
                </div>
```

这里用到了sec:authorize="!isAuthenticated(),sec:authentication="principal.username",

sec:authentication="principal.authorities",需要导入依赖,注意对应springsecurity5版本

```xml
<dependency>
            <groupId>org.thymeleaf.extras</groupId>
            <artifactId>thymeleaf-extras-springsecurity5</artifactId>
            <version>3.0.4.RELEASE</version>
        </dependency>
```

#### 3.开启记住我功能

```java
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
        //开启记住我功能
        http.rememberMe();
    }
```

登录成功后，将cookie发送给浏览器session保存，以后登录带上这个cookie，只要通过检查就可以免登录了。如果点击注销，则会删除这个cookie

#### 4.定制首页-------formLogin模式登录

```java
http.formLogin()
                .loginPage("/login.html")//用户未登录时，访问任何资源都转跳到该路径，即登录页面
                .loginProcessingUrl("/login")//登录表单form中action的地址，也就是处理认证请求的路径
                .usernameParameter("uname")///登录表单form中用户名输入框input的name名，不修改的话默认是username
                .passwordParameter("pword")//form中密码输入框input的name名，不修改的话默认是password
                .defaultSuccessUrl("/index")//登录认证成功后默认转跳的路径
                .and()
```

此程序还未关联数据库的登录信息

结合1中的认证代码理解