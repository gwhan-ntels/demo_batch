package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.entity.Sale;

public interface NBlivb01m27Dao {

	LazyLoader<Sale> querySale(CBillComm bill);
	
	int insertSaleTemp(Sale sale);
	
	int insertSaleTemp(List<Sale> saleList);
	
	int insertSale(Sale sale);
	
	int insertSale(List<Sale> saleList);
	
	int deletePrevData(String billYymm, String soId);
	
}
