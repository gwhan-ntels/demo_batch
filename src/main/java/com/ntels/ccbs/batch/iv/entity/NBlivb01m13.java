package com.ntels.ccbs.batch.iv.entity;

import java.util.ArrayList;
import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.PrepayTransHistory;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

public class NBlivb01m13 {

	private List<OverpayBillAply> overpayBillAplyList;
	
	private List<PrepayOcc> updatePrepayOccList;
	
	private List<Receipt> receiptList;
	
	private List<ReceiptDetail> receiptDetailList;
	
	private List<CBillComm> updateBillList;
	
	private List<OverpayBillAply> overpayBillList;
	
	private List<PrepayOcc> updatePrepayOccTransList;
	
	private List<PrepayTransHistory> prepayTransHistList;

	public List<OverpayBillAply> getOverpayBillAplyList() {
		return overpayBillAplyList;
	}

	public void addOverpayBillAply(OverpayBillAply overpayBillAply) {
		
		if (overpayBillAplyList == null) {
			overpayBillAplyList = new ArrayList<>();
		}
		
		overpayBillAplyList.add(overpayBillAply);
	}
	
	public List<PrepayOcc> getUpdatePrepayOccList() {
		return updatePrepayOccList;
	}
	
	public void addUpdatePrepayOcc(PrepayOcc prepayOcc) {
		if (updatePrepayOccList == null) {
			updatePrepayOccList = new ArrayList<>();
		}
		
		updatePrepayOccList.add(prepayOcc);
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

	public List<ReceiptDetail> getReceiptDetailList() {
		return receiptDetailList;
	}

	public void addReceiptDetail(ReceiptDetail receiptDetail) {
		if (receiptDetailList == null) {
			receiptDetailList = new ArrayList<>();
		}

		receiptDetailList.add(receiptDetail);
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

	public List<OverpayBillAply> getOverpayBillList() {
		return overpayBillList;
	}

	public void addOverpayBill(OverpayBillAply overpayBill) {
		if (overpayBillList == null) {
			overpayBillList = new ArrayList<>();
		}

		overpayBillList.add(overpayBill);
	}

	public List<PrepayOcc> getUpdatePrepayOccTransList() {
		return updatePrepayOccTransList;
	}

	public void addUpdatePrepayOccTrans(PrepayOcc updatePrepayOccTrans) {
		if (updatePrepayOccTransList == null) {
			updatePrepayOccTransList = new ArrayList<>();
		}

		updatePrepayOccTransList.add(updatePrepayOccTrans);
	}

	public List<PrepayTransHistory> getPrepayTransHistList() {
		return prepayTransHistList;
	}

	public void addPrepayTransHist(PrepayTransHistory prepayTransHist) {
		if (prepayTransHistList == null) {
			prepayTransHistList = new ArrayList<>();
		}

		prepayTransHistList.add(prepayTransHist);
	}
	
}
