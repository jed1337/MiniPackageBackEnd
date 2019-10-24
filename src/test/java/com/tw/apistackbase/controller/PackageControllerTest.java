package com.tw.apistackbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.entity.Package;
import com.tw.apistackbase.entity.Package;
import com.tw.apistackbase.services.PackageService;
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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void should_getAllPackages() throws Exception {
        List<Package> packageList = Arrays.asList(
                new Package(1),
                new Package(2)
        );

        when(packageService.getAllPackages()).thenReturn(packageList);

        ResultActions result = mvc.perform(get("/packages/"));

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].packageNumber", is(1)))
                .andExpect(jsonPath("$[1].packageNumber", is(2)));
    }

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

    @Test
    public void should_post_newPackage() throws Exception {
        Package newPackage = new Package(1);
        newPackage.setPhoneNumber("12");
        when(packageService.postNewPackage(newPackage)).thenReturn(newPackage);

        ResultActions result = mvc.perform(post("/packages/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPackage))
        );

        result.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.packageNumber", is(1)))
                .andExpect(jsonPath("$.phoneNumber", is("12")));
    }
}
