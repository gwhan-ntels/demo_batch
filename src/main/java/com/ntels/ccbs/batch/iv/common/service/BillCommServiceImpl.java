package com.ntels.ccbs.batch.iv.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.iv.common.dao.BillCommDao;

@Service
public class BillCommServiceImpl extends BaseService implements BillCommService {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BillCommDao billCommDao;
	
	@Override
	public int updateChrgAdjAply(String dcsnProcStat, String adjNo) {
		return billCommDao.updateChrgAdjAply(dcsnProcStat, adjNo);
	}
	
	@Override
	public String getTaxIssNo() {
		int seq = commonService.createNewSequence("BL001");
		return String.format("%08d", seq);
	}
	
	@Override
	public int getSaleAdjIssNo() {
		return commonService.createNewSequence("BL002");
	}

}
