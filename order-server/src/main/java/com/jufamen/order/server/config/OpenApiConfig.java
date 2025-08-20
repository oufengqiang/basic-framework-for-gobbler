package com.jufamen.order.server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @description Knife4j自定义配置类
 * @author Jufamen
 * @date 2025/8/19 14:26
 */
@Configuration
public class OpenApiConfig {

    /**
     * 读取 context-path，默认空串
     */
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * OpenAPI阶段的Swagger也被称为Swagger 3.0。
     * 在Swagger 2.0后，Swagger规范正式更名为OpenAPI规范，并且根据OpenAPI规范的版本号进行了更新。
     * 因此，Swagger 3.0对应的就是OpenAPI 3.0版本，它是Swagger在OpenAPI阶段推出的一个重要版本。
     * 与前几个版本相比，Swagger 3.0更加强调对RESTfull API的支持和规范化，提供了更丰富和灵活的定义方式，
     * 并且可以用于自动生成文档、客户端代码、服务器代码和测试工具等。
     * @return 返回配置信息
     */
    @Bean
    public OpenAPI orderOpenAPI() {
        System.out.println("contextPath: " + contextPath);
        // 如果没有设置context-path，就赋值"/"
        String basePath = (contextPath == null || contextPath.isBlank()) ? "/" : contextPath;
        // 返回配置信息
        return new OpenAPI()
                .info(new Info()
                        .title("订单服务") // 标题
                        .description("订单服务API文档，基于Spring Boot 3.x + SpringDoc 2.8.9") // 简介
//                        .contact(new Contact().name("Jufamen")) // 作者
                        .version("1.0.0")) // 版本
                .servers(List.of(new Server().url(basePath)));
    }
}
