package com.tw.apistackbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.entity.Package;
import com.tw.apistackbase.services.PackageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PackageController.class)
@ActiveProfiles(profiles = "test")
public class PackageControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PackageService packageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_getSinglePackage() throws Exception {
        Package newPackage = new Package(1);
        newPackage.setPhoneNumber("12");
        when(packageService.findById(1)).thenReturn(newPackage);

        ResultActions result = mvc.perform(get("/packages/1"));

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.packageNumber", is(1)))
                .andExpect(jsonPath("$.phoneNumber", is("12")));
    }
}
