package com.jufamen.gobbler.gateway.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jufamen
 * @description 自定义网关全局响应结果工具类
 * @date 2025/8/18 14:02
 */
@Slf4j
public class GlobalRetrun {

    /**
     * 根据响应状态码code和响应消息message自定义服务响应结果
     * @param response 响应对象
     * @param code 响应状态码
     * @param message 响应消息
     */
    public static void retrunResult(HttpServletResponse response, int code, String message) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            // 使用SpringBoot自带的Jackson封装响应结果
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String,Object> result = new HashMap<>();
            result.put("code", code);
            result.put("message", message);
            // 用于向客户端发送数据
            response.getWriter().println(objectMapper.writeValueAsString(result));
            // 用于强制将缓冲区中的数据发送到客户端
            response.getWriter().flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
