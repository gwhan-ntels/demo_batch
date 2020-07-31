package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.iv.dao.mapper.NBlivb01m13Mapper;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;

@Repository
public class NBlivb01m13DaoImpl extends LazyLoadingDao implements NBlivb01m13Dao {

	@Autowired
	private NBlivb01m13Mapper nBlivb01m13Mapper;
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Value("${dbms.kind}")
	String dbKind;
	
	@Override
	protected String getMapperName() {
		return "NBlivb01m13Mapper";
	}
	
	@Override
	public LazyLoader<PrepayOcc> getPrepayOccForUnpaidBill(CBillComm bill) {
		return getLazyLoader(dbKind,"getPrepayOccForUnpaidBill", PrepayOcc.class, bill);
	}
	
	@Override
	public List<CBillComm> getUnpaidBillAmt(CBillComm bill) {
		return getList(dbKind,"getUnpaidBillAmt", CBillComm.class, bill);
	}
	
	@Override
	public int insertOverpayBillAply(OverpayBillAply overpayBillAply) {
		return insertOne(dbKind,"insertOverpayBillAply", overpayBillAply);
	}
	
	@Override
	public int insertOverpayBillAply(List<OverpayBillAply> overpayBillAplyList) {
		return insert(dbKind,"insertOverpayBillAply", OverpayBillAply.class, overpayBillAplyList);
	}
	
	@Override
	public List<OverpayBillAply> getOverpayBillInfo(OverpayBillAply overpayBillAply) {
		return getList(dbKind,"getOverpayBillInfo", OverpayBillAply.class, overpayBillAply);
	}
	
	@Override
	public Double getRemainRcptAmtFromBillWrk(CBillComm bill) {
		return nBlivb01m13Mapper.getRemainRcptAmtFromBillWrk(bill);
	}
	
	@Override
	public List<CBillComm> getBillWrkInfoForPrepay(CBillComm bill) {
		return getList(dbKind,"getBillWrkInfoForPrepay", CBillComm.class, bill);
	}
	
	@Override
	public int updateBillWrkRcptAmt(List<CBillComm> billList) {
		return update(dbKind,"updateBillWrkRcptAmt", CBillComm.class, billList);
	}
	
	@Override
	public int updateOverpayBillAply(List<OverpayBillAply> overpayBillAplyList) {
		return update(dbKind,"updateOverpayBillAply", OverpayBillAply.class, overpayBillAplyList);
	}
	
}
