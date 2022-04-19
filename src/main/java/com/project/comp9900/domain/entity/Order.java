package com.project.comp9900.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("order_info")
public class Order {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * orderid
     */
    private Integer orderId;

    /**
     * userId
     */
    private Integer userId;

    /**
     * providerId
     */
    private Integer providerId;

    /**
     * parkId
     */
    private Integer parkId;

    /**
     * startTime
     */
    private String startTime;

    /**
     * endTime
     */
    private String endTime;

    /**
     * orderPrice
     */
    private Double orderPrice;

    @TableLogic
    private Integer IsDeleted;
}
