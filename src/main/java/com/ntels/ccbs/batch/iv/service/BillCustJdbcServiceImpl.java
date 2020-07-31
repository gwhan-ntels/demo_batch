package com.ntels.ccbs.batch.iv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.iv.common.entity.BillCust;
import com.ntels.ccbs.batch.iv.dao.BillCustJdbcDao;

@Service
public class BillCustJdbcServiceImpl extends BaseService implements BillCustJdbcService {

	@Autowired
	private BillCustJdbcDao billCustJdbcDao;
	
	@Override
	public int insertBillCust(List<BillCust> billCustList) {
		return billCustJdbcDao.insertBillCust(billCustList);
	}
	
	@Override
	public int insertBillCustDetail(List<BillCust> billCustDetailList) {
		return billCustJdbcDao.insertBillCustDetail(billCustDetailList);
	}
	
}
