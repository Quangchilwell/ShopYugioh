package com.java.SpringBootProject.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.BillItemDao;
import com.java.SpringBootProject.Entity.BillItem;
import com.java.SpringBootProject.Repository.BillItemRepository;
import com.java.SpringBootProject.Repository.BillRepository;

@Repository
@Transactional
public class BillItemImpl implements BillItemDao{

	@Autowired
	BillItemRepository billItemRepository;
	
	@Override
	public void addBillItem(BillItem billItem) {
		billItemRepository.save(billItem);
	}

	@Override
	public void updateBillItem(BillItem billItem) {
		billItemRepository.save(billItem);
	}

	@Override
	public void deleteBillItem(int id) {
		BillItem billItem = getBillItemByID(id);
		billItemRepository.delete(billItem);
		
	}

	@Override
	public BillItem getBillItemByID(int id) {
		// TODO Auto-generated method stub
		return billItemRepository.getById(id);
	}

	@Override
	public List<BillItem> getBillItemByIDBill(int idBill) {
		return billItemRepository.findByidBill(idBill);
	}

	@Override
	public List<BillItem> getAllBillItem() {
		
		return billItemRepository.findAll();
	}

}
