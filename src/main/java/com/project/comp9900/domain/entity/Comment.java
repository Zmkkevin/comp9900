package com.project.comp9900.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("comment_info")
public class Comment {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *userID
     */
    private Integer userId;

    /**
     * comment
     */
    private String comment;

    @TableLogic
    private Integer IsDeleted;
}
