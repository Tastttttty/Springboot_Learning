#在yml文件里面实例化对象，相比于@value优先使用yml方法
    @ConfigurationProperties(prefix = "person")
    松散绑定:yml中的为last0-name  --->   lastName
    占位符的使用 ${random.int}

#通过yml多环境配置

JSR303数据校验 @Validated 需先在pom文件下导入 spring-boot-starter-validation

