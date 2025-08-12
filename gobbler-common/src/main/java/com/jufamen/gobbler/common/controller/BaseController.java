package com.jufamen.gobbler.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jufamen.gobbler.common.entity.dto.QueryDto;

public class BaseController {

    /**
     * 封装分页查询参数
     * @param queryDto 分页查询参数
     * @return 返回分页查询参数对象
     * @param <T> 接收分页参数形参
     */
    protected <T> IPage<T> buildPage(QueryDto queryDto) {
        long current = queryDto.getCurrent();
        long size = queryDto.getSize();
        return new Page<T>(current,size);
    }
}
