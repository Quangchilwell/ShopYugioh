package com.java.SpringBootProject.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.SpringBootProject.Entity.User;
import com.java.SpringBootProject.Model.BillDTO;
import com.java.SpringBootProject.Model.BillItemDTO;
import com.java.SpringBootProject.Model.CategoryDTO;
import com.java.SpringBootProject.Model.CommentDTO;
import com.java.SpringBootProject.Model.OrderDTO;
import com.java.SpringBootProject.Model.ProductDTO;
import com.java.SpringBootProject.Model.UserDTO;
import com.java.SpringBootProject.Service.BillItemService;
import com.java.SpringBootProject.Service.BillService;
import com.java.SpringBootProject.Service.CategoryService;
import com.java.SpringBootProject.Service.CommentService;
import com.java.SpringBootProject.Service.LoginService;
import com.java.SpringBootProject.Service.OrderService;
import com.java.SpringBootProject.Service.ProductService;
import com.java.SpringBootProject.Service.UserService;

@Controller
@RequestMapping("/home")
public class WebController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	BillItemService billItemService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	OrderService orderService;
	
	public User getInforLogin(HttpSession session, Model model)
	{
		User user = (User) session.getAttribute("user");
		if(user != null) {
			BillDTO billDTO = billService.getBillByIdUser(user.getId());
			List<OrderDTO> orderDTOs = orderService.getOrderByIdUser(user.getId());
			model.addAttribute("billDTO", billDTO);
			session.setAttribute("billDTO", billDTO);
			if(billDTO != null) {
				List<BillItemDTO> billItemDTOs = billItemService.getBillItemByIDBill(billDTO.getId());
				model.addAttribute("billItems", billItemDTOs);	
				session.setAttribute("billItems", billItemDTOs);
			}
			model.addAttribute("user", user);
			session.setAttribute("orderDTOs", orderDTOs);
		}
		return user;
	}
	
	
	@GetMapping("")
	public String home(Model model, HttpSession session)
	{
		List<ProductDTO> productDTOs = productService.getAllProducts();
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		List<OrderDTO> orderDTOs = orderService.getAllOrder();
		
		int orderQuantity = 0;
		for(OrderDTO orderDTO: orderDTOs)
		{
			orderQuantity += 1;
		}
		getInforLogin(session, model);

		model.addAttribute("products", productDTOs);
		model.addAttribute("categories", categoryDTOs);
		model.addAttribute("orderQuantity", orderQuantity);
		return "web/home";
	}
	
	@GetMapping("/category/{id}")
	public String categoryFilter(HttpSession session, Model model, @PathVariable(name = "id") int id)
	{
		List<ProductDTO> productDTOs = productService.getProductsByCategory(id);
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		getInforLogin(session, model);
		
		model.addAttribute("products", productDTOs);
		model.addAttribute("categories", categoryDTOs);
		return "web/categoryFilter";
	}
	
	@GetMapping("/searchProduct")
	public String searchProduct(HttpSession session, Model model, @RequestParam(name = "infor") String inforSearch)
	{
		List<ProductDTO> productDTOs = productService.SearchProducts(inforSearch);
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		getInforLogin(session, model);
		
		model.addAttribute("products", productDTOs);
		model.addAttribute("categories", categoryDTOs);
		return "web/searchProduct";
	}
	
	@GetMapping("/seeProduct")
	public String seeProduct(Model model, HttpSession session, @RequestParam(name = "idPro") int idPro)
	{
		ProductDTO productDTO = productService.getProductByID(idPro);
		getInforLogin(session, model);
		model.addAttribute("product", productDTO);
		session.setAttribute("product", productDTO);
		
		// Hien thi binh luan
		List<CommentDTO> commentDTOs = commentService.getCommentByIdProduct(idPro);
		model.addAttribute("comments", commentDTOs);
		
		// Dem so comment trong san pham
		int count = 0;
		for(CommentDTO commentDTO: commentDTOs)
		{
			count++;
		}
		model.addAttribute("commentNumber", count);
		
		return "web/seeProduct";
	}
	
	@GetMapping("/inforOfCustomer")
	public String inforOfCustomer(Model model, @RequestParam(name = "id") int id)
	{
		UserDTO userDTO = userService.getUserByID(id);
		model.addAttribute("user", userDTO);
		return "web/inforOfCustomer";
	}
}
