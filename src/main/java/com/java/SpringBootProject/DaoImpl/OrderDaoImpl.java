package com.java.SpringBootProject.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.OrderDao;
import com.java.SpringBootProject.Entity.Order;
import com.java.SpringBootProject.Repository.OrderRepository;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao{

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}

	@Override
	public void addOrder(Order order) {
		orderRepository.save(order);
		
	}

	@Override
	public void updateOrder(Order order) {
		orderRepository.save(order);
		
	}

	@Override
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
		
	}

	@Override
	public Order getOrderByID(int id) {
		return orderRepository.getById(id);
	}

	@Override
	public List<Order> getOrderByIdUser(int idUser) {
		
		return orderRepository.findOrderByidUser(idUser);
	}

	@Override
	public Order getOrderByIndexExtra(int index) {
		
		return orderRepository.findOrderByindexExtra(index);
	}

}
