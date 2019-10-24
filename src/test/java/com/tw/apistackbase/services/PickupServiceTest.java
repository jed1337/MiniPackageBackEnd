package com.tw.apistackbase.services;

import com.tw.apistackbase.entity.Pickup;
import com.tw.apistackbase.repositories.PickupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PickupServiceTest {
    @Autowired
    private PickupService pickupService;

    @MockBean
    private PickupRepository pickupRepository;

    @Test
    public void should_findById() {
        when(pickupRepository.findById(1)).thenReturn(Optional.of(new Pickup(1)));

        Pickup foundPickup = pickupService.findById(1);

        assertThat(foundPickup.getPickupNumber(), is(1));
    }

    @Test
    public void should_throw_exception_if_cannot_findById() {
        assertThrows(IllegalArgumentException.class, () -> {
            pickupService.findById(-1);
        });
    }

    @Test
    public void should_postNewPickup() {
        Pickup newPickup = new Pickup(1);
        when(pickupRepository.save(newPickup)).thenReturn(newPickup);

        Pickup foundPickup = pickupService.postNewPickup(newPickup);

        assertThat(newPickup, is(foundPickup));
    }

    @Test
    public void should_patchExistingPickup() {
        Pickup existingPickup = new Pickup(1);
        existingPickup.setPickupTime("10");

        when(pickupRepository.findById(1)).thenReturn(Optional.of(existingPickup));

        Pickup updatedPickup = new Pickup(1);
        updatedPickup.setPickupTime("12");

        when(pickupRepository.save(updatedPickup)).thenReturn(updatedPickup);

        assertThat(pickupService.updatePickup(updatedPickup).getPickupTime(), is("12"));
    }

    @Test
    public void should_throw_exception_if_cannot_find_existingPickup_to_patch() {
        when(pickupRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {
            pickupService.updatePickup(new Pickup(1));
        });
    }
}