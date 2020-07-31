package com.ntels.ccbs.batch.iv.service;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.common.service.ClogService;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.dao.NBlivb01m32Dao;


/**
 * 공통 코드 Service.
 *
 * <PRE>
 * 1. ClassName: NBliv32m01Service
 * 2. FileName : NBliv32m01Service.java
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
public class NBlivb01m32ServiceImpl implements NBlivb01m32Service {

	/** logger. */
	Logger l = LoggerFactory.getLogger(this.getClass());
	
	/** NBliv32m01Dao Autowired.  */
	@Autowired
	private NBlivb01m32Dao clsDao;
	
	
	/** 
	 * DataSource Autowired.
	 * 
	 *  jdbcTemplate 사용을 위해서는 아래 선언 필요
	 * 
	 * */
	@Autowired
	private DataSource dataSource;

	@Autowired
	private ClogService  clslog ;	

	
	public LazyLoader<CBillComm> listJdbcDirect(Common comm) 
	{
		return clsDao.listInfoDirect( comm );
	}	

	public int saveJdbcDirect(List<Object>  obj) 
	{
		return  clsDao.saveInfoDirect(obj);
	}

	public LazyLoader<CBillComm> listMastJdbcDirect(Common comm) 
	{
		return clsDao.listMastInfoDirect(comm);
	}
	
	public int saveMastJdbcDirect(List<Object>  obj) 
	{
		return  clsDao.saveMastInfoDirect(obj);
	}
	
	public LazyLoader<CBillComm> listHotBillJdbcDirect(Common comm )
	{
		return  clsDao.listHotBillInfo(comm);
	}
	
	public LazyLoader<CBillComm> listHotBillMastJdbcDirect(Common comm)
	{
		return  clsDao.listHotBillMastInfo(comm);
	}
	
	public int saveHotBillJdbcDirect(List<Object> obj)
	{
		return clsDao.saveHotBillInfo(obj);
	}
	
	public int saveHotBillJdbcAllDirect(Common comm)
	{
		int result = 0;
		int nCnt = 0;
		
			List<CBillComm> hotBill  = clsDao.listHotBillAllInfo(comm);
			List<Object> obj = new ArrayList<Object>();
		
			while( nCnt < hotBill.size() )
			{
				
				hotBill.get(nCnt).setTimeInfo();
				hotBill.get(nCnt).setHotbillSeqNo(hotBill.get(nCnt).getClcWrkNo());
				
				if( hotBill.get(nCnt).getBillAmt() == 0 ) hotBill.get(nCnt).setFullPayYn("Y");
				obj.add(hotBill.get(nCnt));
				nCnt ++;
			}			
		 	result =  clsDao.saveHotBillInfo(obj);
			if ( result < 0 )
			{
				clslog.writeLog("saveHotBillDirect Error.");
			    return result;
			}
				
			result =  clsDao.saveInfoDirect(obj);
			if ( result < 0 )
			{
				clslog.writeLog("saveJdbcDirect Error.");
			    return result;
			}

			obj.clear();
			List<CBillComm> billMast  = clsDao.listHotBillMastAllInfo(comm);
			nCnt = 0;
			while( nCnt < billMast.size() )
			{
				
				obj.add(billMast.get(nCnt));
				nCnt ++;
			}			
			
		 	result =  clsDao.saveMastInfoDirect(obj);
		 	if ( result < 0 )
			{
				clslog.writeLog("saveMastInfoDirect Error.");
			    return result;
			}

		return result;
	}
	
	
}