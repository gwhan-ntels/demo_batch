package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.dao.mapper.NBlivb01m10Mapper;

@Repository
public class NBlivb01m10DaoImpl extends LazyLoadingDao implements NBlivb01m10Dao {

	@Autowired
	private NBlivb01m10Mapper nBlivb01m10Mapper;
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}
	
	
	@Value("${dbms.kind}")
	String dbKind;	
	
	
	@Override
	protected String getMapperName() {
		return "NBlivb01m10Mapper";
	}
	
	@Override
	public LazyLoader<BillCust> getBillCustInfo(BillCust billCust) {
		return getLazyLoader(dbKind,"getBillCustInfo", BillCust.class, billCust);
	}
	
	@Override
	public List<BillCust> getBillCustInfoMapper(BillCust billCust) {
		return nBlivb01m10Mapper.getBillCustInfoMapper(billCust);
	}
	
	@Override
	public List<BillCust> getBillCustInfoByBillSeqNo(BillCust billCust) {
		return getList(dbKind,"getBillCustInfoByBillSeqNo", BillCust.class, billCust);
	}
	
	@Override
	public LazyLoader<BillCust> getBillTgtSvcCmpsTargetInfo(BillCust billCust) {
		return getLazyLoader(dbKind,"getBillTgtSvcCmpsTargetInfo", BillCust.class, billCust);
	}

	@Override
	public int updateBillTgtCust(List<BillCust> billCustList) {
		return update(dbKind,"updateBillTgtCust", BillCust.class, billCustList);
	}
	
}
