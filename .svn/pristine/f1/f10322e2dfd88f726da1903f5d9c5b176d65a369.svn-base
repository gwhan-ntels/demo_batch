package com.ntels.ccbs.batch.py.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.dao.JdbcDao;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

@Repository
public class NBlpyb09m02JdbcDaoImpl extends JdbcDao implements NBlpyb09m02JdbcDao {

	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/py/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "NBlpyb09m02Mapper";
	}
	
	@Override
	public int updateBillMastRcptAmt(List<CBillComm> billList) {
		return update("updateBillMastRcptAmt", new CBillComm(), billList);
	}
	
	@Override
	public int updateFullPayBill(List<CBillComm> billList) {
		return update("updateFullPayBill", new CBillComm(), billList);
	}
	
	@Override
	public List<CBillComm> getBillListByBillSeqNo(CBillComm bill) {
		return getList("getBillListByBillSeqNo", new CBillComm(), bill);
	}
	
	@Override
	public int updateBillRcptAmt(List<CBillComm> billList) {
		return update("updateBillRcptAmt", new CBillComm(), billList);
	}

}
