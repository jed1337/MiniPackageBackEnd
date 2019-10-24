package com.tw.apistackbase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pickup {
    @Id
    private int pickupNumber;

    @Column
    private String pickupTime;

    public Pickup() {
    }

    public Pickup(int pickupNumber) {
        this.pickupNumber = pickupNumber;
    }

    public int getPickupNumber() {
        return pickupNumber;
    }

    public void setPickupNumber(int pickupNumber) {
        this.pickupNumber = pickupNumber;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }
}
