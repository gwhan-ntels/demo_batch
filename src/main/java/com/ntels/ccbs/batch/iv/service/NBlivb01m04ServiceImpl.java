package com.ntels.ccbs.batch.iv.service;

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

	/** NBliv04m01Dao Autowired. */
	@Autowired
	private NBlivb01m04Dao nBlivb01m04Dao;

	@Autowired
	CBillWrkService clsWrkSvc;

	@Autowired
	private ClogService clslog;

	@Autowired
	private DataSource dataSource;

	public LazyLoader<CBillComm> listJdbcDirect(Common comm) {
		return nBlivb01m04Dao.listInfoDirect(comm);
	}

	public int saveJdbcDirect(List<Object> obj) {
		int result = 0;

		result = nBlivb01m04Dao.saveInfoDirect(obj);

		if (result <= 0) {
			clslog.writeLog("NBlivb01m04-saveJdbcDirect Error : " + obj.get(0).toString());
			return result;
		}

		return nBlivb01m04Dao.deleteInfoDirect(obj);
	}

	public LazyLoader<CBillComm> listDivJdbcDirect(Common comm) {
		return nBlivb01m04Dao.listDivInfoDirect(comm);
	}

	public int saveWrkDirect(List<Object> obj) {
		return nBlivb01m04Dao.saveWrkDirect(obj);

	}

	public int saveDivJdbcDirect(List<Object> obj) {

		int result = 0;

		result = nBlivb01m04Dao.saveDivInfoDirect(obj);
		if (result <= 0) {
			clslog.writeLog("NBlivb01m04-saveDivInfoDirect Error : " + obj.get(0).toString());
			return result;
		}

		return clsWrkSvc.saveJdbcDirect(obj);
	}
	
	

}