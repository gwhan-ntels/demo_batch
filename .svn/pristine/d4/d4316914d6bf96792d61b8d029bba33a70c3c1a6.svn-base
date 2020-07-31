package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.entity.RateInfo;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.TaxBill;
import com.ntels.ccbs.batch.iv.common.entity.TaxTarget;
import com.ntels.ccbs.batch.iv.dao.mapper.NBlivb01m09Mapper;

@Repository
public class NBlivb01m09DaoImpl extends LazyLoadingDao implements NBlivb01m09Dao {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private NBlivb01m09Mapper nBlivb01m09Mapper;
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Value("${dbms.kind}")
	String dbKind;
	
	@Override
	protected String getMapperName() {
		return "NBlivb01m09Mapper";
	}
	
	@Override
	public int insertBillWrk(List<CBillComm> billList) {
		return insert(dbKind,"insertBillWrk", CBillComm.class, billList);
	}
	
	@Override
	public int insertTaxBill(List<TaxBill> taxBillList) {
		return insert(dbKind,"insertTaxBill", TaxBill.class, taxBillList);
	}
	
	@Override
	public LazyLoader<TaxTarget> getTaxTargetList(CBillComm bill) {
		return getLazyLoader(dbKind,"getTaxTargetList", TaxTarget.class, bill);
	}
	
	@Override
	public LazyLoader<TaxTarget> getTaxTargetListByBillSeqNo(CBillComm bill) {
		return getLazyLoader(dbKind,"getTaxTargetListByBillSeqNo", TaxTarget.class, bill);
	}
	
	@Override
	public List<TaxTarget> getBillForTaxCalcFromSimulationBillByBillSeqNo(Common bill) {
		return getList(dbKind,"getBillForTaxCalcFromSimulationBillByBillSeqNo", TaxTarget.class, bill);
	}
	
	@Override
	public List<TaxBill> getNewTaxBill(CBillComm bill) {
		return getList(dbKind,"getNewTaxBill", TaxBill.class, bill);
	}
	
	@Override
	public String getRoundingAdjSvcRateItmTypCd(String soId) {
		TaxTarget taxTarget = new TaxTarget();
		taxTarget.setSoId(soId);
		return getOne(dbKind, "getRoundingAdjSvcRateItmTypCd", TaxTarget.class, taxTarget);
		//return nBlivb01m09Mapper.getRoundingAdjSvcRateItmTypCd(soId);
	}
	
}
