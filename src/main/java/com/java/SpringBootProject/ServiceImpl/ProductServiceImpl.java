package com.java.SpringBootProject.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.CategoryDao;
import com.java.SpringBootProject.Dao.ProductDao;
import com.java.SpringBootProject.Entity.Category;
import com.java.SpringBootProject.Entity.Product;
import com.java.SpringBootProject.Model.CategoryDTO;
import com.java.SpringBootProject.Model.ProductDTO;
import com.java.SpringBootProject.Service.CategoryService;
import com.java.SpringBootProject.Service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	 @Autowired
	 ProductDao productDao;
	 
	 @Autowired
	 CategoryDao categoryDao;
	 
	 @Autowired
	 CategoryService categoryService;

	@Override
	public void addProduct(ProductDTO productDTO) {
		Product product = new Product();

		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setIdCate(productDTO.getCategoryDTO().getIdCate());
		product.setDescription(productDTO.getDescription());
		product.setQuantity(productDTO.getQuantity());
		product.setImageUrl(productDTO.getImageUrl());
		
		productDao.addProduct(product);
		
	}

	@Override
	public void updateProduct(ProductDTO productDTO) {
		Product product = productDao.getProductByID(productDTO.getId());

		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setIdCate(productDTO.getCategoryDTO().getIdCate());
		product.setDescription(productDTO.getDescription());
		product.setQuantity(productDTO.getQuantity());
		product.setImageUrl(productDTO.getImageUrl());
		
		productDao.updateProduct(product);
		
	}

	@Override
	public void deleteProduct(int id) {
		productDao.deleteProduct(id);
		
	}

	@Override
	public ProductDTO getProductByID(int id) {
		Product product = productDao.getProductByID(id);
		if(product != null) {
			ProductDTO productDTO = new ProductDTO();
			CategoryDTO categoryDTO = categoryService.getCategoryByID(product.getIdCate());
			
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setPrice(product.getPrice());
			productDTO.setQuantity(product.getQuantity());
			productDTO.setDescription(product.getDescription());
			productDTO.setCategoryDTO(categoryDTO);
			productDTO.setIdCate(productDTO.getCategoryDTO().getIdCate());
			productDTO.setImageUrl(product.getImageUrl());
			
			return productDTO;
		}
		return null;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> products = productDao.getAllProducts();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for(Product product: products)
		{
			ProductDTO productDTO = new ProductDTO();
			CategoryDTO categoryDTO = categoryService.getCategoryByID(product.getIdCate());
			
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setPrice(product.getPrice());
			productDTO.setQuantity(product.getQuantity());
			productDTO.setDescription(product.getDescription());
			productDTO.setCategoryDTO(categoryDTO);
			productDTO.setImageUrl(product.getImageUrl());
			
			productDTOs.add(productDTO);
			
		}
		return productDTOs;
	}

	@Override
	public List<ProductDTO> getProductsByCategory(int idCate) {
		Category category = categoryDao.getCategoryByID(idCate);
		List<Product> products = productDao.getAllProducts();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		
		if (category != null) {
			for(Product product: products)
			{
				if(product.getIdCate() == category.getId())
				{
					ProductDTO productDTO = new ProductDTO();
					CategoryDTO categoryDTO = categoryService.getCategoryByID(product.getIdCate());
					
					productDTO.setId(product.getId());
					productDTO.setName(product.getName());
					productDTO.setPrice(product.getPrice());
					productDTO.setQuantity(product.getQuantity());
					productDTO.setDescription(product.getDescription());
					productDTO.setCategoryDTO(categoryDTO);
					productDTO.setImageUrl(product.getImageUrl());
					
					productDTOs.add(productDTO);
				}
			}
		}
		
		return productDTOs;
	}

	@Override
	public List<ProductDTO> SearchProducts(String infor) {
		List<Product> products = productDao.getAllProducts();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		
		for(Product product: products)
		{
			if(product.getName().contains(infor) ||  product.getName().compareToIgnoreCase(infor) == 0) {
				ProductDTO productDTO = new ProductDTO();
				CategoryDTO categoryDTO = categoryService.getCategoryByID(product.getIdCate());
				
				productDTO.setId(product.getId());
				productDTO.setName(product.getName());
				productDTO.setPrice(product.getPrice());
				productDTO.setQuantity(product.getQuantity());
				productDTO.setDescription(product.getDescription());
				productDTO.setCategoryDTO(categoryDTO);
				productDTO.setImageUrl(product.getImageUrl());
				
				productDTOs.add(productDTO);
			}
		}
		
		return productDTOs;
	}

}
