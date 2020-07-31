package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.BillCust;

public interface BillCustJdbcService {

	int insertBillCust(List<BillCust> billCustList);
	
	int insertBillCustDetail(List<BillCust> billCustDetailList);
	
}
