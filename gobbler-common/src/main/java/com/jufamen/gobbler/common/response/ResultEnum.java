package com.jufamen.gobbler.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
    枚举类型（enum）是编程中用于定义一组具名常量的数据类型，常用于表示有限且相关的离散值集合
    是一种用户自定义的数据类型，通过关键字enum声明，用于将一组相关的常量组织在一起。
    其核心优势在于提升代码可读性和安全性，避免使用魔法数字或松散常量
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(200,"请求响应成功"), // 请求成功，服务器已成功处理了请求，且完成了业务逻辑
    FAILED(300,"请求响应失败"), // 请求失败，服务器已成功处理了请求，但未完成业务逻辑
    BAD_REQUEST(400,"请求格式错误"), // 服务器无法理解请求的格式，客户端不应当再次使用相同的内容发起请求。
    UNAUTHORIZED(401,"请求需要认证"), // 请求要求用户的身份认证
    FORBIDDEN(403,"请求没有权限"), // 服务器理解请求但是拒绝执行
    NOT_FOUND(404,"请求不存在"), // 服务器无法根据客户端的请求找到资源
    TOO_MANY_REQUESTS(429,"请求频率过高"), // 请求频率过高（限流）
    INTERNAL_SERVER_ERROR(500,"服务器内部错误，无法完成请求"), // 服务器内部错误，无法完成请求
    BAD_GATEWAY(502,"网关无响应"), // 服务器作为网关或代理，从上游服务器收到无效响应
    SERVICE_UNAVAILABLE(503,"服务器无响应"), // 服务器目前无法使用（由于超载或停机维护）。通常只是暂时状态
    GATEWAY_TIMEOUT(504,"网关超时"), // 服务器作为网关或代理，但是没有及时从上游服务器收到请求。
    CUSTOMIZE(600,"自定义服务器响应"); // 自定义服务器响应结果

    private final int code;

    private final String message;

}
