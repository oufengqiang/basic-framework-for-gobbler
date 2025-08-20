package com.jufamen.gobbler.gateway.config;

import com.jufamen.gobbler.gateway.filter.JwtValidationFilter;
import com.jufamen.gobbler.gateway.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description Spring Security权限配置类
 * Spring3.0之前要使用Spring必须要有一个xml配置文件，而Spring3.0之后注解慢慢登上舞台，
 * 通过注解@Configuration和@Bean可以完全搞定。此时，注解和xml配置形成了相互协作与竞争的关系。
 * 随着Springboot的推广，注解的使用在Spring中大放光彩，xml的辉煌一去不返。
 * 通过注解，简化了配置，提升了编码效率。
 * @author Jufamen
 * @date 2025/8/16 15:54
 */
/*
    @Configuration注解是实现Java配置的核心。
    它允许开发者以编程的方式定义Bean的创建过程，而不是使用XML文件。
    这种基于注解的配置方式，不仅简化了配置的复杂性，还提高了代码的可读性和可维护性。
    它包含四个注解，分别是：
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
        @Component
            是Spring框架中最核心的注解之一，用于标识一个类为Spring容器管理的组件。
            通过组件扫描机制，Spring会自动实例化这些类并将其纳入IoC容器进行管理，
            是实现“依赖注入（DI）和控制反转（IoC）”的重要基础。
 */
@Configuration
/*
    @EnableWebSecurity是Spring Security提供的注解，它的作用是用于开启Web安全性支持
    当@EnableWebSecurity注解存在时，Spring Security的默认配置将会被应用
 */
@EnableWebSecurity
/*
    @EnableMethodSecurity注解用于开启方法级安全授权功能，允许通过注解直接在控制器方法上配置权限验证。
        prePostEnabled：确定Spring Security的preauthorization，postauthorization，PreFilter和PostFilter注解是否应该启用。
                        默认为true。如果需要启用前/后注释，则为true，否则为false
        securedEnabled：确定是否应该启用Spring Security的安全注释。默认为false。如果启用安全注释，则为true，否则为false
        jsr250Enabled：确定是否应该启用JSR-250注释。默认为false。如果启用JSR-250，则为true，否则为false
 */
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * 未授权异常处理器
     */
    private final UnauthorizedAccessDeniedHandler unauthorizedAccessDeniedHandler;

    /**
     * 未认证异常处理器
     */
    private final UnauthenticatedEntryPoint unauthenticatedEntryPoint;

    /**
     * security配置对象
     */
    private final SecurityProperties securityProperties;

    /**
     * SecurityFilterChain是Spring Security中定义的一个接口，表示一组过滤器的链，负责处理进入应用程序的HTTP请求。
     * @param http HttpSecurity对象，是Spring Security中用于配置HTTP安全策略的核心组件，主要用于定义认证、授权、会话管理等安全规则
     * @return 返回SecurityFilterChain对象
     * @throws Exception 异常
     */
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService, ServerProperties serverProperties) throws Exception {
        // authorizeHttpRequests是Spring Security中用于配置HTTP请求授权的一个关键方法，它允许根据特定的URL模式或请求属性来定义授权规则
        http.authorizeHttpRequests(
                requestMathcherRegisty -> requestMathcherRegisty.requestMatchers(
                        securityProperties.getIgnoreUrls().toArray(new String[0])) // 把白名单资源列表转成数组
                        .permitAll() // 允许所有用户访问白名单资源
                        .anyRequest() // 配合authenticated()使用
                        .authenticated() // 除白名单资源外的所有客户端请求，均需要经过认证
                )
                .csrf(AbstractHttpConfigurer::disable) // 用于禁用CSRF（跨站请求伪造）保护
                // 设置会话管理的配置器
                .sessionManagement(configuer ->
                        /*
                            SessionCreationPolicy是Spring Security中用于控制会话创建策略的枚举类型，主要用于管理HTTP会话的生成与使用。
                                ALWAYS：始终创建HTTP会话（传统Session认证模式建议使用）
                                NEVER：不创建HTTP会话，但会使用已存在的会话（若存在）
                                IF_REQUIRED：仅在需要时创建HTTP会话（传统Session认证模式建议使用）
                                STATELESS：不创建也不使用HTTP会话，不通过会话获取上下文（前后端分离模式建议使用）
                         */
                        configuer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 自定义异常处理机制
                .exceptionHandling(customizer ->
                                customizer.accessDeniedHandler(unauthorizedAccessDeniedHandler) // 处理未授权访问
                                        .authenticationEntryPoint(unauthenticatedEntryPoint) // 处理未认证访问
                )
                // .addFilterBefore是Spring Security配置中用于将自定义过滤器添加到目标过滤器之前的工具方法，主要用于调整过滤器链的执行顺序。
                .addFilterBefore(new JwtValidationFilter(userDetailsService,securityProperties.getJwt().getSecret()), UsernamePasswordAuthenticationFilter.class);
        // 根据通过HttpSecurity配置的安全规则，创建并返回一个SecurityFilterChain对象，该对象包含了所有基于配置的安全过滤器，并按照预定的顺序排列
        return http.build();
    }
}
