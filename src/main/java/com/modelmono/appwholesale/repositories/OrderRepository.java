package com.modelmono.appwholesale.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelmono.appwholesale.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
