package com.tw.apistackbase.services;

import com.tw.apistackbase.entity.Pickup;
import com.tw.apistackbase.entityBasis.Company;
import com.tw.apistackbase.repositories.PickupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
