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

    public Pickup findById(int id){
        Optional<Pickup> foundPickup = pickupRepository.findById(id);
        if (foundPickup.isPresent()) {
            return foundPickup.get();
        }

        throw new IllegalArgumentException(String.format("Pickup with id %d not found", id));
    }

    public Pickup postNewPickup(Pickup pickup) {
        return pickupRepository.save(pickup);
    }

    public Pickup updatePickup(Pickup updatedPickup) {
        Pickup foundPickup = findById(updatedPickup.getPickupNumber());

        foundPickup.setPickupTime(updatedPickup.getPickupTime());

        return pickupRepository.save(foundPickup);
    }
}
