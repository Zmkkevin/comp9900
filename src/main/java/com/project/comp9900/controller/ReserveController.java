package com.project.comp9900.controller;


import com.project.comp9900.dao.ParkingMapper;
import com.project.comp9900.domain.entity.Order;
import com.project.comp9900.domain.entity.Parking;
import com.project.comp9900.domain.entity.User;
import com.project.comp9900.domain.param.ReserveQuery;
import com.project.comp9900.service.AccountService;
import com.project.comp9900.service.ReserveService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/reserve")
public class ReserveController {



    @Resource
    ReserveService reserveService;
    /**
     * 预定车位
     */
    @PostMapping("/reservePark")
    public Object reserve(@RequestBody Order order) {
        return reserveService.reserve(order);
    }

    @PostMapping("/findById")
    public Object findById(@RequestBody Order order) {
        return reserveService.findById(order);
    }

    /**
     * 返回所有空闲车位id
     */
    @PostMapping("/findParkList")
    public Object findParkList() {
        return reserveService.findParkList();
    }

    /**
     * 取消预定车位
     */
    @PostMapping("/cancelReverse")
    public Object findParkList(@RequestBody Order order) {
        return reserveService.CancelById(order);
    }
}
