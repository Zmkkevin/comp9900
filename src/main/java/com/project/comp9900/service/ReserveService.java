package com.project.comp9900.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.comp9900.dao.OrderMapper;
import com.project.comp9900.dao.ParkingMapper;
import com.project.comp9900.domain.entity.Order;
import com.project.comp9900.domain.entity.Parking;
import com.project.comp9900.utils.JsonData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@AllArgsConstructor
@Service
public class ReserveService {

    @Resource
    OrderMapper orderMapper;

    @Resource
    ParkingMapper parkingMapper;


    @Transactional
    public Object reserve(Order order) {

        if(checkIsReserve(order.getOrderId())){
            //生成订单落库：

            //查表：查provider_id:
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",order.getParkId());
            queryWrapper.eq("is_deleted",0);
            Parking park = parkingMapper.selectOne(queryWrapper);
            order.setProviderId(park.getProviderId());
            orderMapper.insert(order);
            //更新park表：车位已经在使用
            parkingMapper.update(Parking.builder().IsUsing(1).build(),new QueryWrapper<Parking>().in("id",order.getParkId()));
            return JsonData.buildSuccess("预定成功");
        }else{
            return JsonData.buildError(4001,"已存在该订单号");
        }

    }

    public Boolean checkIsReserve(Integer order_id){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",order_id);
        queryWrapper.eq("is_deleted",0);
        Order order = orderMapper.selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(order)){
            return true;
        }
        return false;
    }

    public Object findById(Order order) {
        return JsonData.buildSuccess(findOrderById(order.getOrderId()));
    }

    /**
     * 返回所有空闲车位id
     */
    public Object findParkList() {

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Is_using",0);
        List<Parking> parkingList = parkingMapper.selectList(queryWrapper);
        return JsonData.buildSuccess(parkingList);
    }

    public Order findOrderById(Integer orderId){
        return orderMapper.selectOne(new QueryWrapper<Order>()
                .eq("order_id",orderId)
                .eq("is_deleted",0));
    }

    public Object CancelById(Order order) {
        if(checkIsReserve(order.getOrderId())){
            return JsonData.buildError(4002,"不存在该订单号");
        }else{
            orderMapper.update(Order.builder().IsDeleted(1).build(), new QueryWrapper<Order>()
                    .eq("order_id",order.getOrderId()));
            //更新park表：车位不在使用
            parkingMapper.update(Parking.builder().IsUsing(0).build(),new QueryWrapper<Parking>().in("id",order.getParkId()));
            return JsonData.buildSuccess("cancel success");
        }

    }
}
