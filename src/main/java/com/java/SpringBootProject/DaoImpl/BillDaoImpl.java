package com.java.SpringBootProject.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.BillDao;
import com.java.SpringBootProject.Entity.Bill;
import com.java.SpringBootProject.Repository.BillRepository;

@Repository
@Transactional
public class BillDaoImpl implements BillDao{

	@Autowired
	BillRepository billRepository;
	
	@Override
	public void addBill(Bill bill) {
		billRepository.save(bill);
		
	}

	@Override
	public void updateBill(Bill bill) {
		billRepository.save(bill);
		
	}

	@Override
	public void deleteBill(int id) {
		Bill bill = getBillByID(id);
		billRepository.delete(bill);
		
	}

	@Override
	public Bill getBillByID(int id) {
		return billRepository.getById(id);
	}

	@Override
	public List<Bill> getAllBill() {
		
		return billRepository.findAll();
	}

	@Override
	public Bill getBillByIdUser(int idUser) {
		
		return billRepository.findBillByidUser(idUser);
	}

}
