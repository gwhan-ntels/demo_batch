package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrintCt;
import com.ntels.ccbs.batch.iv.common.entity.PrintCustInfo;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m17Dao;

@Service
public class NBlivb01m17ServiceImpl extends BaseService implements NBlivb01m17Service {
	
	@Autowired
	private NBlivb01m17Dao nBlivb01m17Dao;
	
	@Override
	public LazyLoader<PrintCustInfo> getPrintCustInfo(CBillComm bill) {
		return nBlivb01m17Dao.getPrintCustInfo(bill);
	}
	
	@Override
	public LazyLoader<PrintCt> getPrintCt(CBillComm bill) {
		return nBlivb01m17Dao.getPrintCt(bill);
	}
	
	@Override
	public int insertPrintCustInfo(List<PrintCustInfo> printCustInfoList) {
		return nBlivb01m17Dao.insertPrintCustInfo(printCustInfoList);
	}
	
	@Override
	public int insertPrintCt(List<PrintCt> printCtList) {
		return nBlivb01m17Dao.insertPrintCt(printCtList);
	}
	
}
