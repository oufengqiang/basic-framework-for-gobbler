package com.jufamen.user.server.entity.dto;

import com.jufamen.gobbler.common.entity.dto.QueryDto;
import lombok.Data;

/**
 * 账号查询实体类
 */
@Data
public class QueryAccountDto extends QueryDto {

    /**
     * 账号
     */
    private String account;
}
