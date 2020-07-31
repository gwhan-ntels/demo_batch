package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrepayBillAply;
import com.ntels.ccbs.batch.iv.common.entity.PrepayCtrt;
import com.ntels.ccbs.batch.iv.dao.mapper.NBlivb01m12Mapper;

@Repository
public class NBlivb01m12DaoImpl extends LazyLoadingDao implements NBlivb01m12Dao {

	@Autowired
	private NBlivb01m12Mapper nBlivb01m12Mapper;
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Override
	protected String getMapperName() {
		return "NBlivb01m12Mapper";
	}
	
	@Value("${dbms.kind}")
	String dbKind;
	
	@Override
	public LazyLoader<PrepayBillAply> getPrepayCtrtContents(CBillComm bill) {
		return getLazyLoader(dbKind, "getPrepayCtrtContents", PrepayBillAply.class, bill);
	}
	
	@Override
	public PrepayBillAply getNoPayProceedPrepayBillAply(PrepayBillAply prepayBillAply) {
		return getOne(dbKind, "getNoPayProceedPrepayBillAply", PrepayBillAply.class, prepayBillAply);
	}
	
	@Override
	public Double getRemainRcptAmtFromBillWrk(CBillComm bill) {
		return nBlivb01m12Mapper.getRemainRcptAmtFromBillWrk(bill);
	}
	
	@Override
	public List<CBillComm> getBillWrkInfoForPrepayByCtrt(CBillComm bill) {
		return getList(dbKind, "getBillWrkInfoForPrepayByCtrt", CBillComm.class, bill);
	}
	
	@Override
	public List<CBillComm> getBillWrkInfoForPrepayByCust(CBillComm bill) {
		return getList(dbKind, "getBillWrkInfoForPrepayByCust", CBillComm.class, bill);
	}
	
	@Override
	public int updateBillWrkRcptAmt(List<CBillComm> billList) {
		return update(dbKind, "updateBillWrkRcptAmt", CBillComm.class, billList);
	}
	
	@Override
	public int insertPrepayBillAply(List<PrepayBillAply> prepayBillAply) {
		return insert(dbKind, "insertPrepayBillAply", PrepayBillAply.class, prepayBillAply);
	}
	
	@Override
	public int updatePrepayBillAplyPayProc(List<PrepayBillAply> prepayBillAply) {
		return update(dbKind, "updatePrepayBillAplyPayProc", PrepayBillAply.class, prepayBillAply);
	}
	
	@Override
	public int updatePrepayCtrt(List<PrepayCtrt> prepayCtrtList) {
		return update("", PrepayCtrt.class, prepayCtrtList);
	}

}
