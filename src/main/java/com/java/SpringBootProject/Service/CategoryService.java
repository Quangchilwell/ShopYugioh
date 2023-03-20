package com.java.SpringBootProject.Service;

import java.util.List;

import com.java.SpringBootProject.Entity.Category;
import com.java.SpringBootProject.Model.CategoryDTO;

public interface CategoryService {
	public void addCategory(CategoryDTO categoryDTO);
	
	public void updateCategory(CategoryDTO categoryDTO);
	
	public void deleteCategory(int id);
	
	public CategoryDTO getCategoryByID(int id);
	
	public List<CategoryDTO> getAllCategory();
}
