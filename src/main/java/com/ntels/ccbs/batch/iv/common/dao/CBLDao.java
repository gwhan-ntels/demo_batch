package com.ntels.ccbs.batch.iv.common.dao;

import java.util.List;

import com.mysql.jdbc.Connection;
import com.ntels.ccbs.batch.iv.common.entity.BillCyclStp;
import com.ntels.ccbs.batch.iv.common.entity.BillStp;

public interface CBLDao {

	/**
	 * 청구일자를 조회한다.
	 * Mybatis Mapper사용
	 * @return
	 */
	List<BillCyclStp> getCyclStpSetVal(BillCyclStp billCyclStp);
	
	/**
	 * 청구일자를 조히한다.
	 * jdbc api사용
	 * @param conn
	 * @return
	 */
	List<BillCyclStp> getCyclStpSetVal(Connection conn, BillCyclStp billCyclStp);
	
	/**
	 * 
	 * @param billCyclStp
	 * @return
	 */
	List<String> getSetLoc(BillCyclStp billCyclStp);
	
	/**
	 * 
	 * @param conn
	 * @param billCyclStp
	 * @return
	 */
	List<String> getSetLoc(Connection conn, BillCyclStp billCyclStp);
	
	/**
	 * 
	 * @param billCyclStp
	 * @return
	 */
	List<BillStp> getStpSetVal(BillCyclStp billCyclStp);
	
	
	BillStp getStpSetVal(BillCyclStp billCyclStp);
	
	/**
	 * 
	 * @param conn
	 * @param billCyclStp
	 * @return
	 */
	List<BillStp> getStpSetVal(Connection conn, BillCyclStp billCyclStp);
	
	/**
	 * 환율 적용일자 조회
	 * @param currentDate
	 * @return
	 */
	String getExrateAplyDt(String currentDate);
	
}
