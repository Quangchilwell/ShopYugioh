package com.java.SpringBootProject.Service;

import java.util.List;

import com.java.SpringBootProject.Model.BillDTO;

public interface BillService {
	public void addBill(BillDTO billDTO);
	
	public void updateBill(BillDTO billDTO);
	
	public void deleteBill(int id);
	
	public BillDTO getBillByID(int id);
	
	public BillDTO getBillByIdUser(int idUser);
	
	public List<BillDTO> getAllBill();
}
