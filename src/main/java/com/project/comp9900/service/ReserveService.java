package com.project.comp9900.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.comp9900.dao.OrderMapper;
import com.project.comp9900.dao.ParkingMapper;
import com.project.comp9900.domain.entity.Order;
import com.project.comp9900.domain.entity.Parking;
import com.project.comp9900.domain.param.ReserveQuery;
import com.project.comp9900.utils.JsonData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@AllArgsConstructor
@Service
public class ReserveService {

    @Resource
    OrderMapper orderMapper;

    @Resource
    ParkingMapper parkingMapper;

    @Resource
    ReserveService reserveService;

    @Transactional
    public Object reserve(Order order) {
        //生成订单落库：
        orderMapper.insert(order);
        //更新park表：车位已经在使用
        parkingMapper.update(Parking.builder().IsUsing(1).build(),new QueryWrapper<Parking>().in("park_id",order.getParkId()));
        return JsonData.buildSuccess("预定成功");
    }

    public Object findById(Order order) {
        return JsonData.buildSuccess(reserveService.findOrderById(order.getOrderId()));
    }

    /**
     * 返回所有空闲车位id
     */
    public Object findParkList() {

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_using",0);
        List<Parking> parkingList = orderMapper.selectList(queryWrapper);
        return JsonData.buildSuccess(parkingList);
    }

    public Order findOrderById(Integer orderId){
        return orderMapper.selectOne(new QueryWrapper<Order>()
                .eq("order_id",orderId)
                .eq("id_deleted",0));
    }

    public Object CancelById(Order order) {
        orderMapper.update(Order.builder().IsDeleted(1).build(), new QueryWrapper<Order>()
                .eq("order_id",order.getOrderId()));
        //更新park表：车位不在使用
        parkingMapper.update(Parking.builder().IsUsing(0).build(),new QueryWrapper<Parking>().in("park_id",order.getParkId()));
        return JsonData.buildSuccess("cancel success");
    }
}
