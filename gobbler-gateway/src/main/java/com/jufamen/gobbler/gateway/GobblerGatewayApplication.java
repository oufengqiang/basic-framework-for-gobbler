package com.jufamen.gobbler.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关服务
 * Spring Gateway是一个基于Spring 5、Spring Boot 2和Project Reactor的API网关。
 * 它旨在为微服务架构提供一个简单、有效的统一的API路由、限流、熔断等功能
 * 是全异步非阻塞的，对于高并发场景有着更好的表现
 */
/*
@EnableDiscoveryClient是Spring Cloud框架中用于启用服务发现功能的注解，
    主要作用是将应用注册到服务注册中心（如 Eureka、Consul、Nacos 等），并实现服务间的相互发现与通信。
    它包含四个注解，分别是
        @Target({ElementType.TYPE})
            Java元注解，用于指定注解的作用目标，可以是类、方法、字段等
                ElementType.TYPE 注解可以应用到类、接口、枚举上
                ElementType.FIELD 注解可以应用到字段（成员变量）上
                ElementType.METHOD 注解可以应用到方法上
                ElementType.CONSTRUCTOR 注解可以应用到构造方法上
                ElementType.PARAMETER 注解可以应用到方法的参数上。
                ElementType.LOCAL_VARIABLE 注解可以应用到局部变量上。
                ElementType.ANNOTATION_TYPE 注解可以应用到其他注解上。
                ElementType.PACKAGE 注解可以应用到包上。
                ...
        @Retention(RetentionPolicy.RUNTIME)
            Java元注解，用于指定注解的保留期限，即注解在编译期、类加载期、运行期的生命周期
                RetentionPolicy.SOURCE 注解仅保留在源码中
                RetentionPolicy.CLASS 注解保留在字节码中，但是反射机制不能调用
                RetentionPolicy.RUNTIME 注解保留在字节码中，并且可以被反射机制所使用
        @Documented
            Java元注解，用于指定注解是否包含在JavaDoc文档中
        @Inherited
            Java元注解，用于指定注解是否可以被继承
        @Import({EnableDiscoveryClientImportSelector.class})
            Spring Framework注解，主要用于模块化配置管理，支持三种导入方式：直接导入配置类、动态选择组件、手动注册 Bean 定义，适用于不同场景的组件批量注册与管理。 ‌
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GobblerGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GobblerGatewayApplication.class, args);
    }

}
