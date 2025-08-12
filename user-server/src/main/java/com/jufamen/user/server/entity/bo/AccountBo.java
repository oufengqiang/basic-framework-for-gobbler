package com.jufamen.user.server.entity.bo;

import com.jufamen.gobbler.common.entity.bo.BaseBo;
import lombok.Data;

/**
 * 账号业务对象类
 */
@Data
public class AccountBo extends BaseBo {

    private String account;

    private String password;

    private String salt;
}
