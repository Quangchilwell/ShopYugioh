package com.java.SpringBootProject.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.OrderItemDao;
import com.java.SpringBootProject.Entity.OrderItem;
import com.java.SpringBootProject.Model.OrderDTO;
import com.java.SpringBootProject.Model.OrderItemDTO;
import com.java.SpringBootProject.Model.ProductDTO;
import com.java.SpringBootProject.Model.UserDTO;
import com.java.SpringBootProject.Service.OrderItemService;
import com.java.SpringBootProject.Service.OrderService;
import com.java.SpringBootProject.Service.ProductService;
import com.java.SpringBootProject.Service.UserService;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	OrderItemDao orderItemDao;
	
	@Autowired
	ProductService productService;
	
	@Autowired 
	OrderService orderService;
	
	@Override
	public List<OrderItemDTO> getAllOrder() {
		List<OrderItem> orderItems = orderItemDao.getAllOrderItem();
		List<OrderItemDTO> orderItemDTOs = new ArrayList<OrderItemDTO>();
		
		for(OrderItem orderItem: orderItems)
		{
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			ProductDTO productDTO = productService.getProductByID(orderItem.getIdPro());
			OrderDTO orderDTO = orderService.getOrderByID(orderItem.getIdOrder());
			
			orderItemDTO.setId(orderItem.getId());
			orderItemDTO.setProductDTO(productDTO);
			orderItemDTO.setOrderDTO(orderDTO);
			orderItemDTO.setQuantity(orderItem.getQuantity());
			orderItemDTO.setUnitPrice(orderItem.getUnitPrice());
			
			orderItemDTOs.add(orderItemDTO);
		}
		
		return orderItemDTOs;
	}

	@Override
	public void addOrderItem(OrderItemDTO orderItemDTO) {
		OrderItem orderItem = new OrderItem();
		
		orderItem.setId(orderItemDTO.getId());
		orderItem.setIdOrder(orderItemDTO.getOrderDTO().getId());
		orderItem.setIdPro(orderItemDTO.getProductDTO().getId());
		orderItem.setQuantity(orderItemDTO.getQuantity());
		orderItem.setUnitPrice(orderItemDTO.getUnitPrice());
		orderItemDao.addOrderItem(orderItem);
		
	}

	@Override
	public void updateOrderItem(int id) {
		OrderItem orderItem = new OrderItem();
		OrderItemDTO orderItemDTO = getOrderItemByID(id);
		
		orderItem.setId(orderItemDTO.getId());
		orderItem.setIdOrder(orderItemDTO.getOrderDTO().getId());
		orderItem.setIdPro(orderItemDTO.getProductDTO().getId());
		orderItem.setQuantity(orderItemDTO.getQuantity());
		orderItem.setUnitPrice(orderItemDTO.getUnitPrice());
		orderItemDao.updateOrderItem(orderItem);
		
	}

	@Override
	public void deleteOrderItem(int id) {
		OrderItem orderItem = orderItemDao.getOrderIteByID(id);
		orderItemDao.deleteOrderItem(orderItem);
		
	}

	@Override
	public OrderItemDTO getOrderItemByID(int id) {
		OrderItem orderItem = orderItemDao.getOrderIteByID(id);
		
		if(orderItem != null)
		{
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			ProductDTO productDTO = productService.getProductByID(orderItem.getIdPro());
			OrderDTO orderDTO = orderService.getOrderByID(orderItem.getIdOrder());
			
			orderItemDTO.setId(orderItem.getId());
			orderItemDTO.setProductDTO(productDTO);
			orderItemDTO.setOrderDTO(orderDTO);
			orderItemDTO.setQuantity(orderItem.getQuantity());
			orderItemDTO.setUnitPrice(orderItem.getUnitPrice());
			
			return orderItemDTO;
		}
		return null;
	}

	@Override
	public List<OrderItemDTO> getOrderItemByIdOrder(int idOrder) {
		List<OrderItem> orderItems = orderItemDao.getOderItemByIdOrder(idOrder);
		List<OrderItemDTO> orderItemDTOs = new ArrayList<OrderItemDTO>();
		
		for(OrderItem orderItem: orderItems)
		{
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			ProductDTO productDTO = productService.getProductByID(orderItem.getIdPro());
			OrderDTO orderDTO = orderService.getOrderByID(orderItem.getIdOrder());
			
			orderItemDTO.setId(orderItem.getId());
			orderItemDTO.setProductDTO(productDTO);
			orderItemDTO.setOrderDTO(orderDTO);
			orderItemDTO.setQuantity(orderItem.getQuantity());
			orderItemDTO.setUnitPrice(orderItem.getUnitPrice());
			
			orderItemDTOs.add(orderItemDTO);
		}
		
		return orderItemDTOs;
	}
	
}
