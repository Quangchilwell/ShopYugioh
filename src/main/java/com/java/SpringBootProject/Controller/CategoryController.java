package com.java.SpringBootProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.SpringBootProject.Model.CategoryDTO;
import com.java.SpringBootProject.Model.ProductDTO;
import com.java.SpringBootProject.Service.CategoryService;
import com.java.SpringBootProject.Service.ProductService;

@Controller
@RequestMapping("/admin")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/categoryList")
	public String categoryList(Model model)
	{
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		model.addAttribute("categoryDTOs" ,categoryDTOs);
		return "category/categoryList";
	}
	
	@GetMapping("/inforCategory/{id}")
	public String inforCategory(Model model, @PathVariable(name = "id") int id)
	{
		CategoryDTO categoryDTO = categoryService.getCategoryByID(id);
		model.addAttribute("categoryDTO" ,categoryDTO);
		return "category/inforCategory";
	}
	
	@GetMapping("/addCategory")
	public String addCategory(Model model)
	{
		model.addAttribute("category", new CategoryDTO());
		return "category/addCategory";
	}
	
	@PostMapping("/addCategory")
	public String addCategory(@ModelAttribute(name = "category") CategoryDTO categoryDTO)
	{
		categoryService.addCategory(categoryDTO);
		return "redirect:/admin/categoryList";
	}
	
	@GetMapping("/updateCategory/{id}")
	public String updateCategory(Model model, @PathVariable(name = "id") int id)
	{
		CategoryDTO categoryDTO = categoryService.getCategoryByID(id);
		model.addAttribute("category", categoryDTO);
		return "category/updateCategory";
	}
	
	@PostMapping("/updateCategory")
	public String updateCategory(@ModelAttribute(name = "category") CategoryDTO categoryDTO)
	{
		categoryService.updateCategory(categoryDTO);
		return "redirect:/admin/categoryList";
	}
	
	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(Model model, @PathVariable(name = "id") int id)
	{
		CategoryDTO categoryDTO = categoryService.getCategoryByID(id);
		model.addAttribute("category", categoryDTO);
		return "category/deleteCategory";
	}
	
	@GetMapping("/deleteCategorySuccess")
	public String deleteCategorySuccess(@RequestParam(name = "id") int id)
	{
		categoryService.deleteCategory(id);
		return "redirect:/admin/categoryList";
	}
	
	
	//Mo rong chuc nang cho danh muc
	
	@GetMapping("/getProductByCategory/{id}")
	public String getProductByCategory(Model model, @PathVariable(name = "id") int id)
	{
		List<ProductDTO> productDTOs = productService.getProductsByCategory(id);
		model.addAttribute("productDTOs", productDTOs);
		model.addAttribute("category", categoryService.getCategoryByID(id));
		return "category/getProductByCategory";
	}
}
