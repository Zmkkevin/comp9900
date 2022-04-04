package com.project.comp9900.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.comp9900.dao.ParkingMapper;
import com.project.comp9900.domain.entity.Order;
import com.project.comp9900.domain.entity.Parking;
import com.project.comp9900.utils.JsonData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@AllArgsConstructor
@Service
public class ParkService {

    @Resource
    ParkingMapper parkingMapper;

    public Object newPark(Parking parking) {

        parkingMapper.insert(parking);
        return JsonData.buildSuccess("新车位信息创建成功");
    }

    public Object deletePark(Parking parking) {
        parkingMapper.update(Parking.builder().IsDeleted(1).build(), new QueryWrapper<Parking>()
                .eq("park_id",parking.getId()));
        return JsonData.buildSuccess("新车位信息删除成功");
    }
}
