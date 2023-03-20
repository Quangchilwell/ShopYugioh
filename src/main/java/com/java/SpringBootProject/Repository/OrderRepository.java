package com.java.SpringBootProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.SpringBootProject.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	public List<Order> findOrderByidUser(int idUser);
	
	public Order findOrderByindexExtra(int index);
}
