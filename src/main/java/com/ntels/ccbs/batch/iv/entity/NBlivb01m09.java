package com.ntels.ccbs.batch.iv.entity;

import java.util.ArrayList;
import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.TaxBill;

public class NBlivb01m09 {

	private List<CBillComm> billList;
	
	private List<TaxBill> taxBillList;

	public List<CBillComm> getBillList() {
		return billList;
	}

	public void addBill(CBillComm bill) {
		if (billList == null) {
			billList = new ArrayList<>();
		}
		
		billList.add(bill);
	}

	public List<TaxBill> getTaxBillList() {
		return taxBillList;
	}

	public void addTaxBill(TaxBill taxBill) {
		if (taxBillList == null) {
			taxBillList = new ArrayList<>();
		}
		
		taxBillList.add(taxBill);
	}
	
}
