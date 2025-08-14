package com.jufamen.user.server.service;

import com.jufamen.gobbler.common.response.Result;
import com.jufamen.user.server.entity.dto.LoginDto;

public interface LoginService {

    /**
     * 登录
     * @param loginDto 登录参数
     * @return 需要返回接口响应结果
     */
    Result<Object> login(LoginDto loginDto);
}
