package com.ntels.ccbs.batch.up.unpay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.service.BaseService;
import com.ntels.ccbs.batch.up.entity.UpymObj;
import com.ntels.ccbs.batch.up.unpay.dao.UnpayDao;

@Service
public class UnpayServiceImpl extends BaseService implements UnpayService {

	@Autowired
	private UnpayDao unpayDao;
	
	/**
	 * 미수금 내역을 저장한다.
	 * @param upymObj
	 * @return
	 */
	@Override
	public int insertUnpaymentObj(List<UpymObj> upymObjList) {
		return unpayDao.insertUnpaymentObj(upymObjList);
	}
	
}
