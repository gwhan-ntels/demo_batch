package com.ntels.ccbs.batch.ch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.common.entity.RateAttr;
import com.ntels.ccbs.batch.ch.dao.NBlchb00m01Dao;
import com.ntels.ccbs.batch.ch.entity.NBlchb00m01;
import com.ntels.ccbs.batch.ch.entity.NBlchb11m01;
import com.ntels.ccbs.batch.common.LazyLoader;

/**
 * <PRE>
 * 1. ClassName: NBlchb00m01ServiceImpl
 * 2. FileName : NBlchb00m01ServiceImpl.java
 * 3. Package  : com.ntels.ccbs.batch.ch.service
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 5. 26. 오후 3:21:33
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 5. 26.	: 신규 개발.
 * </PRE>
 */
@Service
public class NBlchb00m01ServiceImpl implements NBlchb00m01Service {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** StandardChargeDao Autowired.  */
	@Autowired
	private NBlchb00m01Dao nBlchb00m01Dao;
	
	
	/** 
	 * DataSource Autowired.
	 * 
	 *  jdbcTemplate 사용을 위해서는 아래 선언 필요
	 * 
	 * */
	@Autowired
	private DataSource dataSource;

	/**
	 * 목록.
	 *
	 * @param condition 조회조건
	 * @return List<StandardCharge>
	 */
	/**
	 * 출력.
	 * 
	 * @param StandardCharge 공통코드
	 * @return boolean
	 */
	public boolean printStandardCharge(NBlchb00m01 nBlchb00m01){
		
		l.debug("common code := {}", nBlchb00m01);
		
		return true;
	}
	
	/**
	 * 목록.
	 * 
	 * transaction을 처리하기 위해서는 service에서 
	 * dataSource 컨트롤 필요.
	 * @param condition 조회조건
	 * @return List<StandardCharge>
	 */

	public LazyLoader<NBlchb00m01> listJdbcDirect(Multi multi) {

		return nBlchb00m01Dao.listNBlchb00m01Direct(multi);
	}
/*
	public List<NBlchb00m01> listJdbcDirect(Multi multi){
		
		List<NBlchb00m01> list = nBlchb00m01Dao.listNBlchb00m01Direct(multi);
		
		if(list == null)
			list = new ArrayList<NBlchb00m01>();
		
		return list;
	}
*/
	public LazyLoader<NBlchb00m01> listJdbcDirectAll(Multi multi) {

		return nBlchb00m01Dao.listNBlchb00m01DirectAll(multi);
	}

	public int saveJdbcDirect(List<Object>  obj) {
		Connection conn = null;
		int result = 0;

		try{
			conn = dataSource.getConnection();
			result = nBlchb00m01Dao.saveNBlchb00m01Direct(conn, obj);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;

	}

	public int saveNBlchb00m01(NBlchb00m01 nblchb00m01) {
		int result = nBlchb00m01Dao.saveNBlchb00m01(nblchb00m01);
		return result;
	}

	public int saveIfCustInfoAll(Multi multi) {
		int result = nBlchb00m01Dao.saveIfCustInfoAll(multi);
		return result;
	}

	public int saveIfCustInfo(Multi multi) {
		int result = nBlchb00m01Dao.saveIfCustInfo(multi);
		return result;
	}

	public Multi getColId(Multi multi){
		
		Multi list = nBlchb00m01Dao.getColId(multi);
		return list;
	}
	public Multi getFunc(Multi multi){
		
		Multi getFunc = nBlchb00m01Dao.getFunc(multi);
		return getFunc;
	}
}