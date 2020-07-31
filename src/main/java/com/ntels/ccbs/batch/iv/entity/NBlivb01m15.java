package com.ntels.ccbs.batch.iv.entity;

import java.util.ArrayList;
import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.BillTgtCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;
import com.ntels.ccbs.batch.py.entity.PrepayTransHistory;
import com.ntels.ccbs.batch.py.entity.Receipt;
import com.ntels.ccbs.batch.py.entity.ReceiptDetail;

public class NBlivb01m15 {

	private List<ReceiptDetail> receiptDetailList;
	private List<Receipt> receiptList;
	private List<CBillComm> updateFullPayBillList;
	private List<CBillComm> billList;
	private List<CBillComm> billMastList;
	private List<BillTgtCust> billTgtCustList;
	private List<OverpayBillAply> overpayBillAplyList;
	private List<PrepayOcc> prepayOccList;
	private List<PrepayTransHistory> prepayTransHistoryList;

	/**
	 * @return the receiptDetailList
	 */
	public List<ReceiptDetail> getReceiptDetailList() {
		return receiptDetailList;
	}

	public void addReceiptDetail(ReceiptDetail receiptDetail) {
		if (receiptDetailList == null) {
			receiptDetailList = new ArrayList<>();
		}
		receiptDetailList.add(receiptDetail);
	}

	/**
	 * @return the receiptList
	 */
	public List<Receipt> getReceiptList() {
		return receiptList;
	}

	public void addReceiptList(Receipt receipt) {
		if (receiptList == null) {
			receiptList = new ArrayList<>();
		}
		receiptList.add(receipt);
	}
	
	public List<CBillComm> getBillList() {
		return billList;
	}

	public void addBillList(CBillComm bill) {
		if (billList == null) {
			billList = new ArrayList<>();
		}
		billList.add(bill);
	}

	/**
	 * @return the billList
	 */
	public List<CBillComm> getFullPayBillList() {
		return updateFullPayBillList;
	}

	public void addFullPayBill(CBillComm bill) {
		if (updateFullPayBillList == null) {
			updateFullPayBillList = new ArrayList<>();
		}
		updateFullPayBillList.add(bill);
	}
	
	/**
	 * @return the billMastList
	 */
	public List<CBillComm> getBillMastList() {
		return billMastList;
	}

	public void addBillMast(CBillComm bill) {
		if (billMastList == null) {
			billMastList = new ArrayList<>();
		}
		billMastList.add(bill);
	}

	/**
	 * @return the billTgtCustList
	 */
	public List<BillTgtCust> getBillTgtCustList() {
		return billTgtCustList;
	}

	public void addBillTgtCustList(BillTgtCust billTgtCust) {
		if (billTgtCustList == null) {
			billTgtCustList = new ArrayList<>();
		}
		billTgtCustList.add(billTgtCust);
	}

	/**
	 * @return the overpayBillAplyList
	 */
	public List<OverpayBillAply> getOverpayBillAplyList() {
		return overpayBillAplyList;
	}

	public void addOverpayBillAplyList(OverpayBillAply overpayBillAply) {
		if (overpayBillAplyList == null) {
			overpayBillAplyList = new ArrayList<>();
		}
		overpayBillAplyList.add(overpayBillAply);
	}

	/**
	 * @return the prepayOccList
	 */
	public List<PrepayOcc> getPrepayOccList() {
		return prepayOccList;
	}

	public void addPrepayOccList(PrepayOcc prepayOcc) {
		if (prepayOccList == null) {
			prepayOccList = new ArrayList<>();
		}
		prepayOccList.add(prepayOcc);
	}

	/**
	 * @return the prepayTransHistoryList
	 */
	public List<PrepayTransHistory> getPrepayTransHistoryList() {
		return prepayTransHistoryList;
	}

	public void addPrepayTransHistoryList(PrepayTransHistory prepayTransHistory) {
		if (prepayTransHistoryList == null) {
			prepayTransHistoryList = new ArrayList<>();
		}
		prepayTransHistoryList.add(prepayTransHistory);
	}

}
