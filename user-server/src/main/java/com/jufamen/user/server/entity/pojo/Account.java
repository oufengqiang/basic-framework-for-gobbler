package com.jufamen.user.server.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jufamen.gobbler.common.check.Add;
import com.jufamen.gobbler.common.check.Edit;
import com.jufamen.gobbler.common.entity.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 对应账号表t_account的账号实体类
 */
@Data
@Schema(description = "映射t_account表账号对象")
@TableName("t_account")
public class Account extends BaseEntity {

    @Schema(description = "账号", example = "添加/修改时，账号必填参数", minLength = 4, maxLength = 20, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "账号不能为空",groups = {Add.class, Edit.class})
    private String account;

    @Schema(description = "账号", example = "添加时，账号必填参数", minLength = 4, maxLength = 20, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "密码不能为空",groups = {Add.class})
    private String password;

    @Schema(description = "加密因子", example = "添加时，系统自动生成")
    private String salt;
}
