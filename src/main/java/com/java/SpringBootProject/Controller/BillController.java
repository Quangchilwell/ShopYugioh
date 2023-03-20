package com.java.SpringBootProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.SpringBootProject.Model.BillDTO;
import com.java.SpringBootProject.Service.BillService;

@Controller
@RequestMapping("/admin")
public class BillController {
	
	@Autowired
	BillService billService;
	
	@GetMapping("/billList")
	public String billList(Model model)
	{
		List<BillDTO> billDTOs = billService.getAllBill();
		model.addAttribute("billDTOs", billDTOs);
		return "bill/billList";
	}
	
}
