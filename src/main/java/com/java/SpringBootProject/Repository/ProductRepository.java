package com.java.SpringBootProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.SpringBootProject.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
