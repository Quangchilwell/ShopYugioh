package com.java.SpringBootProject.Dao;

import java.util.List;

import com.java.SpringBootProject.Entity.BillItem;

public interface BillItemDao {
	public void addBillItem(BillItem billItem);
	
	public void updateBillItem(BillItem billItem);
	
	public void deleteBillItem(int id);
	
	public BillItem getBillItemByID(int id);
	
	public List<BillItem> getBillItemByIDBill(int idBill);
	
	public List<BillItem> getAllBillItem();
}
