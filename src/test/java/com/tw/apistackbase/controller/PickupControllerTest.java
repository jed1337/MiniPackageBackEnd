package com.tw.apistackbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.controllerBasis.CompanyController;
import com.tw.apistackbase.entity.Pickup;
import com.tw.apistackbase.services.PickupService;
import com.tw.apistackbase.servicesBasis.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        ResultActions result = mvc.perform(get("/pickup?name=a"));

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.pickupNumber", is(1)))
                .andExpect(jsonPath("$.pickupTime", is("12")));
    }
}