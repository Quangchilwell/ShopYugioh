package com.java.SpringBootProject.Service;

import java.util.List;

import com.java.SpringBootProject.Model.BillItemDTO;

public interface BillItemService {
	public void addBillItem(BillItemDTO billItemDTO);
	
	public void updateBillItem(BillItemDTO billItemDTO);
	
	public void deleteBillItem(int id);
	
	public BillItemDTO getBillItemByID(int id);
	
	public List<BillItemDTO> getBillItemByIDBill(int id);
	
	public List<BillItemDTO> getAllBillItem();
}
