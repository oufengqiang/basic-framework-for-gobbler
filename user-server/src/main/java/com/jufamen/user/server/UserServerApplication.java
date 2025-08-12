package com.jufamen.user.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/*
    @ComponentScan是扫描组件的注解
 */
@ComponentScan(basePackages = "com.jufamen")
/*
    @MapperScan是spring用于批量注入mybatis映射器(DAO接口)的注解。与之相对应@Mapper进行单个注册。
    @Mapper是MyBatis框架中用于标识接口为Mapper接口的注解，主要用于简化数据库操作配置。
    主要作用：
        1、标识Mapper接口：通过@Mapper注解标识接口为MyBatis的Mapper接口，框架自动识别并生成代理对象处理数据库操作
        2、替代XML配置：使用注解直接在接口方法上定义SQL语句，无需编写XML映射文件
        3、参数绑定：支持通过接口方法参数传递，MyBatis自动绑定到SQL占位符
 */
@MapperScan(basePackages = "com.jufamen.user.server.mapper")
/*
    @EnableDiscoveryClient是Spring Cloud框架中用于启用服务发现功能的注解，
    主要作用是将应用注册到服务注册中心（如 Eureka、Consul、Nacos 等），并实现服务间的相互发现与通信。
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

}
