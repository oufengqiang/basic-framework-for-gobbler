package com.jufamen.user.server.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jufamen.gobbler.common.check.Add;
import com.jufamen.gobbler.common.check.Edit;
import com.jufamen.gobbler.common.entity.pojo.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 对应账号表t_account的账号实体类
 */
@Data
@TableName("t_account")
public class Account extends BaseEntity {

    @NotBlank(message = "账号不能为空",groups = {Add.class, Edit.class})
    private String account;

    @NotBlank(message = "密码不能为空",groups = {Add.class})
    private String password;

    private String salt;
}
