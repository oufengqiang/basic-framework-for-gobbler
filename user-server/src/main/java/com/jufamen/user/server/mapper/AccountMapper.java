package com.jufamen.user.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jufamen.user.server.entity.bo.AccountBo;
import com.jufamen.user.server.entity.dto.QueryAccountDto;
import com.jufamen.user.server.entity.pojo.Account;

import java.util.List;

/**
 * 账号CRUD封装方法
 */
/*
    com.baomidou.mybatisplus.core.mapper.BaseMapper是MyBatis-Plus框架中定义的一个接口，
    它继承自MyBatis的Mapper接口，并为MyBatis-Plus提供了额外的功能。
    BaseMapper接口定义了一系列常用的数据库操作方法，如插入（@Insert）、删除（@Delete）、更新（@Update）和查询（@Select）等。
    通过继承BaseMapper，可以获得如下功能：
        1、基本的CRUD操作：包括插入、删除、更新和查询等
        2、自动生成SQL：通过继承BaseMapper，可以不必手动编写大量的SQL语句，MyBatis-Plus会根据方法名和参数自动生成SQL
        3、条件构造器：提供了强大的条件构造器，可以方便地构建复杂的查询和更新条件
        4、分页插件：支持物理分页和自动分页，可以非常方便地实现分页查询。
        5、乐观锁：支持乐观锁，通过版本号控制并发更新问题
        6、批量操作：支持批量插入、批量更新和批量删除等操作
 */
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 分页查询账号信息
     * @param page 分页查询对象
     * @param queryAccountDto 分页查询账号参数
     * @return 需要返回查询结果
     */
    List<AccountBo> pageList(IPage<AccountBo> page, QueryAccountDto queryAccountDto);
}
