package com.modelmono.appwholesale.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelmono.appwholesale.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
