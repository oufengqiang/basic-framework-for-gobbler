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
 * @author Jufamen
 * @description 自定义未通过认证的响应结果
 *  AuthenticationEntryPoint是Spring Security中的一个核心接口，用于处理未认证或认证失败的请求。
 *  当用户尝试访问受保护的资源而未通过身份验证时，该接口会被调用以定义响应方式，例如重定向到登录页面、返回401状态码或显示错误页面。
 * @date 2025/8/16 11:32
 */
@Component
public class UnauthenticatedEntryPoint implements AuthenticationEntryPoint {

    /**
     * 重写启动身份验证方案方法
     * @param request 客户端请求对象
     * @param response 服务器响应对象
     * @param authException 认证异常
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        GlobalRetrun.retrunResult(response,HttpServletResponse.SC_UNAUTHORIZED,"认证不通过");
    }
}
