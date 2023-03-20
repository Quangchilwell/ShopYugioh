package com.java.SpringBootProject.Service;

import java.util.List;

import com.java.SpringBootProject.Model.OrderItemDTO;

public interface OrderItemService {
	public List<OrderItemDTO> getAllOrder();
	
	public void addOrderItem(OrderItemDTO orderItemDTO);
	
	public void updateOrderItem(int id);
	
	public void deleteOrderItem(int id);
	
	public OrderItemDTO getOrderItemByID(int id);
	
	public List<OrderItemDTO> getOrderItemByIdOrder(int idOrder);
}
