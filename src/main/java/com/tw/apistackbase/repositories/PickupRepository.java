package com.tw.apistackbase.repositories;

import com.tw.apistackbase.entity.Pickup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupRepository extends JpaRepository<Pickup, Integer> {
}
