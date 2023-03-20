package com.java.SpringBootProject.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.CategoryDao;
import com.java.SpringBootProject.Entity.Category;
import com.java.SpringBootProject.Model.CategoryDTO;
import com.java.SpringBootProject.Service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Override
	public void addCategory(CategoryDTO categoryDTO) {
		Category category = new Category();

		category.setId(categoryDTO.getIdCate());
		category.setName(categoryDTO.getName());

		categoryDao.addCategory(category);

	}

	@Override
	public void updateCategory(CategoryDTO categoryDTO) {
		Category category = categoryDao.getCategoryByID(categoryDTO.getIdCate());

		category.setId(categoryDTO.getIdCate());
		category.setName(categoryDTO.getName());

		categoryDao.updateCategory(category);

	}

	@Override
	public void deleteCategory(int id) {
		categoryDao.deleteCategory(id);
	}

	@Override
	public CategoryDTO getCategoryByID(int id) {
		Category category = categoryDao.getCategoryByID(id);

		if (category != null) {
			CategoryDTO categoryDTO = new CategoryDTO();

			categoryDTO.setIdCate(category.getId());
			categoryDTO.setName(category.getName());

			return categoryDTO;
		}

		return null;
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> categories = categoryDao.getAllCategory();
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();

		for (Category category : categories) {
			CategoryDTO categoryDTO = new CategoryDTO();

			categoryDTO.setIdCate(category.getId());
			categoryDTO.setName(category.getName());
			
			categoryDTOs.add(categoryDTO);
		}

		return categoryDTOs;
	}

}
