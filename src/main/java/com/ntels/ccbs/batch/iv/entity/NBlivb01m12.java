package com.ntels.ccbs.batch.iv.entity;

import java.util.ArrayList;
import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrepayBillAply;
import com.ntels.ccbs.batch.iv.common.entity.PrepayCtrt;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

public class NBlivb01m12 {

	private PrepayBillAply prepayBillAply;
	
	private List<ReceiptDetail> receiptDetailList;
	
	private List<Receipt> receiptList;
	
	private List<CBillComm> updateBillList;
	
	private List<PrepayBillAply> updatePrepayList;
	
	private List<PrepayCtrt> updatePrepayCtrtList;

	public PrepayBillAply getPrepayBillAply() {
		return prepayBillAply;
	}

	public void setPrepayBillAply(PrepayBillAply prepayBillAply) {
		this.prepayBillAply = prepayBillAply;
	}

	public List<ReceiptDetail> getReceiptDetailList() {
		return receiptDetailList;
	}

	public void addReceiptDetail(ReceiptDetail receiptDetail) {
		if (receiptDetailList == null) {
			receiptDetailList = new ArrayList<>();
		}
		receiptDetailList.add(receiptDetail);
	}

	public List<Receipt> getReceiptList() {
		return receiptList;
	}

	public void addReceipt(Receipt receipt) {
		if (receiptList == null) {
			receiptList = new ArrayList<>();
		}
		receiptList.add(receipt);
	}
	
	public List<CBillComm> getUpdateBillList() {
		return updateBillList;
	}
	
	public void addUpdateBill(CBillComm bill) {
		if (updateBillList == null) {
			updateBillList = new ArrayList<>();
		}
		
		updateBillList.add(bill);
	}

	public List<PrepayBillAply> getUpdatePrepayList() {
		return updatePrepayList;
	}

	public void addUpdatePrepay(PrepayBillAply updatePrepay) {
		if (updatePrepayList == null) {
			updatePrepayList = new ArrayList<>();
		}
		updatePrepayList.add(updatePrepay);
	}
	
	public List<PrepayCtrt> getUpdatePrepayCtrtList() {
		return updatePrepayCtrtList;
	}
	
	public void addUpdatePrepayCtrt(PrepayCtrt prepayCtrt) {
		if (updatePrepayCtrtList == null) {
			updatePrepayCtrtList = new ArrayList<>();
		}
		
		updatePrepayCtrtList.add(prepayCtrt);
	}
}
