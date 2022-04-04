package com.project.comp9900.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@TableName("park_info")
public class Parking {

    /**
     * id
     */
    @TableId("id")
    private Integer id;

    /**
     * IsUsing 0:no 1:using
     */
    private Integer IsUsing;

    /**
     * providerId
     */
    private Integer providerId;

    /**
     * Location
     */
    private String Location;

    /**
     * GPS point
     */
    private String point;

    @TableLogic
    private Integer IsDeleted;
}
