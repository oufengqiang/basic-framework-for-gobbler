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
 * @author Jufamen
 * @description 自定义访问未授权资源时的响应结果
 *  AccessDeniedHandler是Spring Security框架中用于处理访问被拒绝的组件，当用户尝试访问无权限的资源时触发。
 * @date 2025/8/16 10:01
 */
@Component
public class UnauthorizedAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        GlobalRetrun.retrunResult(response,HttpServletResponse.SC_FORBIDDEN,"资源未授权");
    }
}
