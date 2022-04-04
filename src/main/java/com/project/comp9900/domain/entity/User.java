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

@ApiModel("user_info")
@Data
@Builder
@TableName("user")
public class User {
    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("username")
    private String username;

    @ApiModelProperty("password")
    private String password;

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("idCardNum")
    private Integer idCardNum;

    @ApiModelProperty("phone")
    private String phone;

    @ApiModelProperty("status")
    private String status;

    @ApiModelProperty("level")
    private String level;

    @ApiModelProperty("IsDeleted")
    @TableLogic
    private Integer IsDeleted;
}
