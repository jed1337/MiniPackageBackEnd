package com.tw.apistackbase.services;

import com.tw.apistackbase.entity.Pickup;
import com.tw.apistackbase.repositories.PickupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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

        Optional<Pickup> foundPickup = pickupService.findById(1);

        assertThat(foundPickup.isPresent(), is(true));
    }

    @Test
    public void should_postNewPickup() {
        Pickup newPickup = new Pickup(1);

        when(pickupRepository.save(newPickup)).thenReturn(newPickup);

        Pickup foundPickup = pickupService.postNewPickup(newPickup);
        assertThat(newPickup, is(foundPickup));
    }
}