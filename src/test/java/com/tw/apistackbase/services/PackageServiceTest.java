package com.tw.apistackbase.services;

import com.tw.apistackbase.entity.Package;
import com.tw.apistackbase.repositories.PackageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PackageServiceTest {
    @Autowired
    private PackageService packageService;

    @MockBean
    private PackageRepository packageRepository;

    @Test
    public void should_findById() {
        when(packageRepository.findById(1)).thenReturn(Optional.of(new Package(1)));

        Package foundPackage = packageService.findById(1);

        assertThat(foundPackage.getPackageNumber(), is(1));
    }

    @Test
    public void should_throw_exception_if_cannot_findById() {
        assertThrows(IllegalArgumentException.class, () -> {
            packageService.findById(-1);
        });
    }
}

