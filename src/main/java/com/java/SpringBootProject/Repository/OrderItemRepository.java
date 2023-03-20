package com.java.SpringBootProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.SpringBootProject.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
	public List<OrderItem> findOrderByidOrder(int idOrder);
}
