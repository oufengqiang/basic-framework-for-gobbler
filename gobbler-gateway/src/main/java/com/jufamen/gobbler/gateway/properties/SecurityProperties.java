package com.jufamen.gobbler.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 读取security配置类
 * 将配置文件中的属性映射到Java Bean上，从而实现外部配置的灵活关联
 */
@Data
@Component
/*
    @ConfigurationProperties注解提供了一种将外部配置（如application.properties或application.yml文件中的属性）绑定到Java对象的便捷方式。
    这种机制简化了配置管理，使得配置的变更更加灵活和动态。
 */
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    /**
     * 白名单资源列表
     */
    private List<String> ignoreUrls;

    /**
     * JWT配置
     */
    private JwtProperty jwt;

    @Data
    public static class JwtProperty {
        /**
         * JWT签名密钥
         */
        private String secret;
        /**
         * JWT有效期（单位：秒）
         */
        private long ttl;
    }
}
