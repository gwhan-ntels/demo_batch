package com.ntels.ccbs.batch.up.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.service.CommonService;
import com.ntels.ccbs.batch.up.common.dao.UpCommonDao;
import com.ntels.ccbs.batch.up.entity.NBlupb01m02;

@Service
public class UpCommonServiceImpl implements UpCommonService {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UpCommonDao upCommonDao;
	
	@Override
	public int upymNo() {
		return commonService.createNewSequence("UP001");
	}
	
	@Override
	public int updateUpymJobDtl(NBlupb01m02 nBlupb01m02) {
		return upCommonDao.updateUpymJobDtl(nBlupb01m02);
	}
	
}
