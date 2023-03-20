package com.java.SpringBootProject.Service;

import java.util.List;

import com.java.SpringBootProject.Model.OrderDTO;

public interface OrderService {
	public List<OrderDTO> getAllOrder();
	
	public void addOrder(OrderDTO orderDTO);
	
	public void updateOrder(OrderDTO orderDTO);
	
	public void deleteOrder(int id);
	
	public OrderDTO getOrderByID(int id);
	
	public OrderDTO getOrderByIndexExtra(int index);
	
	public List<OrderDTO> getOrderByIdUser(int idUser);
}
