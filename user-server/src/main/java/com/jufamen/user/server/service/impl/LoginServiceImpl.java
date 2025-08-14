package com.jufamen.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jufamen.gobbler.common.response.Result;
import com.jufamen.gobbler.common.response.ResultEnum;
import com.jufamen.user.server.entity.dto.LoginDto;
import com.jufamen.user.server.entity.pojo.Account;
import com.jufamen.user.server.mapper.AccountMapper;
import com.jufamen.user.server.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 登录接口实现类
 */
@Slf4j
/*
    @Service注解表示该类是一个服务层组件，通常用于实现业务逻辑，表示该 Bean 包含业务逻辑，可以提高代码的可读性，并使得服务层的角色更明确
    com.baomidou.mybatisplus.extension.service.impl.ServiceImpl是MyBatis-Plus框架中的一个核心实现类，
    提供了许多便捷的方法来简化CRUD操作。
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    /**
     * 账号CRUD封装方法实例
     */
    private final AccountMapper accountMapper;

    /**
     * 登录
     * @param loginDto 登录参数
     * @return 需要返回接口响应结果
     */
    @Override
    public Result<Object> login(LoginDto loginDto) {
        // 根据账号查询账号信息
        Account account = accountMapper.selectOne(
                new QueryWrapper<Account>().eq("account",loginDto.getAccount()));
        if (account == null){ // 账号信息为空
            return Result.failed(ResultEnum.CUSTOMIZE.getCode(),"账号不存在");
        }
        if (!account.getPassword().equals(loginDto.getPassword())){
            return Result.failed(ResultEnum.CUSTOMIZE.getCode(),"密码不正确");
        }
        return Result.success(account);
    }
}
