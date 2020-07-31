package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m10Dao;

@Service
public class NBlivb01m10ServiceImpl extends LazyLoadingDao implements NBlivb01m10Service {

	@Autowired
	private NBlivb01m10Dao nBlivb01m10Dao;
	
	@Value("${dbms.kind}")
	String dbKind;
	
	@Override
	protected String getMapperPath() 
	{
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}
	
	@Override
	protected String getMapperName()
	{
		return "NBlivb01m10Mapper";
	}
	
	@Override
	public LazyLoader<BillCust>  getBillCustInfo(BillCust billCust) {
	//	return getOne(dbKind, "getBillCustInfo", BillCust.class, billCust);
		return nBlivb01m10Dao.getBillCustInfo(billCust);
	}
	
	@Override
	public List<BillCust> getBillCustInfoMapper(BillCust billCust) {
	//	return getList(dbKind, "getBillCustInfoMapper", BillCust.class, billCust);
	    return nBlivb01m10Dao.getBillCustInfoMapper(billCust);
	}
	
	@Override
	public List<BillCust> getBillCustInfoByBillSeqNo(BillCust billCust) {
		//return getList(dbKind, "getBillCustInfoByBillSeqNo", BillCust.class, billCust);
		return nBlivb01m10Dao.getBillCustInfoByBillSeqNo(billCust);
	}
	
	@Override
	public LazyLoader<BillCust> getBillTgtSvcCmpsTargetInfo(BillCust billCust) {
		//return getOne(dbKind, "getBillTgtSvcCmpsTargetInfo", BillCust.class, billCust);
		return nBlivb01m10Dao.getBillTgtSvcCmpsTargetInfo(billCust);
	}

	@Override
	public int updateBillTgtCust(List<BillCust> billCustList) {
		return nBlivb01m10Dao.updateBillTgtCust(billCustList);
	}
	
}
