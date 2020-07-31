package com.ntels.ccbs.batch.iv.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.entity.BillStp;

@Component
public interface CBLMapper {

	/**
	 * 
	 * @param billCyclStp
	 * @return
	 */
	List<BillCyclStp> getCyclStpSetVal(BillCyclStp billCyclStp);
	
	/**
	 * 
	 * @param billCyclStp
	 * @return
	 */
	List<String> getSetLoc(BillCyclStp billCyclStp);
	
	/**
	 * 
	 * @param billCyclStp
	 * @return
	 */
	List<BillStp> getStpSetVal(BillCyclStp billCyclStp);
	
	/**
	 * 환율 적용일자 조회
	 * @param currentDate
	 * @return
	 */
	String getExrateAplyDt(String currentDate);
	
}
