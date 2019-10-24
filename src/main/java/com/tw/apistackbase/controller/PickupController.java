package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Pickup;
import com.tw.apistackbase.services.PickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pickups")
public class PickupController {
    @Autowired
    private PickupService pickupService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code= HttpStatus.OK)
    @RequestMapping("/{id}")
    public Pickup findPickup(@PathVariable int id){
        return pickupService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code= HttpStatus.CREATED)
    public Pickup postNewPickup(@RequestBody Pickup newPickup){
        return pickupService.postNewPickup(newPickup);
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Pickup updateExistingPickup(@RequestBody Pickup newPickup) {
        return pickupService.updatePickup(newPickup);
    }
}
