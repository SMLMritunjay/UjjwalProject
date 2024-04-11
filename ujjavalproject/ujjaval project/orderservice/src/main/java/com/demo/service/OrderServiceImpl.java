package com.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.entity.Order;
import com.demo.payload.Product;
import com.demo.repo.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public Order placeOrder(Order order) {
		// Check if the product is available in the inventory
		ResponseEntity<Product> responseEntity = webClientBuilder.build().get()
				.uri("http://localhost:8080/api/inventory/" + order.getProductId()).retrieve().toEntity(Product.class)
				.block();

		if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
			Product product = responseEntity.getBody();
			if (product.getQuantity() >= order.getQuantity()) {
				// Reduce the quantity of the product in inventory
				product.setQuantity(product.getQuantity() - order.getQuantity());
				webClientBuilder.build().put().uri("http://localhost:8080/api/inventory/" + product.getProductId())
						.body(BodyInserters.fromValue(product)).retrieve().toBodilessEntity().block();

				// Set order status to placed
				order.setDate(LocalDate.now());
				order.setStatus("Placed");
			} else {
				// Set order status to failed
				order.setStatus("Failed");
			}
		} else {
			// Set order status to failed
			order.setStatus("Failed");
		}

		// Save the order
		return orderRepository.save(order);
	}

}
