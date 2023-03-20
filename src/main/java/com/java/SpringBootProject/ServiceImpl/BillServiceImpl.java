package com.java.SpringBootProject.ServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.BillDao;
import com.java.SpringBootProject.Dao.BillItemDao;
import com.java.SpringBootProject.Dao.UserDao;
import com.java.SpringBootProject.Entity.Bill;
import com.java.SpringBootProject.Entity.BillItem;
import com.java.SpringBootProject.Entity.User;
import com.java.SpringBootProject.Model.BillDTO;
import com.java.SpringBootProject.Model.UserDTO;
import com.java.SpringBootProject.Service.BillService;
import com.java.SpringBootProject.Service.UserService;

@Service
@Transactional
public class BillServiceImpl implements BillService {

	@Autowired
	BillDao billDao;
	
	@Autowired
	BillItemDao billItemDao;

	@Autowired
	UserDao userDao;

	@Autowired
	UserService userService;

	@Override
	public void addBill(BillDTO billDTO) {
		Bill bill = new Bill();

		bill.setId(billDTO.getId());
		bill.setIdUser(billDTO.getUser().getId());
		bill.setBillDate(Date.valueOf(billDTO.getBillDate()));
		bill.setTotalProduct(billDTO.getTotalProduct());
		bill.setTotalPrice(bill.getTotalPrice());

		billDao.addBill(bill);
	}

	@Override
	public void updateBill(BillDTO billDTO) {
		Bill bill = billDao.getBillByID(billDTO.getId());
		List<BillItem> billItems = billItemDao.getBillItemByIDBill(bill.getId());
		float totalPrice = 0;
		for(BillItem billItem: billItems)
		{
			totalPrice += billItem.getUnitPrice();
		}

		bill.setId(billDTO.getId());
		bill.setIdUser(billDTO.getUser().getId());
		bill.setBillDate(Date.valueOf(billDTO.getBillDate()));
		bill.setTotalProduct(billDTO.getTotalProduct());
		bill.setTotalPrice(totalPrice);

		billDao.updateBill(bill);

	}

	@Override
	public void deleteBill(int id) {
		Bill bill = billDao.getBillByID(id);
		billDao.deleteBill(id);

	}

	@Override
	public BillDTO getBillByID(int id) {
		Bill bill = billDao.getBillByID(id);
		if (bill != null) {
			BillDTO billDTO = new BillDTO();
			UserDTO userDTO = userService.getUserByID(bill.getIdUser());

			billDTO.setId(bill.getId());
			billDTO.setBillDate(String.valueOf(bill.getBillDate()));
			billDTO.setTotalProduct(bill.getTotalProduct());
			billDTO.setTotalPrice(bill.getTotalPrice());
			billDTO.setUser(userDTO);

			return billDTO;
		}

		return null;
	}

	@Override
	public BillDTO getBillByIdUser(int idUser) {
		User user = userDao.getUserByID(idUser);

		if (user != null) {
			Bill bill = billDao.getBillByIdUser(user.getId());
			if(bill != null)
			{
				BillDTO billDTO = new BillDTO();
				UserDTO userDTO = userService.getUserByID(bill.getIdUser());

				billDTO.setId(bill.getId());
				billDTO.setBillDate(String.valueOf(bill.getBillDate()));
				billDTO.setTotalProduct(bill.getTotalProduct());
				billDTO.setTotalPrice(bill.getTotalPrice());
				billDTO.setUser(userDTO);
				
				return billDTO;
			}
		}
		
		return null;
	}

	@Override
	public List<BillDTO> getAllBill() {
		List<Bill> bills = billDao.getAllBill();
		List<BillDTO> billDTOs = new ArrayList<BillDTO>();

		for (Bill bill : bills) {
			BillDTO billDTO = new BillDTO();
			UserDTO userDTO = userService.getUserByID(bill.getIdUser());

			billDTO.setId(bill.getId());
			billDTO.setBillDate(String.valueOf(bill.getBillDate()));
			billDTO.setTotalProduct(bill.getTotalProduct());
			billDTO.setTotalPrice(bill.getTotalPrice());
			billDTO.setUser(userDTO);

			billDTOs.add(billDTO);
		}
		return billDTOs;
	}

}
