package com.jufamen.user.server.entity.dto;

import com.jufamen.gobbler.common.entity.dto.QueryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 账号查询实体类
 */
@Data
@Schema(description = "账号查询对象")
public class QueryAccountDto extends QueryDto {

    /**
     * 账号
     */
    @Schema(description = "账号", maxLength = 20)
    private String account;
}
