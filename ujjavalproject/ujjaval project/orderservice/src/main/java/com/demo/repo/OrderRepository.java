package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
