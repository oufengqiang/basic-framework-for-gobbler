package com.jufamen.user.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jufamen.gobbler.common.response.Result;
import com.jufamen.user.server.entity.bo.AccountBo;
import com.jufamen.user.server.entity.dto.QueryAccountDto;
import com.jufamen.user.server.entity.pojo.Account;

/**
 * 账号接口
 */
/*
    com.baomidou.mybatisplus.extension.service.IService是MyBatis-Plus框架中的一个接口，
    为开发者提供了一种便捷的方式来操作数据库中的数据
 */
public interface AccountService extends IService<Account> {

    /**
     *
     * 分页查询账号信息
     * @param page 分页查询对象
     * @param queryAccountDto 分页查询账号参数
     * @return 需要返回接口响应结果
     */
    Result<Object> pageList(IPage<AccountBo> page, QueryAccountDto queryAccountDto);

    /**
     * 根据ID获取账号信息
     * @param id 账号ID
     * @return 需要返回接口响应结果
     */
    Result<Object> view(Integer id);

    /**
     * 添加账号
     * @param account 添加的账号信息实体对象
     * @return 需要返回接口响应结果
     */
    Result<Object> add(Account account);

    /**
     * 编辑账号
     * @param account 编辑的账号信息实体对象
     * @return 需要返回接口响应结果
     */
    Result<Object> edit(Account account);

    /**
     * 删除账号
     * @param account 删除的账号信息实体对象
     * @return 需要返回接口响应结果
     */
    Result<Object> delete(Account account);
}
