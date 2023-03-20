package com.java.SpringBootProject.Dao;

import java.util.List;

import com.java.SpringBootProject.Entity.Order;

public interface OrderDao {
	public List<Order> getAllOrder();
	
	public void addOrder(Order order);
	
	public void updateOrder(Order order);
	
	public void deleteOrder(Order order);
	
	public Order getOrderByID(int id);
	
	public Order getOrderByIndexExtra(int index);
	
	public List<Order> getOrderByIdUser(int idUser);
}
