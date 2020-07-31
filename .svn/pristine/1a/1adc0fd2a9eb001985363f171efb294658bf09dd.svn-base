package com.ntels.ccbs.batch.py.common.dao;

import com.ntels.ccbs.batch.py.common.entity.ExrateInfo;
import com.ntels.ccbs.batch.py.entity.AcAgncyPymInfo;

public interface PyCommDao {

	/**
	 * 환율 정보 조회
	 * @param exrateAplyDt
	 * @param crncyCd
	 * @return
	 */
	ExrateInfo getExrateInfo(String exrateAplyDt, String crncyCd);
	
	int insertAgncyPymInfo(AcAgncyPymInfo acAgncyPymInfo);
	
	/**
	 * 여신사용금액/여신가능금액을 갱신한다.
	 * @param amt
	 * @param orgId
	 * @return
	 */
	int updateLoanInfo(long amt, String orgId);
	
	/**
	 * 납부계정ID 조회
	 * @param pymAcntId
	 * @return
	 */
	int getPymAcntCnt(String pymAcntId);
	
}
