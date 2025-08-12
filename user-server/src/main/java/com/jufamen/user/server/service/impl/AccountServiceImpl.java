package com.jufamen.user.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jufamen.gobbler.common.response.Result;
import com.jufamen.user.server.entity.bo.AccountBo;
import com.jufamen.user.server.entity.dto.QueryAccountDto;
import com.jufamen.user.server.entity.pojo.Account;
import com.jufamen.user.server.mapper.AccountMapper;
import com.jufamen.user.server.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
/*
    @Service注解表示该类是一个服务层组件，通常用于实现业务逻辑，表示该 Bean 包含业务逻辑，可以提高代码的可读性，并使得服务层的角色更明确
    com.baomidou.mybatisplus.extension.service.impl.ServiceImpl是MyBatis-Plus框架中的一个核心实现类，
    提供了许多便捷的方法来简化CRUD操作。
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    /**
     * 分页查询账号信息
     * @param page 分页查询对象
     * @param queryAccountDto 分页查询账号参数
     * @return 需要返回接口响应结果
     */
    @Override
    public Result<Object> pageList(IPage<AccountBo> page, QueryAccountDto queryAccountDto) {
        List<AccountBo> list = baseMapper.pageList(page,queryAccountDto);
        page.setRecords(list);
        return Result.success(page);
    }

    /**
     * 根据ID获取账号信息
     * @param id 账号ID
     * @return 需要返回接口响应结果
     */
    @Override
    public Result<Object> view(Integer id) {
        Account account = baseMapper.selectById(id);
        return Result.success(account);
    }

    /**
     * 添加账号
     * @param account 添加的账号信息实体对象
     * @return 需要返回接口响应结果
     */
    @Override
    public Result<Object> add(Account account) {
        log.info("添加账号");
        if(baseMapper.insert(account) > 0){
            return Result.success();
        }
        return Result.failed();
    }

    /**
     * 编辑账号
     * @param account 编辑的账号信息实体对象
     * @return 需要返回接口响应结果
     */
    @Override
    public Result<Object> edit(Account account) {
        if(baseMapper.updateById(account) > 0){
            return Result.success();
        }
        return Result.failed();
    }

    /**
     * 删除账号
     * @param account 删除的账号信息实体对象
     * @return 需要返回接口响应结果
     */
    @Override
    public Result<Object> delete(Account account) {
        if(baseMapper.deleteById(account) > 0){
            return Result.success();
        }
        return Result.failed();
    }
}
