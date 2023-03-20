package com.java.SpringBootProject.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.CategoryDao;
import com.java.SpringBootProject.Entity.Category;
import com.java.SpringBootProject.Repository.CategoryRepository;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void addCategory(Category category) {
		
		categoryRepository.save(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(int id) {
		Category category = getCategoryByID(id);
		categoryRepository.delete(category);
	}

	@Override
	public Category getCategoryByID(int id) {
		return categoryRepository.getById(id);
	}

	@Override
	public List<Category> getAllCategory() {
		
		return categoryRepository.findAll();
	}

}
