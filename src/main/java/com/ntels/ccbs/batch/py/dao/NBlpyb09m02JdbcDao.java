package com.ntels.ccbs.batch.py.dao;

import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

public interface NBlpyb09m02JdbcDao {
	
	int updateBillMastRcptAmt(List<CBillComm> billList);
	
	int updateFullPayBill(List<CBillComm> billList);
	
	List<CBillComm> getBillListByBillSeqNo(CBillComm bill);
	
	int updateBillRcptAmt(List<CBillComm> billList);
	
}
