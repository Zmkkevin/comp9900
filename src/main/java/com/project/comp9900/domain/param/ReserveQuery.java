package com.project.comp9900.domain.param;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReserveQuery {

    /**
     * orderid
     */
    private Integer orderId;

    /**
     * userId
     */
    private Integer userName;

    /**
     * parkID
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
    private String orderPrice;
}
