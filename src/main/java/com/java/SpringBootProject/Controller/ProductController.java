package com.java.SpringBootProject.Controller;

import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.MultipartFile;

import com.java.SpringBootProject.Dao.ProductDao;
import com.java.SpringBootProject.Entity.Product;
import com.java.SpringBootProject.Model.CategoryDTO;
import com.java.SpringBootProject.Model.ProductDTO;
import com.java.SpringBootProject.Service.CategoryService;
import com.java.SpringBootProject.Service.ProductService;

@Controller
@RequestMapping("/admin")
public class ProductController {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/productList")
	public String productList(Model model)
	{
		List<ProductDTO> productDTOs = productService.getAllProducts();
		model.addAttribute("productDTOs", productDTOs);
		return "product/productList";
	}
	
	@GetMapping("/inforProduct/{id}")
	public String inforProduct(Model model, @PathVariable(name = "id") int id)
	{
		ProductDTO productDTO = productService.getProductByID(id);
 		model.addAttribute("product", productDTO);
		return "product/inforProduct";
	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model)
	{
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
 		model.addAttribute("product", new ProductDTO());
		model.addAttribute("categories", categoryDTOs);
		return "product/addProduct";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute(name = "product") ProductDTO productDTO)
	{	
		MultipartFile file = productDTO.getFile();
		try {
			File newFile = new File("E:/SpringBoot/workspace/SpringBootProject/src/main/resources/static/img/imgProduct/" + file.getOriginalFilename());
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
			
			productDTO.setImageUrl(file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		productDTO.setCategoryDTO(categoryService.getCategoryByID(productDTO.getIdCate()));
		
		productService.addProduct(productDTO);
		
		return "redirect:/admin/productList";
	}
	
	@GetMapping("/updateProduct/{id}")
	public String updateProduct(Model model, @PathVariable(name = "id") int id)
	{
		ProductDTO productDTO = productService.getProductByID(id);
		CategoryDTO category = categoryService.getCategoryByID(productDTO.getCategoryDTO().getIdCate());
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		
 		model.addAttribute("product", productDTO);
 		model.addAttribute("category", category);
		model.addAttribute("categories", categoryDTOs);
		
		return "product/updateProduct";
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute(name = "product") ProductDTO productDTO)
	{	
		MultipartFile file = productDTO.getFile();
		try {
			File newFile = new File("E:/SpringBoot/workspace/SpringBootProject/src/main/resources/static/img/imgProduct/" + file.getOriginalFilename());
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		productDTO.setCategoryDTO(categoryService.getCategoryByID(productDTO.getIdCate()));
		
		if(file.getOriginalFilename().equals("")) {
			Product product = productDao.getProductByID(productDTO.getId());
			productDTO.setImageUrl(product.getImageUrl());
		}
		else {
			productDTO.setImageUrl(file.getOriginalFilename());
		}
		
		productService.updateProduct(productDTO);
		
		return "redirect:/admin/productList";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(Model model, @PathVariable(name = "id") int id)
	{
		ProductDTO productDTO = productService.getProductByID(id);
 		model.addAttribute("product", productDTO);
		return "product/deleteProduct";
	}
	
	@GetMapping("/deleteProductSuccess")
	public String deleteProductSuccess(@RequestParam(name = "id") int id)
	{
		productService.deleteProduct(id);
		return "redirect:/admin/productList";
	}
}
