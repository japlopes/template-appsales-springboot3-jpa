package com.modelmono.appwholesale.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelmono.appwholesale.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
