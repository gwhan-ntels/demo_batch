package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.iv.common.entity.Sale;

public interface NBlivb01m27Service {

	LazyLoader<Sale> querySale(String billYymm, String soId);
	
	int insertSaleTemp(Sale sale);
	
	int insertSaleTemp(List<Sale> saleList);
	
	int insertSale(Sale sale);
	
	int insertSale(List<Sale> saleList);
	
	int deletePrevData(String billYymm, String soId);

}
