package com.java.SpringBootProject.ServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.OrderDao;
import com.java.SpringBootProject.Dao.OrderItemDao;
import com.java.SpringBootProject.Entity.Order;
import com.java.SpringBootProject.Entity.OrderItem;
import com.java.SpringBootProject.Model.BillDTO;
import com.java.SpringBootProject.Model.OrderDTO;
import com.java.SpringBootProject.Model.OrderItemDTO;
import com.java.SpringBootProject.Model.UserDTO;
import com.java.SpringBootProject.Service.BillService;
import com.java.SpringBootProject.Service.OrderService;
import com.java.SpringBootProject.Service.UserService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderItemDao orderItemDao;

	@Autowired
	UserService userService;

	@Autowired
	BillService billService;

	@Override
	public List<OrderDTO> getAllOrder() {
		List<Order> orders = orderDao.getAllOrder();
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();

		for (Order order : orders) {
			OrderDTO orderDTO = new OrderDTO();
			UserDTO userDTO = userService.getUserByID(order.getIdUser());

			orderDTO.setId(order.getId());
			orderDTO.setUserDTO(userDTO);
			orderDTO.setTotalProduct(order.getTotalProduct());
			orderDTO.setTotalPrice(order.getTotalPrice());
			orderDTO.setAddress(order.getAddress());
			orderDTO.setStatus(order.getStatus());
			orderDTO.setDateOrder(String.valueOf(order.getDateOrder()));
			orderDTO.setIndexExtra(order.getIndexExtra());
			orderDTOs.add(orderDTO);
		}
		return orderDTOs;
	}

	@Override
	public void addOrder(OrderDTO orderDTO) {
		Order order = new Order();
		List<Order> orders = orderDao.getAllOrder();
		
		order.setId(orderDTO.getId());
		order.setIdUser(orderDTO.getUserDTO().getId());
		order.setTotalProduct(orderDTO.getTotalProduct());
		order.setTotalPrice(orderDTO.getTotalPrice());
		order.setAddress(orderDTO.getAddress());
		order.setStatus("Đang_xử_lí");
		order.setDateOrder(Date.valueOf(LocalDate.now()));
		
		order.setIndexExtra(orderDTO.getIndexExtra());
		

		orderDao.addOrder(order);
	}

	@Override
	public void updateOrder(OrderDTO orderDTO) {
		Order order = orderDao.getOrderByID(orderDTO.getId());
		if(orderDTO != null)
		{
			order.setId(orderDTO.getId());
			order.setIdUser(orderDTO.getUserDTO().getId());
			order.setTotalProduct(order.getTotalProduct());
			order.setAddress(orderDTO.getAddress());
			order.setStatus(orderDTO.getStatus());
			order.setDateOrder(Date.valueOf(LocalDate.now()));
			order.setIndexExtra(orderDTO.getIndexExtra());
			orderDao.updateOrder(order);
		}
		
	}

	@Override
	public void deleteOrder(int id) {
		Order order = orderDao.getOrderByID(id);
		orderDao.deleteOrder(order);
	}

	@Override
	public OrderDTO getOrderByID(int id) {
		Order order = orderDao.getOrderByID(id);

		if (order != null) {
			OrderDTO orderDTO = new OrderDTO();
			UserDTO userDTO = userService.getUserByID(order.getIdUser());

			orderDTO.setId(order.getId());
			orderDTO.setUserDTO(userDTO);
			orderDTO.setTotalProduct(order.getTotalProduct());
			orderDTO.setTotalPrice(order.getTotalPrice());
			orderDTO.setAddress(order.getAddress());
			orderDTO.setStatus(order.getStatus());
			orderDTO.setDateOrder(String.valueOf(order.getDateOrder()));
			orderDTO.setIndexExtra(order.getIndexExtra());

			return orderDTO;
		}
		return null;
	}

	@Override
	public List<OrderDTO> getOrderByIdUser(int idUser) {
		List<Order> orders = orderDao.getOrderByIdUser(idUser);
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		
		for (Order order : orders) {
			OrderDTO orderDTO = new OrderDTO();
			UserDTO userDTO = userService.getUserByID(order.getIdUser());
			
			orderDTO.setId(order.getId());
			orderDTO.setUserDTO(userDTO);
			orderDTO.setTotalProduct(order.getTotalProduct());
			orderDTO.setTotalPrice(order.getTotalPrice());
			orderDTO.setAddress(order.getAddress());
			orderDTO.setStatus(order.getStatus());
			orderDTO.setDateOrder(String.valueOf(order.getDateOrder()));
			orderDTO.setIndexExtra(order.getIndexExtra());
			orderDTOs.add(orderDTO);
		}
		return orderDTOs;
	}

	@Override
	public OrderDTO getOrderByIndexExtra(int index) {
		Order order = orderDao.getOrderByIndexExtra(index);

		if (order != null) {
			OrderDTO orderDTO = new OrderDTO();
			UserDTO userDTO = userService.getUserByID(order.getIdUser());

			orderDTO.setId(order.getId());
			orderDTO.setUserDTO(userDTO);
			orderDTO.setTotalProduct(order.getTotalProduct());
			orderDTO.setTotalPrice(order.getTotalPrice());
			orderDTO.setAddress(order.getAddress());
			orderDTO.setStatus(order.getStatus());
			orderDTO.setDateOrder(String.valueOf(order.getDateOrder()));
			orderDTO.setIndexExtra(order.getIndexExtra());

			return orderDTO;
		}
		return null;
	}

}
