package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.BillTgtCust;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.OverpayBillAply;
import com.ntels.ccbs.batch.iv.dao.mapper.NBlivb01m15Mapper;
import com.ntels.ccbs.batch.py.entity.PrepayOcc;

@Repository
public class NBlivb01m15DaoImpl extends LazyLoadingDao implements NBlivb01m15Dao {

	@Autowired
	private NBlivb01m15Mapper nBlivb01m15Mapper;
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}
	
	@Value("${dbms.kind}")
	String dbKind;
	
	@Override
	protected String getMapperName() {
		return "NBlivb01m15Mapper";
	}

	@Override
	public LazyLoader<OverpayBillAply> getOverpayBillAplyList(CBillComm bill) {
		return getLazyLoader(dbKind,"getOverpayBillAplyList", OverpayBillAply.class, bill);
	}
	
	@Override
	public List<CBillComm> getBillList(CBillComm bill) {
		return getList(dbKind,"getBillList", CBillComm.class, bill);
	}
	
	@Override
	public int updateBill(List<CBillComm> billList) {
		return update(dbKind,"updateBill", CBillComm.class, billList);
	}
	
	@Override
	public int updateBillMast(List<CBillComm> billList) {
		return update(dbKind,"updateBillMast", CBillComm.class, billList);
	}
	
	@Override
	public List<String> getSmlAmtYn(CBillComm bill) {
		return nBlivb01m15Mapper.getSmlAmtYn(bill);
	}
	
	@Override
	public int updateSmlAmtYn(List<BillTgtCust> billTgtCustList) {
		return update(dbKind,"updateSmlAmtYn", BillTgtCust.class, billTgtCustList);
	}
	
	@Override
	public int updateOverpayBillAply(List<OverpayBillAply> overpayBillAplyList) {
		return update(dbKind,"updateOverpayBillAply", OverpayBillAply.class, overpayBillAplyList);
	}

	@Override
	public int updatePrepayOcc(List<PrepayOcc> prepayOccList) {
		return update(dbKind,"updatePrepayOcc", PrepayOcc.class, prepayOccList);
	}
	
}
