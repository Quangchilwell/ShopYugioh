package com.java.SpringBootProject.Dao;

import java.util.List;

import com.java.SpringBootProject.Entity.OrderItem;

public interface OrderItemDao {
	public List<OrderItem> getAllOrderItem();
	
	public void addOrderItem(OrderItem orderItem);
	
	public void updateOrderItem(OrderItem orderItem);
	
	public void deleteOrderItem(OrderItem orderItem);
	
	public OrderItem getOrderIteByID(int id);
	
	public List<OrderItem> getOderItemByIdOrder(int idOrder);
}
