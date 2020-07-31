package com.ntels.ccbs.batch.py.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.py.common.dao.mapper.PyCommMapper;
import com.ntels.ccbs.batch.py.common.entity.ExrateInfo;
import com.ntels.ccbs.batch.py.entity.AcAgncyPymInfo;

@Repository
public class PyCommDaoImpl implements PyCommDao {

	@Autowired
	private PyCommMapper pyCommMapper;
	
	@Override
	public ExrateInfo getExrateInfo(String exrateAplyDt, String crncyCd) {
		return pyCommMapper.getExrateInfo(exrateAplyDt, crncyCd);
	}
	
	@Override
	public int insertAgncyPymInfo(AcAgncyPymInfo acAgncyPymInfo) {
		return pyCommMapper.insertAgncyPymInfo(acAgncyPymInfo);
	}
	
	/**
	 * 여신사용금액/여신가능금액을 갱신한다.
	 * @param amt
	 * @param orgId
	 * @return
	 */
	@Override
	public int updateLoanInfo(long amt, String orgId) {
		return pyCommMapper.updateLoanInfo(amt, orgId);
	}
	
	/**
	 * 납부계정ID 조회
	 * @param pymAcntId
	 * @return
	 */
	@Override
	public int getPymAcntCnt(String pymAcntId) {
		return pyCommMapper.getPymAcntCnt(pymAcntId);
	}
	
}
