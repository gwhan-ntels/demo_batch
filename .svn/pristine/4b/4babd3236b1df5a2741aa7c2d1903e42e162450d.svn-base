package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.PrintCustInfo;
import com.ntels.ccbs.batch.iv.common.entity.Sale;
import com.ntels.ccbs.batch.iv.dao.mapper.NBlivb01m27Mapper;

@Repository
public class NBlivb01m27DaoImpl extends LazyLoadingDao implements NBlivb01m27Dao {

	@Autowired
	private NBlivb01m27Mapper nBlivb01m27Mapper;
	
	@Override
	protected String getMapperPath() {
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	@Value("${dbms.kind}")
	String dbKind;
	
	@Override
	protected String getMapperName() {
		return "NBlivb01m27Mapper";
	}
	
	@Override
	public LazyLoader<Sale> querySale(CBillComm bill) {
		return getLazyLoader(dbKind, "querySale", Sale.class, bill);
	}
	
	@Override
	public int insertSaleTemp(Sale sale) {
		return nBlivb01m27Mapper.insertSaleTemp(sale);
	}
	
	@Override
	public int insertSaleTemp(final List<Sale> saleList) {
		return batchJob(NBlivb01m27Mapper.class, new BatchJob<NBlivb01m27Mapper>() {

			@Override
			public int job(NBlivb01m27Mapper mapper) {
				int cnt = 0;
				
				for (Sale sale : saleList) {
					mapper.insertSaleTemp(sale);
					cnt++;
				}
				
				return cnt;
			}
		});
	}
	
	@Override
	public int insertSale(Sale sale) {
		return nBlivb01m27Mapper.insertSale(sale);
	}
	
	@Override
	public int insertSale(final List<Sale> saleList) {
		return batchJob(NBlivb01m27Mapper.class, new BatchJob<NBlivb01m27Mapper>() {

			@Override
			public int job(NBlivb01m27Mapper mapper) {
				int cnt = 0;
				
				for (Sale sale : saleList) {
					mapper.insertSale(sale);
					cnt++;
				}
				
				return cnt;
			}
		});
	}
	
	@Override
	public int deletePrevData(String billYymm, String soId) {
		//return nBlivb01m27Mapper.deletePrevData(billYymm, soId);
		CBillComm cBillComm = new CBillComm();
		cBillComm.setSoId(soId);
		cBillComm.setBillYymm(billYymm);
		return deleteOne(dbKind, "deletePrevData", cBillComm);
	}
	
}
