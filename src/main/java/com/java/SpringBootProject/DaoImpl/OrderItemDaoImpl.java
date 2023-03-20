package com.java.SpringBootProject.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.OrderItemDao;
import com.java.SpringBootProject.Entity.OrderItem;
import com.java.SpringBootProject.Repository.OrderItemRepository;

@Repository
@Transactional
public class OrderItemDaoImpl implements OrderItemDao{

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Override
	public List<OrderItem> getAllOrderItem() {
		return orderItemRepository.findAll();
	}

	@Override
	public void addOrderItem(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
		
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
		
	}

	@Override
	public void deleteOrderItem(OrderItem orderItem) {
		orderItemRepository.delete(orderItem);
		
	}

	@Override
	public OrderItem getOrderIteByID(int id) {
		return orderItemRepository.getById(id);
	}

	@Override
	public List<OrderItem> getOderItemByIdOrder(int idOrder) {
		return orderItemRepository.findOrderByidOrder(idOrder);
	}

}
