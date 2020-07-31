package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.CUtil;
import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.Sale;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m27Dao;

@Service
public class NBlivb01m27ServiceImpl extends BaseService implements NBlivb01m27Service {

	@Autowired
	private NBlivb01m27Dao nBlivb01m27Dao;
	
	@Override
	public LazyLoader<Sale> querySale(String billYymm, String soId) {
		
		String useYymm = "";
		
		try {
			// 청구년월의 전월을 구한다.
			useYymm = CUtil.addMonths(billYymm, -1);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		CBillComm bill = new CBillComm();
		bill.setBillYymm(billYymm);
		bill.setUseYymm(useYymm);
		bill.setSoId(soId);
		
		return nBlivb01m27Dao.querySale(bill);
	}
	
	@Override
	public int insertSaleTemp(Sale sale) {
		return nBlivb01m27Dao.insertSaleTemp(sale);
	}
	
	@Override
	public int insertSaleTemp(List<Sale> saleList) {
		return nBlivb01m27Dao.insertSaleTemp(saleList);
	}
	
	@Override
	public int insertSale(Sale sale) {
		return nBlivb01m27Dao.insertSale(sale);
	}
	
	@Override
	public int insertSale(List<Sale> saleList) {
		return nBlivb01m27Dao.insertSale(saleList);
	}
	
	@Override
	public int deletePrevData(String billYymm, String soId) {
		return nBlivb01m27Dao.deletePrevData(billYymm, soId);
	}
	
}