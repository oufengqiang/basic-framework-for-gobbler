package com.jufamen.gobbler.gateway.config;

import com.jufamen.gobbler.gateway.utils.GlobalRetrun;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description 自定义访问未授权资源时的响应结果
 *  AccessDeniedHandler是Spring Security框架中用于处理访问被拒绝的组件，当用户尝试访问无权限的资源时触发。
 * @author Jufamen
 * @date 2025/8/16 10:01
 */
@Component
public class UnauthorizedAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 重写未授权异常处理方法
     * @param request 请求对象
     * @param response 响应对象
     * @param accessDeniedException  AccessDeniedException是Java安全框架中常见的运行时异常，表示当前安全上下文中的主体（如用户或进程）试图执行未被授权的操作。该异常通常由权限不足、配置错误或文件/资源被占用导致。
     * @throws IOException IOException是Java中的一个异常类，属于java.io包。它表示在输入/输出操作过程中可能发生的各种错误或异常情况。IOException是Java中表示I/O操作失败时抛出的最顶层的异常，很多具体的I/O异常都是它的子类
     * @throws ServletException ServletException是Java Servlet API中的一个异常类，用于表示在处理Servlet请求或响应时发生的异常情况。
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        GlobalRetrun.retrunResult(response,HttpServletResponse.SC_FORBIDDEN,"资源未授权");
    }
}
