package com.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long orderId;
	    private LocalDate date;
	    private Long productId;
	    private int quantity;
	    private String status;
		public Order() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Order(Long orderId, LocalDate date, Long productId, int quantity, String status) {
			super();
			this.orderId = orderId;
			this.date = date;
			this.productId = productId;
			this.quantity = quantity;
			this.status = status;
		}
		public Long getOrderId() {
			return orderId;
		}
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
		public Long getProductId() {
			return productId;
		}
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		@Override
		public String toString() {
			return "Order [orderId=" + orderId + ", date=" + date + ", productId=" + productId + ", quantity="
					+ quantity + ", status=" + status + "]";
		}
	    
	    
}
