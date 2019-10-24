package com.tw.apistackbase.services;

import com.tw.apistackbase.entity.Pickup;
import com.tw.apistackbase.repositories.PickupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PickupService {
    @Autowired
    private PickupRepository pickupRepository;

    public Optional<Pickup> findById(int id){
        return pickupRepository.findById(id);
    }

    public Pickup postNewPickup(Pickup pickup) {
        return pickupRepository.save(pickup);
    }
}
