package com.ntels.ccbs.batch.ch.standard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.ch.standard.entity.StandardCharge;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.ch.standard.dao.StandardChargeDao;
import com.ntels.ccbs.batch.sample.vo.SearchParam;


/**
 * 공통 코드 Service.
 *
 * <PRE>
 * 1. ClassName: StandardChargeService
 * 2. FileName : StandardChargeService.java
 * 3. Package  : com.ntels.ccbs.cm.service.configuration
 * 4. 작성자   : smyun@ntels.com
 * 5. 작성일   : 2014. 4. 8. 오후 5:02:49
 * 6. 변경이력
 *		이름  :		일자	: 변경내용
 *     ———————————————————————————————————
 *		smyun :	2014. 4. 8.	: 신규 개발.
 * </PRE>
 */
@Service
public class StandardChargeServiceImpl implements StandardChargeService {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** StandardChargeDao Autowired.  */
	@Autowired
	private StandardChargeDao standardChargeDao;
	
	
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
//	public List<StandardCharge> list(SearchParam param) {
//
//		List<StandardCharge> list = StandardChargeDao.listStandardCharge(param);
//		
//		if(list == null)
//			list = new ArrayList<StandardCharge>();
//		
//		return list;
//	}
	
	/**
	 * 출력.
	 * 
	 * @param StandardCharge 공통코드
	 * @return boolean
	 */
	public boolean printStandardCharge(StandardCharge StandardCharge){
		
		l.debug("common code := {}", StandardCharge);
		
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
//	public List<StandardCharge> listJdbcTemplate(SearchParam param) {
//
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		
//		return StandardChargeDao.listStandardChargeJdbcTemplate(jdbcTemplate, param);
//	}
	


	public List<StandardCharge> listJdbcDirect(Common comm) {

		Connection conn = null;
		List<StandardCharge> list = null;
		try{
			conn = dataSource.getConnection();
			list = standardChargeDao.listStandardChargeDirect(conn, comm);
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
		
		return list;
	}

	public int saveJdbcDirect(List<Object>  obj) {
		Connection conn = null;
		int result = 0;

		try{
			conn = dataSource.getConnection();
			result = standardChargeDao.saveStandardChargeDirect(conn, obj);
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
	public int saveJdbcDirect(StandardCharge  standardCharge) {
		Connection conn = null;
		int result = 0;

		try{
			conn = dataSource.getConnection();
			result = standardChargeDao.saveStandardChargeDirect(conn, standardCharge);
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
	
}