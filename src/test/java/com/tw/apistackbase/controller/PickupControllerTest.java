package com.tw.apistackbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.entity.Pickup;
import com.tw.apistackbase.services.PickupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PickupController.class)
@ActiveProfiles(profiles = "test")
public class PickupControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PickupService pickupService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_getSinglePickup() throws Exception {
        Pickup newPickup = new Pickup(1);
        newPickup.setPickupTime("12");
        when(pickupService.findById(1)).thenReturn(newPickup);

        ResultActions result = mvc.perform(get("/pickups/1"));

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.pickupNumber", is(1)))
                .andExpect(jsonPath("$.pickupTime", is("12")));
    }

    @Test
    public void should_post_newPickup() throws Exception {
        Pickup newPickup = new Pickup(1);
        newPickup.setPickupTime("12");
        when(pickupService.postNewPickup(newPickup)).thenReturn(newPickup);

        ResultActions result = mvc.perform(post("/pickups/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPickup))
        );

        result.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.pickupNumber", is(1)))
                .andExpect(jsonPath("$.pickupTime", is("12")));
    }

    @Test
    public void should_update_existingPickup() throws Exception {
        Pickup existingPickup = new Pickup(1);
        existingPickup.setPickupTime("12");
        when(pickupService.updatePickup(existingPickup)).thenReturn(existingPickup);

        ResultActions result = mvc.perform(patch("/pickups/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(existingPickup))
        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.pickupNumber", is(1)))
                .andExpect(jsonPath("$.pickupTime", is("12")));

    }
}
