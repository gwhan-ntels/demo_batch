package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import com.ntels.ccbs.batch.iv.common.entity.BillCust;

public interface BillCustJdbcDao {

	int insertBillCust(List<BillCust> billCustList);
	
	int insertBillCustDetail(List<BillCust> billCustDetailList);
	
}
