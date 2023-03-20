package com.java.SpringBootProject.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.BillDao;
import com.java.SpringBootProject.Dao.BillItemDao;
import com.java.SpringBootProject.Entity.Bill;
import com.java.SpringBootProject.Entity.BillItem;
import com.java.SpringBootProject.Model.BillDTO;
import com.java.SpringBootProject.Model.BillItemDTO;
import com.java.SpringBootProject.Model.ProductDTO;
import com.java.SpringBootProject.Service.BillItemService;
import com.java.SpringBootProject.Service.BillService;
import com.java.SpringBootProject.Service.ProductService;
import com.java.SpringBootProject.Service.UserService;

@Service
@Transactional
public class BillItemServiceImpl implements BillItemService{

	@Autowired
	BillItemDao billItemDao;
	
	@Autowired
	BillDao billDao;
	
	@Autowired
	BillService billService;
	
	@Autowired
	ProductService productService;
	
	@Override
	public void addBillItem(BillItemDTO billItemDTO) {
		BillItem billItem = new BillItem();
		
		billItem.setId(billItemDTO.getId());
		billItem.setIdBill(billItemDTO.getBillDTO().getId());
		billItem.setIdPro(billItemDTO.getProductDTO().getId());
		billItem.setQuantity(billItemDTO.getQuantity());
		
		billItemDTO.setUnitPrice(billItemDTO.getProductDTO().getPrice() 
				* billItemDTO.getQuantity());
		billItem.setUnitPrice(billItemDTO.getUnitPrice());
		
		billItemDao.addBillItem(billItem);
		
	}

	@Override
	public void updateBillItem(BillItemDTO billItemDTO) {
		BillItem billItem = billItemDao.getBillItemByID(billItemDTO.getId());
		
		billItem.setId(billItemDTO.getId());
		billItem.setIdBill(billItemDTO.getBillDTO().getId());
		billItem.setIdPro(billItemDTO.getProductDTO().getId());
		billItem.setQuantity(billItemDTO.getQuantity());
		
		billItemDTO.setUnitPrice(billItemDTO.getProductDTO().getPrice() 
				* billItemDTO.getQuantity());
		billItem.setUnitPrice(billItemDTO.getUnitPrice());
		
		billItemDao.updateBillItem(billItem);
		
	}

	@Override
	public void deleteBillItem(int id) {
		BillItem billItem = billItemDao.getBillItemByID(id);
		billItemDao.deleteBillItem(id);
	}

	@Override
	public BillItemDTO getBillItemByID(int id) {
		BillItem billItem = billItemDao.getBillItemByID(id);
		
		if(billItem != null) {
			BillItemDTO billItemDTO = new BillItemDTO();
			BillDTO billDTO = billService.getBillByID(billItem.getIdBill());
			ProductDTO productDTO = productService.getProductByID(billItem.getIdPro());
			
			billItemDTO.setId(billItem.getId());
			billItemDTO.setBillDTO(billDTO);
			billItemDTO.setProductDTO(productDTO);
			billItemDTO.setQuantity(billItem.getQuantity());
			billItemDTO.setUnitPrice(billItem.getUnitPrice());
			
			return billItemDTO;
		}
		
		return null;
	}

	@Override
	public List<BillItemDTO> getBillItemByIDBill(int idBill) {
		Bill bill = billDao.getBillByID(idBill);
		List<BillItemDTO> billItemDTOs = new ArrayList<BillItemDTO>();
		
		
		if(bill != null)
		{
			List<BillItem> billItems = billItemDao.getBillItemByIDBill(bill.getId());
			float totalPrice = 0;
			int totalProduct = 0;
			
//			Lay danh sach cac vat pham
			for(BillItem billitem : billItems)
			{
				totalPrice += billitem.getUnitPrice();
				totalProduct += billitem.getQuantity();
				
				BillItemDTO billItemDTO = new BillItemDTO();
				BillDTO billDTO = billService.getBillByID(billitem.getIdBill());
				ProductDTO productDTO = productService.getProductByID(billitem.getIdPro());
				
				billItemDTO.setId(billitem.getId());
				billItemDTO.setBillDTO(billDTO);
				billItemDTO.setProductDTO(productDTO);
				billItemDTO.setQuantity(billitem.getQuantity());
				billItemDTO.setUnitPrice(billItemDTO.getProductDTO().getPrice() 
						* billItemDTO.getQuantity());
				
				billItemDTOs.add(billItemDTO);
			}
			
//			Cap nhat lai don hang
			bill.setTotalPrice(totalPrice);
			bill.setTotalProduct(totalProduct);
			billDao.updateBill(bill);
		}
		
		return billItemDTOs;
	}

	@Override
	public List<BillItemDTO> getAllBillItem() {
		List<BillItem> billitems = billItemDao.getAllBillItem();
		List<BillItemDTO> billItemDTOs = new ArrayList<BillItemDTO>();
		
		for(BillItem billitem : billitems)
		{
			BillItemDTO billItemDTO = new BillItemDTO();
			BillDTO billDTO = billService.getBillByID(billitem.getIdBill());
			ProductDTO productDTO = productService.getProductByID(billitem.getIdPro());
			
			billItemDTO.setId(billitem.getId());
			billItemDTO.setBillDTO(billDTO);
			billItemDTO.setProductDTO(productDTO);
			billItemDTO.setQuantity(billitem.getQuantity());
			billItemDTO.setUnitPrice(billItemDTO.getProductDTO().getPrice() 
					* billItemDTO.getQuantity());
			
			billItemDTOs.add(billItemDTO);
		}
		return billItemDTOs;
	}
	
}
