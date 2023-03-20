package com.java.SpringBootProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.SpringBootProject.Entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{
	public Bill findBillByidUser(int idUser);
}
