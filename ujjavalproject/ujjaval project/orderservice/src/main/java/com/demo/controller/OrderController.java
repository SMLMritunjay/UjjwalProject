package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Order;
import com.demo.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	 @Autowired
	    private OrderService orderService;

	    @GetMapping
	    public List<Order> getAllOrders() {
	        return orderService.getAllOrders();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
	        Order order = orderService.getOrderById(id);
	        if (order != null) {
	            return new ResponseEntity<>(order, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @PostMapping
	    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
	        Order placedOrder = orderService.placeOrder(order);
	        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
	    }

}
