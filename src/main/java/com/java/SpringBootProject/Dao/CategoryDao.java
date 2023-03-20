package com.java.SpringBootProject.Dao;

import java.util.List;

import com.java.SpringBootProject.Entity.Category;

public interface CategoryDao {
	public void addCategory(Category category);
	
	public void updateCategory(Category category);
	
	public void deleteCategory(int id);
	
	public Category getCategoryByID(int id);
	
	public List<Category> getAllCategory();
}
