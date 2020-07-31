package com.ntels.ccbs.batch.ch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.ch.common.entity.Multi;
import com.ntels.ccbs.batch.ch.dao.NBlchb10m01Dao;
import com.ntels.ccbs.batch.ch.entity.NBlchb10m01;
import com.ntels.ccbs.batch.ch.entity.NBlchb11m01;
import com.ntels.ccbs.batch.common.LazyLoader;

/**
 * <PRE>
 * 1. ClassName: NBlchb10m01ServiceImpl
 * 2. FileName : NBlchb10m01ServiceImpl.java
 * 3. Package  : com.ntels.ccbs.batch.ch.service
 * 4. Comment  :
 * 5. 작성자   : lsm
 * 6. 작성일   : 2016. 7. 28. 오전 11:18:46
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		lsm :	2016. 7. 28.	: 신규 개발.
 * </PRE>
 */
@Service
public class NBlchb10m01ServiceImpl implements NBlchb10m01Service {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** StandardChargeDao Autowired.  */
	@Autowired
	private NBlchb10m01Dao nBlchb10m01Dao;
	@Autowired
	private DataSource dataSource;
	
	public LazyLoader<NBlchb10m01> listCharge(Multi multi) {
		return nBlchb10m01Dao.listCharge(multi);
	}

	public int saveCharge(List<Object> obj) {
		Connection conn = null;
		int result = 0;

		try{
			conn = dataSource.getConnection();
			result = nBlchb10m01Dao.saveCharge(conn, obj);
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
	
	public int saveChargeUsage(NBlchb11m01 nblchb11m01) {
		int result = nBlchb10m01Dao.saveChargeUsage(nblchb11m01);
		return result;
	}

}