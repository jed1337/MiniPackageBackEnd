package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Package;
import com.tw.apistackbase.entity.Package;
import com.tw.apistackbase.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packages")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code= HttpStatus.OK)
    @RequestMapping("/{id}")
    public Package findPackage(@PathVariable int id){
        return packageService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code= HttpStatus.CREATED)
    public Package postNewPackage(@RequestBody Package newPackage){
        return packageService.postNewPackage(newPackage);
    }


}
