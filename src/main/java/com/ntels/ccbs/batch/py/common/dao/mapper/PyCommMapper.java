package com.ntels.ccbs.batch.py.common.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.py.common.entity.ExrateInfo;
import com.ntels.ccbs.batch.py.entity.AcAgncyPymInfo;

@Component
public interface PyCommMapper {

	/**
	 * 환율 정보 조회
	 * @param exrateAplyDt
	 * @param crncyCd
	 * @return
	 */
	ExrateInfo getExrateInfo(@Param("exrateAplyDt") String exrateAplyDt
							, @Param("crncyCd") String crncyCd);
	
	int insertAgncyPymInfo(@Param("acAgncyPymInfo") AcAgncyPymInfo acAgncyPymInfo);
	
	/**
	 * 여신사용금액/여신가능금액을 갱신한다.
	 * @param amt
	 * @param orgId
	 * @return
	 */
	int updateLoanInfo(@Param("amt") long amt, @Param("orgId") String orgId);
	
	/**
	 * 납부계정ID 조회
	 * @param pymAcntId
	 * @return
	 */
	int getPymAcntCnt(@Param("pymAcntId") String pymAcntId);
	
}
