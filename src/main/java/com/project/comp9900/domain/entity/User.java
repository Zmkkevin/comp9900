package com.project.comp9900.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;

@ApiModel("系统用户")
@Data
@Builder
@TableName("user")
public class User {
    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("登录名,备用字段,目前随机自动生成")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("身份ID")
    private Integer idCardNum;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("角色级别")
    private String level;

    @ApiModelProperty("删除标志")
    @TableLogic
    private Integer IsDeleted;
}
