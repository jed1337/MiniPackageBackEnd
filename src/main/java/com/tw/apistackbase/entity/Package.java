package com.tw.apistackbase.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int packageNumber;

    private String receiver;
    private String phoneNumber;
    private String weight;

    @OneToOne
    @Cascade(value = CascadeType.ALL)
    private Pickup pickup;

    public Package() {
    }

    public Package(int packageNumber) {
        this.packageNumber = packageNumber;
    }

    public int getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(int packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Pickup getPickup() {
        return pickup;
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return packageNumber == aPackage.packageNumber &&
                Objects.equals(receiver, aPackage.receiver) &&
                Objects.equals(phoneNumber, aPackage.phoneNumber) &&
                Objects.equals(weight, aPackage.weight) &&
                Objects.equals(pickup, aPackage.pickup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageNumber, receiver, phoneNumber, weight, pickup);
    }
}
