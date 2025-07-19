package com.sree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sree.entity.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {

}
