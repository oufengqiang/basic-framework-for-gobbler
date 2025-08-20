package com.jufamen.gobbler.gateway.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.jufamen.gobbler.gateway.utils.GlobalRetrun;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @description JWT校验过滤器
 *  OncePerRequestFilter是Spring框架提供的一种特殊过滤器，用于确保在一次完整的HTTP请求中，
 *  无论请求经过多少次内部转发（如服务器内部转发或包含多个资源请求），过滤器的逻辑仅执行一次。
 *  这种设计主要用于需要一次性完成资源初始化、安全检查或日志记录等操作的场景，以避免重复执行带来的性能损耗。
 * @author Jufamen
 * @date 2025/8/16 15:56
 */
@Slf4j
public class JwtValidationFilter extends OncePerRequestFilter {
    /**
     * 请求头参数HttpHeaders.AUTHORIZATION的属性值的前缀
     * Bearer Token是一种常用的身份验证方式，用于保护API的访问权限
     */
    private static final String PREFIX = "Bearer ";
    /**
     *  UserDetailsService是Spring Security中用于加载用户信息的核心接口，主要用于身份认证过程中根据用户名检索用户信息。
     */
    private final UserDetailsService userDetailsService;
    /**
     * JWT签名密钥
     */
    private final byte[] secretKey;
    public JwtValidationFilter(UserDetailsService userDetailsService, String secretKey) {
        this.userDetailsService = userDetailsService;
        this.secretKey = secretKey.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * doFilterInternal是OncePerRequestFilter类中的抽象方法，用于实现过滤器的具体逻辑。
     * @param request 请求对象
     * @param response 响应对象
     * @param filterChain 过滤器链
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        // JWT本身并不强制要求令牌（Token）以Bearer开头，但在HTTP请求的Authorization头部中，Bearer是OAuth 2.0协议规定的认证方案标识符，用于明确表示令牌类型。
        if (StringUtils.isEmpty(token) || !token.startsWith(PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String uri = request.getRequestURI();// 客户端请求URI
        log.info("客户端请求：{}，进入JWT校验过滤器，Token是：{}", uri, token);
        token = token.substring(PREFIX.length());
        JWT jwt = JWTUtil.parseToken(token);
        if (!jwt.setKey(secretKey).verify()) {// 校验JWT无效
            log.warn("客户端请求：{}，JWT校验不通过，因为TOKEN无效", uri);
            GlobalRetrun.retrunResult(response,HttpServletResponse.SC_UNAUTHORIZED,"TOKEN无效");
            return;
        }
        JWTPayload payload = jwt.getPayload();
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(payload.getClaimsJson().getStr(JWTPayload.SUBJECT));
        // 在Spring Security中，UsernamePasswordAuthenticationToken是用于封装用户认证信息的核心类。手动构建一个已认证的Authentication对象，并设置到SecurityContextHolder中。
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // 将当前HTTP请求的附加信息（如IP地址、Session ID）绑定到认证对象（Authentication）中，以便后续流程（如权限校验、日志记录）使用。
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 把构建的Authentication对象设置到SecurityContextHolder中
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("客户端请求：{}，JWT校验通过", uri);
        // 过滤器链执行的核心方法，其作用是将请求传递给下一个过滤器或目标资源
        filterChain.doFilter(request, response);
    }
}
