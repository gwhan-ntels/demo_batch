package com.ntels.ccbs.batch.iv.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.service.CBillWrkService;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m04Dao;


/**
 * 공통 코드 Service.
 *
 * <PRE>
 * 1. ClassName: NBliv04m01Service
 * 2. FileName : NBliv04m01Service.java
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
public class NBlivb01m04ServiceImpl implements NBlivb01m04Service {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** NBliv04m01Dao Autowired.  */
	@Autowired
	private NBlivb01m04Dao clsDao;
	
	@Autowired
	CBillWrkService  clsWrkSvc;
	
	@Autowired
	private ClogService  clslog ;	
	
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
	 * @return List<NBliv04m01>
	 */

	
	public LazyLoader<CBillComm> listJdbcDirect(Common comm) 
	{
		return clsDao.listInfoDirect(comm);
	}
	
	public int saveJdbcDirect(List<Object>  obj) 
	{
		int result = 0;
		
		result = clsDao.saveInfoDirect(obj);
	    if ( result <= 0 )
	    {
	    	clslog.writeLog("NBlivb01m04-saveJdbcDirect Error : " + obj.get(0).toString() );
	    	return result;
	    }
	    return clsWrkSvc.deleteJdbcDirect(obj);
	}
	
	
	public LazyLoader<CBillComm> listDivJdbcDirect(Common comm) 
	{
		return clsDao.listDivInfoDirect(comm);
	}
	
	public int saveWrkDirect(List<Object>  obj) {
	    return  clsWrkSvc.saveJdbcDirect(obj);

	}
	
	public int saveDivJdbcDirect(List<Object>  obj) 
	{
	
		int result = 0;
		
		result = clsDao.saveDivInfoDirect(obj);
	    if ( result <= 0 )
	    {
	    	clslog.writeLog("NBlivb01m04-saveDivInfoDirect Error : " + obj.get(0).toString() );
	    	return result;
	    }
	    return clsWrkSvc.saveJdbcDirect(obj);
	}
	
	
}