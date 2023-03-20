package com.java.SpringBootProject.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.ProductDao;
import com.java.SpringBootProject.Entity.Product;
import com.java.SpringBootProject.Repository.ProductRepository;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public void addProduct(Product product) {
		productRepository.save(product);
		
	}

	@Override
	public void updateProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(int id) {
		Product product = getProductByID(id);
		productRepository.delete(product);
	}

	@Override
	public Product getProductByID(int id) {
		
		return productRepository.getById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

}
