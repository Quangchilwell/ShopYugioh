package com.java.SpringBootProject.Dao;

import java.util.List;

import com.java.SpringBootProject.Entity.Bill;

public interface BillDao {
	public void addBill(Bill bill);
	
	public void updateBill(Bill bill);
	
	public void deleteBill(int id);
	
	public Bill getBillByID(int id);
	
	public Bill getBillByIdUser(int idUser);
	
	public List<Bill> getAllBill();
}
