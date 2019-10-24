package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Pickup;
import com.tw.apistackbase.entityBasis.Company;
import com.tw.apistackbase.services.PickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/pickups")
public class PickupController {
    @Autowired
    private PickupService pickupService;

    @GetMapping(produces = {"application/json"})
    public Pickup findCompany(@RequestParam(required = false) int id){
        return pickupService.findById(id);
    }
}
