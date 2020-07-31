/**
 * @FileName
 * CBillServiceImpl.java
 * @Project
 * ccbs_batch
 * @Date
 * 2016. 5. 9.
 * @Writter
 * ntels_shlee
 * @EditHistory
 *
 * @Discript
 */
package com.ntels.ccbs.batch.iv.common.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.dao.CBillDao;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;

@Service
public class CBillServiceImpl implements CBillService {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** StandardChargeDao Autowired.  */
	@Autowired
	private CBillDao clsDao;
	
	
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
	
	
	 public String getBillSeqNo(String bill_yymm, String bill_cycl, String bill_dt, String pym_acnt_id,  String seq)
	 {
			
		    String bill_seq_no = null;
		     
		        if ( bill_dt.length() < 8 )
		         	bill_seq_no = bill_yymm.substring(2, 6) + bill_cycl + bill_dt + pym_acnt_id + seq;
		        else
		        	bill_seq_no = bill_yymm.substring(2, 6) + bill_cycl + bill_dt.substring(6, 8) + pym_acnt_id + seq;
		 	    return bill_seq_no;
	}
	 
	/**
	 * 출력.
	 * 
	 * @param StandardCharge 공통코드
	 * @return boolean
	 */
	public boolean printCBill(CBillComm cbill){
		
		l.debug("common code := {}", cbill);
		
		return true;
	}
	
	
	public int saveJdbcDirect(List<Object>  obj) {
		Connection conn = null;
		int result = 0;

		try{
			conn = dataSource.getConnection();
			result = clsDao.saveInfoDirect(conn, obj);
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
	
	public int updateJdbcDirect(List<Object>  obj) {
		Connection conn = null;
		int result = 0;

		try{
			conn = dataSource.getConnection();
			result = clsDao.updateInfoDirect(conn, obj);
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
	
	public int deleteJdbcDirect(List<Object>  obj) {
		Connection conn = null;
		int result = 0;

		try{
			conn = dataSource.getConnection();
			result = clsDao.deleteInfoDirect(conn, obj);
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