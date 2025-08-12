package com.jufamen.order.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.jufamen")
/*
    @EnableDiscoveryClient是Spring Cloud框架中用于启用服务发现功能的注解，
    主要作用是将应用注册到服务注册中心（如 Eureka、Consul、Nacos 等），并实现服务间的相互发现与通信。
 */
@EnableDiscoveryClient
@Slf4j
@SpringBootApplication
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

}
