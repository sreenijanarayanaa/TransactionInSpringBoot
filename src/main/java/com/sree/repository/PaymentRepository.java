package com.sree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sree.entity.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {

}
