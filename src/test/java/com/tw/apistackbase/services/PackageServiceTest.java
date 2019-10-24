package com.tw.apistackbase.services;

import com.tw.apistackbase.entity.Package;
import com.tw.apistackbase.entity.Package;
import com.tw.apistackbase.repositories.PackageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
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
    public void should_getAllPackages() {
        List<Package> packageList = Arrays.asList(
                new Package(1),
                new Package(2)
        );
        when(packageRepository.findAll()).thenReturn(packageList);

        List<Package> allPackages = packageService.getAllPackages();

        assertThat(allPackages, hasSize(2));
        assertThat(allPackages.get(0).getPackageNumber(), is(1));
        assertThat(allPackages.get(1).getPackageNumber(), is(2));
    }

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

    @Test
    public void should_postNewPackage() {
        Package newPackage = new Package(1);
        when(packageRepository.save(newPackage)).thenReturn(newPackage);

        Package foundPackage = packageService.postNewPackage(newPackage);

        assertThat(newPackage, is(foundPackage));
    }

}

