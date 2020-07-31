package com.ntels.ccbs.batch.iv.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m03Dao;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.common.service.CBillWrkService;


/**
 * 공통 코드 Service.
 *
 * <PRE>
 * 1. ClassName: NBliv03m01Service
 * 2. FileName : NBliv03m01Service.java
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
public class NBlivb01m03ServiceImpl implements NBlivb01m03Service {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** NBliv03m01Dao Autowired.  */
	@Autowired
	private NBlivb01m03Dao clsDao;
	
	@Autowired
	private CBillWrkService clsWrkService;
	
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
		    	clslog.writeLog("NBlivb01m03-saveJdbcDirect Error : " + obj.get(0).toString() );
		    	return result;
		    }
		    return clsWrkService.deleteJdbcDirect(obj);
	}
	
	
	
	public LazyLoader<CBillComm> listHotBillJdbcDirect(Common comm) 
	{
	
		return clsDao.listHotBillInfoDirect(comm);
	}
	
	
	
	public int saveHotBillJdbcDirect(List<Object>  obj)
	{
		
		int result = 0;
		
		clslog.writeLog("SaveHotBillJdbcDirect: "+ obj.get(0).toString());
		result = clsWrkService.saveJdbcDirect(obj);
		clslog.writeLog("[result : " + result + " SaveHotBillJdbcDirect: " + obj.get(0).toString());
		return result;
	}

}