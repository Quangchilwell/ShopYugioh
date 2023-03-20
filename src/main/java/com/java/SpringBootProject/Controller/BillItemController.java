package com.java.SpringBootProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.SpringBootProject.Entity.Bill;
import com.java.SpringBootProject.Model.BillDTO;
import com.java.SpringBootProject.Model.BillItemDTO;
import com.java.SpringBootProject.Model.ProductDTO;
import com.java.SpringBootProject.Service.BillItemService;
import com.java.SpringBootProject.Service.BillService;
import com.java.SpringBootProject.Service.ProductService;

@Controller
public class BillItemController {
	
	@Autowired
	BillService billService;
	
	@Autowired
	BillItemService billItemService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping(value = "/addOne")
	public String addOne(@RequestParam(name = "id") int id)
	{
		BillItemDTO billItemDTO = billItemService.getBillItemByID(id);
		BillDTO billDTO = billService.getBillByID(billItemDTO.getBillDTO().getId());
		ProductDTO productDTO = productService.getProductByID(billItemDTO.getProductDTO().getId());
		
		billItemDTO.setQuantity(billItemDTO.getQuantity() + 1);
		billItemService.updateBillItem(billItemDTO);
		
		// Cap nhat lai hoa don tong
		float totalPrice = billDTO.getTotalPrice();
		billDTO.setTotalPrice(totalPrice + productDTO.getPrice());
		billService.updateBill(billDTO);
		
		return "redirect:/home/seeCart?idBill=" + String.valueOf(billDTO.getId());
	}
	
	
	@GetMapping(value = "/removeOne")
	public String removeOne(@RequestParam(name = "id") int id)
	{
		BillItemDTO billItemDTO = billItemService.getBillItemByID(id);
		BillDTO billDTO = billService.getBillByID(billItemDTO.getBillDTO().getId());
		ProductDTO productDTO = productService.getProductByID(billItemDTO.getProductDTO().getId());
		
		billItemDTO.setQuantity(billItemDTO.getQuantity() - 1);
		billItemService.updateBillItem(billItemDTO);
		
		// Neu san pham bang 0
		if(billItemDTO.getQuantity() == 0)
		{
			billItemService.deleteBillItem(id);
		}
		
		// Cap nhat lai hoa don tong
		float totalPrice = billDTO.getTotalPrice();
		billDTO.setTotalPrice(totalPrice + productDTO.getPrice());
		billService.updateBill(billDTO);
		
		return "redirect:/home/seeCart?idBill=" + String.valueOf(billDTO.getId());
	}
	
	@GetMapping(value = "/deleteItem")
	public String deleteItem(@RequestParam(name = "id") int id)
	{
		BillItemDTO billItemDTO = billItemService.getBillItemByID(id);
		BillDTO billDTO = billService.getBillByID(billItemDTO.getBillDTO().getId());
		ProductDTO productDTO = productService.getProductByID(billItemDTO.getProductDTO().getId());
		billItemService.deleteBillItem(id);
		
		billService.updateBill(billDTO);
		return "redirect:/home/seeCart?idBill=" + String.valueOf(billDTO.getId());
	}
}
