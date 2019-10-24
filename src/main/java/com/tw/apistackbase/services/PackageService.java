package com.tw.apistackbase.services;

import com.tw.apistackbase.entity.Package;
import com.tw.apistackbase.repositories.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;


    public Package findById(int packageNumber) {
        Optional<Package> foundPackage = packageRepository.findById(packageNumber);
        if (foundPackage.isPresent()) {
            return foundPackage.get();
        }
        throw new IllegalArgumentException(String.format("Package with id %d not found", packageNumber));
    }
}
