package com.java.SpringBootProject.Service;

import java.util.List;

import com.java.SpringBootProject.Model.ProductDTO;

public interface ProductService {
	public void addProduct(ProductDTO productDTO);
	
	public void updateProduct(ProductDTO productDTO);
	
	public void deleteProduct(int id);
	
	public ProductDTO getProductByID(int id);
	
	public List<ProductDTO> SearchProducts(String infor);
	
	public List<ProductDTO> getAllProducts();
	
	public List<ProductDTO> getProductsByCategory(int idCate);
	
//	public List<ProductDTO> getProductsByName(String name);
}
