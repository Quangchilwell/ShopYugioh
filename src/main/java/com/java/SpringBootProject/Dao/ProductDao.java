package com.java.SpringBootProject.Dao;

import java.util.List;

import com.java.SpringBootProject.Entity.Product;

public interface ProductDao {
	public void addProduct(Product product);
	
	public void updateProduct(Product product);
	
	public void deleteProduct(int id);
	
	public Product getProductByID(int id);
	
	public List<Product> getAllProducts();
}
