package com.java.SpringBootProject.Controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.SpringBootProject.Entity.User;
import com.java.SpringBootProject.Model.BillDTO;
import com.java.SpringBootProject.Model.BillItemDTO;
import com.java.SpringBootProject.Model.OrderDTO;
import com.java.SpringBootProject.Model.ProductDTO;
import com.java.SpringBootProject.Model.UserDTO;
import com.java.SpringBootProject.Service.BillItemService;
import com.java.SpringBootProject.Service.BillService;
import com.java.SpringBootProject.Service.ProductService;
import com.java.SpringBootProject.Service.UserService;

@Controller
@RequestMapping("/home")
public class CartController {

	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;

	@Autowired
	BillService billService;

	@Autowired
	BillItemService billItemService;

	@PostMapping("/addToCart")
	public String addToCart(HttpSession session, @RequestParam(name = "idPro") int idPro) {
		ProductDTO productDTO = productService.getProductByID(idPro);
		UserDTO userDTO = new UserDTO();

		User user = (User) session.getAttribute("user");

		if (user != null) {
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhone(user.getPhone());
			userDTO.setRole(user.getRole());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setGender(user.getGender());
		}

		if (userDTO != null) {
			BillDTO billDTO = billService.getBillByIdUser(userDTO.getId());
			boolean check = false;

			if (billDTO != null) {
				List<BillItemDTO> billItemDTOs = billItemService.getBillItemByIDBill(billDTO.getId());

				for (BillItemDTO billItemDTO : billItemDTOs) {
					// Co trong gio hang
					if (billItemDTO.getProductDTO().getId() == idPro) {
						check = true;
						billItemDTO.setQuantity(billItemDTO.getQuantity() + 1);
						billItemDTO.setUnitPrice(billItemDTO.getProductDTO().getPrice() * billItemDTO.getQuantity());
						billItemService.updateBillItem(billItemDTO);
						break;
					}
				}

				// Khong co trong gio hang
				if (check == false) {
					BillItemDTO billItemDTO2 = new BillItemDTO();
					billItemDTO2.setBillDTO(billDTO);
					billItemDTO2.setQuantity(1);
					billItemDTO2.setProductDTO(productDTO);
					billItemDTO2.setUnitPrice(productDTO.getPrice());
					billItemService.addBillItem(billItemDTO2);
				}

				// Cap nhat lai bill
				List<BillItemDTO> billItemDTOsInBill = billItemService.getBillItemByIDBill(billDTO.getId());
				float totalPrice = 0;
				int totalProduct = 0;
				for (BillItemDTO billItemDTO : billItemDTOsInBill) {
					totalPrice += billItemDTO.getUnitPrice();
					totalProduct += billItemDTO.getQuantity();
					billService.updateBill(billDTO);
				}
			}

			// Neu nguoi do chua co bill
			else {
				BillDTO billDTONew = new BillDTO();
				LocalDate dateNow = LocalDate.now();

				// Tao moi bill
				billDTONew.setUser(userDTO);
				billDTONew.setTotalProduct(1);
				billDTONew.setTotalPrice(productDTO.getPrice());
				billDTONew.setBillDate(String.valueOf(dateNow));
				billService.addBill(billDTONew);

				// Tao item trong bill moi do
				BillDTO billDTO2 = billService.getBillByIdUser(userDTO.getId());
				BillItemDTO billItemDTO = new BillItemDTO();
				billItemDTO.setBillDTO(billDTO2);
				billItemDTO.setProductDTO(productDTO);
				billItemDTO.setQuantity(1);
				billItemDTO.setUnitPrice(productDTO.getPrice());
				billItemService.addBillItem(billItemDTO);
				
				System.out.println(billDTONew.getId());
			}

		}
		return "redirect:/home";
	}

	// Dua san pham vao gio voi so luong nguoi dung chon.
	@PostMapping("/addToCartWithQuantity")
	public String addToCartWithQuantity(HttpSession session, @RequestParam(name = "idPro") int idPro,
			@RequestParam(name = "quantity") int quantity) {
		ProductDTO productDTO = productService.getProductByID(idPro);
		UserDTO userDTO = new UserDTO();

		User user = (User) session.getAttribute("user");

		if (user != null) {
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhone(user.getPhone());
			userDTO.setRole(user.getRole());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setGender(user.getGender());
		}

		if (userDTO != null) {
			BillDTO billDTO = billService.getBillByIdUser(userDTO.getId());
			boolean check = false;

			if (billDTO != null) {
				List<BillItemDTO> billItemDTOs = billItemService.getBillItemByIDBill(billDTO.getId());

				for (BillItemDTO billItemDTO : billItemDTOs) {
					// Co trong gio hang
					if (billItemDTO.getProductDTO().getId() == idPro) {
						check = true;
						billItemDTO.setQuantity(billItemDTO.getQuantity() + quantity);
						billItemDTO.setUnitPrice(billItemDTO.getProductDTO().getPrice() * billItemDTO.getQuantity());
						billItemService.updateBillItem(billItemDTO);
						break;
					}
				}

				// Khong co trong gio hang
				if (check == false) {
					BillItemDTO billItemDTO2 = new BillItemDTO();
					billItemDTO2.setBillDTO(billDTO);
					billItemDTO2.setQuantity(quantity);
					billItemDTO2.setProductDTO(productDTO);
					billItemDTO2.setUnitPrice(productDTO.getPrice());
					billItemService.addBillItem(billItemDTO2);
				}

				// Cap nhat lai bill
				List<BillItemDTO> billItemDTOsInBill = billItemService.getBillItemByIDBill(billDTO.getId());
				float totalPrice = 0;
				int totalProduct = 0;
				for (BillItemDTO billItemDTO : billItemDTOsInBill) {
					totalPrice += billItemDTO.getUnitPrice();
					totalProduct += billItemDTO.getQuantity();
					billService.updateBill(billDTO);
				}
			}

			// Neu nguoi do chua co bill
			else {
				BillDTO billDTONew = new BillDTO();
				LocalDate dateNow = LocalDate.now();

				// Tao moi bill
				billDTONew.setUser(userDTO);
				billDTONew.setTotalPrice(quantity);
				billDTONew.setTotalPrice(productDTO.getPrice());
				billDTONew.setBillDate(String.valueOf(dateNow));
				billService.addBill(billDTONew);

				// Tao item trong bill moi do
				BillItemDTO billItemDTO = new BillItemDTO();
				billItemDTO.setBillDTO(billDTONew);
				billItemDTO.setProductDTO(productDTO);
				billItemDTO.setQuantity(quantity);
				billItemDTO.setUnitPrice(productDTO.getPrice() * quantity);
				billItemService.addBillItem(billItemDTO);
			}

		}

		return "redirect:/home/seeProduct?idPro=" + String.valueOf(idPro);
	}
	
	public void getInforCart(Model model, int idBill)
	{
		BillDTO billDTO = billService.getBillByID(idBill);
		List<BillItemDTO> billItemDTOs = billItemService.getBillItemByIDBill(billDTO.getId());
		UserDTO userDTO = userService.getUserByID(billDTO.getUser().getId());
		
		model.addAttribute("billItemDTOs", billItemDTOs);
		model.addAttribute("userDTO" ,userDTO);
		model.addAttribute("billDTO", billDTO);
	}
	
	// Xem gio hang
	@GetMapping(value = "/seeCart")
	public String seeCart(Model model, @RequestParam(name = "idBill") int idBill)
	{
		getInforCart(model, idBill);
		return "web/seeCart";
	}
	
}
