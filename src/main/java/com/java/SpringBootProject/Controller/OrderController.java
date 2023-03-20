package com.java.SpringBootProject.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.SpringBootProject.Model.BillDTO;
import com.java.SpringBootProject.Model.BillItemDTO;
import com.java.SpringBootProject.Model.OrderDTO;
import com.java.SpringBootProject.Model.OrderItemDTO;
import com.java.SpringBootProject.Model.UserDTO;
import com.java.SpringBootProject.Service.BillItemService;
import com.java.SpringBootProject.Service.BillService;
import com.java.SpringBootProject.Service.OrderItemService;
import com.java.SpringBootProject.Service.OrderService;
import com.java.SpringBootProject.Service.ProductService;
import com.java.SpringBootProject.Service.UserService;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	BillService billService;

	@Autowired
	BillItemService billItemService;

	@Autowired
	OrderItemService orderItemService;

	public UserDTO getInforCart(Model model, int idBill) {
		BillDTO billDTO = billService.getBillByID(idBill);
		List<BillItemDTO> billItemDTOs = billItemService.getBillItemByIDBill(billDTO.getId());
		UserDTO userDTO = userService.getUserByID(billDTO.getUser().getId());

		model.addAttribute("billItemDTOs", billItemDTOs);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("billDTO", billDTO);

		return userDTO;
	}

	@GetMapping(value = "/admin/orderList")
	public String orderList(Model model) {
		List<OrderDTO> orderDTOs = orderService.getAllOrder();
		model.addAttribute("orderDTOs", orderDTOs);
		return "order/orderList";
	}

	@GetMapping(value = "/admin/inforOrder/{id}")
	public String inforOrder(Model model, @PathVariable(name = "id") int id) {
		OrderDTO orderDTO = orderService.getOrderByID(id);
		if (orderDTO != null) {
			List<OrderItemDTO> orderItemDTOs = orderItemService.getOrderItemByIdOrder(orderDTO.getId());
			model.addAttribute("orderItemDTOs", orderItemDTOs);
		}

		model.addAttribute("order", orderDTO);
		return "order/inforOrder";
	}

	@GetMapping(value = "/payment")
	public String payment(Model model, @RequestParam(name = "idBill") int idBill) {
		getInforCart(model, idBill);

		// Tao ra don dat hang
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setIdUser(getInforCart(model, idBill).getId());
		model.addAttribute("order", orderDTO);
		return "web/payment";
	}

	@GetMapping("/orderSuccess")
	public String orderSuccess(Model model) {
		return "/order/orderSuccess";
	}

	@PostMapping(value = "/payment")
	public String payment(Model model, HttpSession session, @ModelAttribute(name = "order") OrderDTO orderDTO) {
		BillDTO billDTO = (BillDTO) session.getAttribute("billDTO");
		UserDTO userDTO = userService.getUserByID(orderDTO.getIdUser());
		List<OrderDTO> orderDTOs = orderService.getAllOrder();
		
		// Thiet lap chi so phu cho don dat hang
		int indexExtra = 1;
		for(OrderDTO orderDTO2: orderDTOs)
		{
			if(indexExtra == orderDTO2.getIndexExtra()){
				indexExtra++;
			}
		}

		// Ra don dat hang
		orderDTO.setUserDTO(userDTO);
		orderDTO.setTotalProduct(billDTO.getTotalProduct());
		orderDTO.setTotalPrice(billDTO.getTotalPrice());
		orderDTO.setIndexExtra(indexExtra);
		orderService.addOrder(orderDTO);

		// Lay thong tin cac vp gio hang cai vao trong orderItem
		List<BillItemDTO> billItemDTOs = billItemService.getBillItemByIDBill(billDTO.getId());
		OrderDTO orderDTO2 = orderService.getOrderByIndexExtra(indexExtra);

		for (BillItemDTO billItemDTO : billItemDTOs) {
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			orderItemDTO.setOrderDTO(orderDTO2);
			orderItemDTO.setProductDTO(billItemDTO.getProductDTO());
			orderItemDTO.setQuantity(billItemDTO.getQuantity());
			orderItemDTO.setUnitPrice(billItemDTO.getUnitPrice());
			orderItemService.addOrderItem(orderItemDTO);
		}

		return "redirect:/orderSuccess";
	}

	@GetMapping("/inforOrderCustomer")
	public String inforOrderCustomer(Model model, @RequestParam(name = "idUser") int idUser) {
		List<OrderDTO> orderDTOs = orderService.getOrderByIdUser(idUser);
		model.addAttribute("orderDTOs", orderDTOs);
		return "order/inforOrderCustomer";
	}

	@GetMapping("/deleteOrderFromCustomer")
	public String deleteOrderFromCustomer(@RequestParam(name = "id") int id) {
		OrderDTO orderDTO = orderService.getOrderByID(id);
		UserDTO userDTO = userService.getUserByID(orderDTO.getUserDTO().getId());
		
		// Xoa cac san pham trong don hang
		List<OrderItemDTO> orderItemDTOs = orderItemService.getOrderItemByIdOrder(id);
		for(OrderItemDTO orderItemDTO: orderItemDTOs)
		{
			orderItemService.deleteOrderItem(orderItemDTO.getId());
		}
		
		//Xoa han don hang
		orderService.deleteOrder(id);
		return "redirect:/inforOrderCustomer?idUser=" + String.valueOf(userDTO.getId());
	}

	@GetMapping(value = "/admin/acceptOrder")
	public String acceptOrder(@RequestParam(name = "id") int id) {
		OrderDTO orderDTO = orderService.getOrderByID(id);
		orderDTO.setStatus("Đang_vận_chuyển");
		orderService.updateOrder(orderDTO);
		return "redirect:/admin/orderList";
	}
	
	@GetMapping("/admin/completeOrder")
	public String completeOrder(@RequestParam(name = "id") int id) {
		List<OrderItemDTO> orderItemDTOs = orderItemService.getOrderItemByIdOrder(id);
		for(OrderItemDTO orderItemDTO: orderItemDTOs)
		{
			orderItemService.deleteOrderItem(orderItemDTO.getId());
		}
		orderService.deleteOrder(id);
		return "redirect:/admin/orderList";
	}

}
