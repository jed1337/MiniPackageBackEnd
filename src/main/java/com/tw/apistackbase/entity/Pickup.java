package com.tw.apistackbase.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Pickup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pickup pickup = (Pickup) o;
        return pickupNumber == pickup.pickupNumber &&
                Objects.equals(pickupTime, pickup.pickupTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickupNumber, pickupTime);
    }
}
