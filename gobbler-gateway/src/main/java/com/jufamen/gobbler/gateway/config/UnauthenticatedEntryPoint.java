package com.jufamen.gobbler.gateway.config;

import com.jufamen.gobbler.gateway.utils.GlobalRetrun;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description 自定义未通过认证的响应结果
 *  AuthenticationEntryPoint是Spring Security中的一个核心接口，用于处理未认证或认证失败的请求。
 *  当用户尝试访问受保护的资源而未通过身份验证时，该接口会被调用以定义响应方式，例如重定向到登录页面、返回401状态码或显示错误页面。
 * @author Jufamen
 * @date 2025/8/16 11:32
 */
@Component
public class UnauthenticatedEntryPoint implements AuthenticationEntryPoint {

    /**
     * 重写未认证异常处理方法
     * @param request 客户端请求对象
     * @param response 服务器响应对象
     * @param authException 认证异常
     * @throws IOException IOException是Java中的一个异常类，属于java.io包。它表示在输入/输出操作过程中可能发生的各种错误或异常情况。IOException是Java中表示I/O操作失败时抛出的最顶层的异常，很多具体的I/O异常都是它的子类
     * @throws ServletException ServletException是Java Servlet API中的一个异常类，用于表示在处理Servlet请求或响应时发生的异常情况。
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        GlobalRetrun.retrunResult(response,HttpServletResponse.SC_UNAUTHORIZED,"认证不通过");
    }
}
