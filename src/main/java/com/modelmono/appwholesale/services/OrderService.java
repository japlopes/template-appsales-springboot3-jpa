package com.modelmono.appwholesale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelmono.appwholesale.entities.Order;
import com.modelmono.appwholesale.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	public Order findById(Long idOrder) {
		Optional<Order> obj = orderRepository.findById(idOrder);
		return obj.get();
		
	}

}
