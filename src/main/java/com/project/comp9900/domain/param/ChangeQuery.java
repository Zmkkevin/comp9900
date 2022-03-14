package com.project.comp9900.domain.param;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeQuery {

    /**
     * 用户名
     */
    String userName;

    /**
     * 老密码
     */
    String oldPassword;

    /**
     * 新密码
     */
    String newPassword;

}
