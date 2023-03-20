package com.java.SpringBootProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.SpringBootProject.Entity.BillItem;

public interface BillItemRepository extends JpaRepository<BillItem, Integer>{
	public List<BillItem> findByidBill(int idBill);
}
