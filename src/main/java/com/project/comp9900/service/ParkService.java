package com.project.comp9900.service;


import com.project.comp9900.dao.ParkingMapper;
import com.project.comp9900.domain.entity.Parking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@AllArgsConstructor
@Service
public class ParkService {

    @Resource
    ParkingMapper parkingMapper;


    public Object findParkList(Parking parking) {

    }
}
