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

    public Pickup updatePickup(int id, Pickup updatedPickup) {
        Optional<Pickup> optionalPickup = pickupRepository.findById(id);

        if (optionalPickup.isPresent()) {
            Pickup existingPickup = optionalPickup.get();
            existingPickup.setPickupTime(updatedPickup.getPickupTime());

            return pickupRepository.save(existingPickup);
        } else {
            return null;
        }
    }
}
