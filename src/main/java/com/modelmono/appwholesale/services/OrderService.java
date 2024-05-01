package com.modelmono.appwholesale.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.modelmono.appwholesale.entities.Order;
import com.modelmono.appwholesale.entities.OrderItem;
import com.modelmono.appwholesale.entities.Payment;
import com.modelmono.appwholesale.entities.enums.OrderStatus;
import com.modelmono.appwholesale.entities.enums.PaymentStatus;
import com.modelmono.appwholesale.repositories.OrderItemRepository;
import com.modelmono.appwholesale.repositories.OrderRepository;
import com.modelmono.appwholesale.services.exceptions.DatabaseException;
import com.modelmono.appwholesale.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
		


	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Long idOrder) {
		Optional<Order> obj = orderRepository.findById(idOrder);
		return obj.get();

	}

	public Order insert(Order obj) {
		obj.setMoment(Instant.now());
		obj.setOrderStatus(OrderStatus.ORDER_IN_PROCESSING);
		Payment pay = new Payment(null,Instant.now(), PaymentStatus.PENDING_PAYMENT, obj);
		obj.setPayment(pay);
		obj = orderRepository.save(obj);
				
		for (OrderItem oi : obj.getItems()) {
			oi.setPrice(productService.findById(oi.getProduct().getIdProduct()).getPrice());
			oi.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		return obj;
	}

	public void delete(Long idOrder) {
		try {
			orderRepository.deleteById(idOrder);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idOrder);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Order update(Long idOrder, Order obj) {
		try {
			Order entity = orderRepository.getReferenceById(idOrder);
			updateData(entity, obj);
			return orderRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(idOrder);
		}
	}

	private void updateData(Order entity, Order obj) {
		entity.setMoment(obj.getMoment());
		entity.setOrderStatus(obj.getOrderStatus());
		entity.setPayment(obj.getPayment());
	} 

}
