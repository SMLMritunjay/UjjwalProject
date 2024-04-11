package com.demo.service;

import java.util.List;

import com.demo.entity.Order;

public interface OrderService {
	 List<Order> getAllOrders();
	    Order getOrderById(Long id);
	    Order placeOrder(Order order);
}
