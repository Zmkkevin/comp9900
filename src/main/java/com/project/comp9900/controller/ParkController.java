package com.project.comp9900.controller;

import com.project.comp9900.dao.ParkingMapper;
import com.project.comp9900.domain.entity.Order;
import com.project.comp9900.domain.entity.Parking;
import com.project.comp9900.service.ParkService;
import com.project.comp9900.service.ReserveService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@AllArgsConstructor
@RestController
@RequestMapping("/operatePark")
public class ParkController {

    @Resource
    ParkService parkService;

    /**
     * 上传停车位信息
     */
    @PostMapping("/newPark")
    public Object findParkList(@RequestBody Parking parking) {
        return parkService.findParkList(parking);
    }
}
